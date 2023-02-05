package com.example.demo.services;

import com.example.demo.entity.TreatmentEntity;
import com.example.demo.models.TreatmentModel;
import com.example.demo.models.TreatmentWithCategoryModel;
import com.example.demo.repositories.ITreatmentCategoryRepository;
import com.example.demo.repositories.ITreatmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service()
public class TreatmentService implements ITreatmentService{

    private ITreatmentRepository treatmentRepository;

    private ITreatmentCategoryRepository treatmentCategoryRepository;
    private ModelMapper mapper;

    public TreatmentService(ITreatmentRepository treatmentRepository, ITreatmentCategoryRepository treatmentCategoryRepository, ModelMapper mapper)
    {
        this.treatmentRepository = treatmentRepository;
        this.treatmentCategoryRepository = treatmentCategoryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TreatmentModel> GetAllTretments() {
        var models = mapper.map( treatmentRepository.findAll(), new ArrayList<TreatmentModel>().getClass());
        return models;
    }

    @Override
    public Optional<TreatmentModel> GetTreatment(Integer treatmentId) {

        var treatmentFromDb = treatmentRepository.findById(treatmentId);

        if (treatmentFromDb == null)
        {
            return null;
        }

        var treatmentModel =  mapper.map(treatmentFromDb, TreatmentModel.class);
        return Optional.of(treatmentModel);
    }

    @Override
    public Optional<TreatmentWithCategoryModel> GetTreatmentAndCategory(Integer treatmentId) {

        var treatmentWithCategoryFromDb = treatmentCategoryRepository.findById(treatmentId);

        if (treatmentWithCategoryFromDb == null)
        {
            return null;
        }

        var treatmentWithCategoryModel =  mapper.map(treatmentWithCategoryFromDb, TreatmentWithCategoryModel.class);
        return Optional.of(treatmentWithCategoryModel);
    }


    @Override
    public TreatmentModel CreateTreatment(TreatmentModel treatment) {
        var createdTreatment = treatmentRepository.save(mapper.map(treatment, TreatmentEntity.class));
        return mapper.map( createdTreatment, TreatmentModel.class );
    }

    @Override
    public TreatmentModel EditTreatment(TreatmentModel treatment) {

        var editedTreatment = treatmentRepository.save(mapper.map(treatment, TreatmentEntity.class));
        return mapper.map( editedTreatment, TreatmentModel.class );
    }

    @Override
    public void DeleteTreatment(Integer treatmentId) {
        treatmentRepository.deleteById(treatmentId);
    }

}
