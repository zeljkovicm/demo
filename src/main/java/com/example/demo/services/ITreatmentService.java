package com.example.demo.services;

import com.example.demo.models.TreatmentModel;
import com.example.demo.models.TreatmentWithCategoryModel;

import java.util.List;
import java.util.Optional;

public interface ITreatmentService {

    public List<TreatmentModel> GetAllTretments();
    public Optional<TreatmentModel> GetTreatment(Integer treatmentId);
    public Optional<TreatmentWithCategoryModel> GetTreatmentAndCategory(Integer treatmentId);
    public TreatmentModel CreateTreatment(TreatmentModel treatment);
    public TreatmentModel EditTreatment(TreatmentModel treatment);
    public void DeleteTreatment(Integer treatmentId);

}
