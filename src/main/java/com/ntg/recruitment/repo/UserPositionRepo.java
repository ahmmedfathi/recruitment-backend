package com.ntg.recruitment.repo;

import com.ntg.recruitment.entity.UserPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserPositionRepo extends JpaRepository<UserPosition, Long> {

    boolean existsByUserIdAndPositionId(Long userId, Long positionId);

    @Query("SELECT up.user FROM UserPosition up WHERE up.position.id = :positionId")
    List<com.ntg.recruitment.entity.User> findAllCandidatesByPositionId(@Param("positionId") Long positionId);
}
