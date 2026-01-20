
package com.ntg.recruitment.service;

import com.ntg.recruitment.dto.UserDtoResponse;
import com.ntg.recruitment.dto.UserPositionDto;
import com.ntg.recruitment.entity.Position;
import com.ntg.recruitment.entity.User;
import com.ntg.recruitment.entity.UserPosition;
import com.ntg.recruitment.mapper.UserPositionMapper;
import com.ntg.recruitment.repo.PositionRepo;
import com.ntg.recruitment.repo.UserPositionRepo;
import com.ntg.recruitment.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserPositionService {

    private final UserRepository userRepository;
    private final PositionRepo positionRepository;
    private final UserPositionRepo userPositionRepo;
    private final UserPositionMapper userPositionMapper;

    @Transactional
    public void applyToJob(UserPositionDto dto) {
        if (userPositionRepo.existsByUserIdAndPositionId(dto.getUserId(), dto.getPositionId())) {
            throw new RuntimeException("You have already applied to this position before");
        }
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));

        Position position = positionRepository.findById(dto.getPositionId())
                .orElseThrow(() -> new RuntimeException("Position not found with id: " + dto.getPositionId()));

        if (userPositionRepo.existsByUserIdAndPositionId(dto.getUserId(), dto.getPositionId())) {
            throw new RuntimeException("User already applied to this position");
        }

        UserPosition userPosition = userPositionMapper.toEntity(dto);

        userPosition.setUser(user);
        userPosition.setPosition(position);

        userPositionRepo.save(userPosition);
    }

    public List<User> getAllCandidatesByPosition(Long positionId) {
        List<User> users = userPositionRepo.findAllCandidatesByPositionId(positionId);

//        List<UserDtoResponse> response = new ArrayList<>();
//        for (User user : users) {
//            UserDtoResponse dto = new UserDtoResponse();
//            dto.setUsername(user.getUsername());
//            dto.setEmail(user.getEmail());
//            response.add(dto);
//        }
        return users;
    }
}
