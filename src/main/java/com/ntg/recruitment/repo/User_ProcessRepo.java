package com.ntg.recruitment.repo;

import com.ntg.recruitment.entity.User;
import com.ntg.recruitment.entity.UserProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface User_ProcessRepo extends JpaRepository<UserProcess, Long> {

    boolean existsByUser_IdAndRecruitProcess_Id(Long userId, Long processId);

    Optional<UserProcess> findByUser_IdAndRecruitProcess_Id(Long userId, Long processId);

    List<UserProcess> findAllByRecruitProcess_Id(Long processId);

    @Query("SELECT up.user FROM UserProcess up WHERE up.recruitProcess.id = :processId")
    List<User> findAllUsersByRecruitProcess_Id(@Param("processId") Long processId);


}
