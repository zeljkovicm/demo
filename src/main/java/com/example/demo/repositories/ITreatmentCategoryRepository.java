package com.example.demo.repositories;

import com.example.demo.entity.TreatmentWithCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITreatmentCategoryRepository extends JpaRepository<TreatmentWithCategoryEntity, Integer> {

}
