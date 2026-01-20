package com.ntg.recruitment.repo;

import com.ntg.recruitment.entity.RecruitProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProcessRepo extends JpaRepository<RecruitProcess,Long> {
    List<RecruitProcess> findByNameContainingIgnoreCase(String name);
     boolean existsByName(String name);

    @Query("SELECT p FROM RecruitProcess p WHERE p.isActive = true AND p.isDeleted = false")
    List<RecruitProcess> findIsActiveProcess();



}
