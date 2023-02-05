package com.example.demo.repositories;

import com.example.demo.entity.TreatmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITreatmentRepository extends JpaRepository<TreatmentEntity, Integer> {

}
