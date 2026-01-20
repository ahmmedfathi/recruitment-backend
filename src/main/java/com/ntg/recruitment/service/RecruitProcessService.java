package com.ntg.recruitment.service;

import com.ntg.recruitment.dto.ProcessDto;
import com.ntg.recruitment.entity.RecruitProcess;
import com.ntg.recruitment.entity.User;
import com.ntg.recruitment.mapper.RecruitProcessMapper;
import com.ntg.recruitment.repo.ProcessRepo;
import com.ntg.recruitment.repo.User_ProcessRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.comparator.ComparableComparator;

import java.util.Date;
import java.util.List;

@Service

public class RecruitProcessService {

    private User_ProcessRepo user_ProcessRepo;
    private final ProcessRepo processRepo;
    private final RecruitProcessMapper mapper;

    public RecruitProcessService(ProcessRepo processRepo,
                                 RecruitProcessMapper mapper ,User_ProcessRepo user_ProcessRepo) {
        this.processRepo = processRepo;
        this.mapper = mapper;
        this.user_ProcessRepo = user_ProcessRepo;
    }

    public ProcessDto createProcess(ProcessDto dto) {

        if (processRepo.existsByName(dto.getName())) {
            throw new RuntimeException("Process already exists");
        }

        RecruitProcess process = mapper.toEntity(dto);

        process.setActive(true);
        process.setCreatedAt(new Date());
        process.setDeleted(false);

        RecruitProcess saved = processRepo.save(process);
        return mapper.toDto(saved);
    }

    public List<ProcessDto> getAllProcesses() {
        return mapper.toDtoList(processRepo.findAll());
    }

    public ProcessDto getProcessById(Long id) {
        RecruitProcess process = processRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Process not found"));
        List<User> Candidates ;
        Candidates = user_ProcessRepo.findAllUsersByRecruitProcess_Id(id);
        ProcessDto dtoReturn =  mapper.toDto(process);
        dtoReturn.setCandidates(Candidates);
        return dtoReturn;
    }

    public void endProcess(Long id) {
        RecruitProcess process = processRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Process not found"));

        process.setActive(false);
        processRepo.save(process);
    }

    public void deleteProcess(Long id) {
        RecruitProcess process = processRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Process not found"));

        process.setDeleted(true);
        processRepo.save(process);
    }

    public List<RecruitProcess> getActiveProcesses() {
        return processRepo.findIsActiveProcess();
    }
}
