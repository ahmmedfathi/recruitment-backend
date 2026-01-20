package com.ntg.recruitment.repo;

import com.ntg.recruitment.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepo extends JpaRepository<Status,Long > {
    List<Status> findByName(String name);


}
