package com.ntg.recruitment.service;

import com.ntg.recruitment.dto.UserDto;
import com.ntg.recruitment.dto.UserDto;
import com.ntg.recruitment.dto.UserDtoResponse;
import com.ntg.recruitment.entity.Position;
import com.ntg.recruitment.entity.User;
import com.ntg.recruitment.Exception.*;
import com.ntg.recruitment.mapper.PositionMapper;
import com.ntg.recruitment.mapper.UserMapper;
import com.ntg.recruitment.repo.PositionRepo;
import com.ntg.recruitment.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;
    private  PositionRepo positionRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, PositionRepo positionRepository,UserMapper userMapper) {
        this.userRepository = userRepository;
        this.positionRepository = positionRepository;
        this.userMapper = userMapper;
    }

//    public UserDtoResponse createUser(UserDtoRequest dto) {
//        User user = userMapper.toEntity(dto);
//        userRepository.save(user);
//        return userMapper.toDto(user);
//    }
//
//
public UserDto updateUserProfile(Long id, UserDto dto) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

    userMapper.updateEntityFromDto(dto, user);

    User updatedUser = saveUser(user);
    return userMapper.toDto(updatedUser);
}


    //methods for manager
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).get();
//               .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }


    public User getUserById(Long id) {
        User user = userRepository.findById(id).get();
//               .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return user;
    }


//    public List<User> getUsersByStatus(Long statusId) {
//        return userRepository.findAllByStatusId(statusId);
//    }




    public List<User> searchUsersByUsername(String username) {
        return userRepository.findByUsernameContainingIgnoreCase(username);

    }


    public List<User> getAllCandidates() {
        return userRepository.findAllByCandidate();

    }



    public User saveUser(User user) {
        return userRepository.save(user);
    }



//    @Transactional
//    public void applyToPosition(Long userId, Long positionId) {
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
//
//        Position position = positionRepository.findById(positionId)
//                .orElseThrow(() -> new RuntimeException("Position not found with id: " + positionId));
//
//        user.getPositions().add(position);
//        position.getUsers().add(user);
//
//        userRepository.save(user);
//        positionRepository.save(position);
//    }
}


