package com.ntg.recruitment.repo;

import com.ntg.recruitment.entity.Position;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PositionRepo extends JpaRepository<Position,Long> {

    List<Position> findByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);



    @Query(
            "Select p From Position p ORDER BY p.createdAt Desc"
    )
    List<Position> findAllOrderByCreatedAtDesc();


}
