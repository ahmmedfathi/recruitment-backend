package com.ntg.recruitment.service;

import com.ntg.recruitment.dto.AddCandidatesToProcessDto;
import com.ntg.recruitment.dto.ProcessDto;
import com.ntg.recruitment.dto.UserProcessDto;
import com.ntg.recruitment.entity.RecruitProcess;
import com.ntg.recruitment.entity.Status;
import com.ntg.recruitment.entity.User;
import com.ntg.recruitment.entity.UserProcess;
import com.ntg.recruitment.mapper.RecruitProcessMapper;
import com.ntg.recruitment.mapper.UserProcessMapper;
import com.ntg.recruitment.repo.*;
import com.ntg.recruitment.repo.User_ProcessRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class UserProcessService {

    private final User_ProcessRepo userProcessRepo;
    private final StatusRepo statusRepo;
    private final UserProcessMapper mapper;
    private RecruitProcessMapper RecruitMapper;
    private UserPositionRepo userPositionRepo;
    private ProcessRepo processRepo;
    private EmailService emailService;


    @Transactional
    public ProcessDto createProcessWithCandidates(AddCandidatesToProcessDto dto) {

        if (processRepo.existsByName(dto.getName())) {
            throw new RuntimeException("Process already exists");
        }

        RecruitProcess process = new RecruitProcess();
        process.setName("Process for Position " + dto.getName());        process.setActive(true);
        process.setCreatedAt(new Date());
        process.setDeleted(false);

        RecruitProcess savedProcess = processRepo.save(process);

        List<User> candidates = userPositionRepo.findAllCandidatesByPositionId(dto.getPositionId());

        List<User> selectedCandidates = candidates.stream()
                .filter(user -> dto.getUserIds().contains(user.getId()))
                .toList();

        selectedCandidates.forEach(user -> {
            if (userProcessRepo.existsByUser_IdAndRecruitProcess_Id(user.getId(), savedProcess.getId())) {
                throw new RuntimeException("User already exists in this process: " + user.getId());
            }

            // 1. حفظ العلاقة في قاعدة البيانات
            UserProcess userProcess = new UserProcess();
            userProcess.setUser(user);
            userProcess.setRecruitProcess(savedProcess);
            userProcess.setExamLink(dto.getExamLink());
            userProcessRepo.save(userProcess);

            // 2. إرسال الإيميل للمرشح المختار
            try {
                emailService.sendExamEmail(user.getEmail(), user.getFullName(), dto.getExamLink());
            } catch (Exception e) {
                // نسجل الخطأ ولكن لا نوقف العملية (اختياري حسب منطق العمل لديك)
                System.err.println("Failed to send email to: " + user.getEmail());
            }
        });

        return RecruitMapper.toDto(savedProcess);
    }


    @Transactional
    public UserProcessDto updateCandidate(UserProcessDto dto) {

        UserProcess userProcess =
                userProcessRepo.findByUser_IdAndRecruitProcess_Id(
                                dto.getUserId(), dto.getProcessId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException("Candidate not found in process"));

        userProcess.setGrade(dto.getGrade());

        userProcess.setFeedback(dto.getFeedback());

        Status status = statusRepo.findById(dto.getStatusId())
                .orElseThrow(() -> new RuntimeException("Status not found"));
        userProcess.setStatus(status);


        return mapper.toDto(userProcessRepo.save(userProcess));
    }


    public UserProcessDto updateCandidateStatus(UserProcessDto dto) {

        UserProcess userProcess =
                userProcessRepo.findByUser_IdAndRecruitProcess_Id(
                                dto.getUserId(), dto.getProcessId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException("Candidate not found in process"));
        Status status = statusRepo.findById(dto.getStatusId())
                .orElseThrow(() -> new RuntimeException("Status not found"));
        userProcess.setStatus(status);

        return mapper.toDto(userProcessRepo.save(userProcess));
    }

    public List<UserProcessDto> getCandidatesByProcess(Long processId) {
        return userProcessRepo.findAllByRecruitProcess_Id(processId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<Status> getAllStatuses() {
        return statusRepo.findAll();

    }
}
