package com.ntg.recruitment.repo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ntg.recruitment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

//
//    @Query("SELECT u FROM User u WHERE u.status.id = :statusId")
//    List<User> findAllByStatusId(@Param("statusId") Long statusId);

    List<User> findByUsernameContainingIgnoreCase(String username);
    @Query("SELECT u FROM User u WHERE u.role = 'CANDIDATE'")
    List<User> findAllByCandidate();
}