package com.example.demo.controllers;

import com.example.demo.models.TreatmentModel;
import com.example.demo.models.TreatmentWithCategoryModel;
import com.example.demo.services.ITreatmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("treatment")
public class TreatmentController {

    private final ITreatmentService treatmentService;

    public TreatmentController( ITreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @GetMapping("get/all/treatments")
    @CrossOrigin("*")
    public List<TreatmentModel> GetAllTreatments() {return treatmentService.GetAllTretments();}

    @GetMapping("get/treatment/by/id")
    @CrossOrigin("*")
    public Optional<TreatmentModel> GetTreatment(Integer treatmentId) {
        return treatmentService.GetTreatment(treatmentId);
        }

    @GetMapping("get/treatment/category/by/id")
    @CrossOrigin("*")
    public Optional<TreatmentWithCategoryModel> GetTreatmentAndCategory(Integer treatmentId) {
        return treatmentService.GetTreatmentAndCategory(treatmentId);
    }

    @PostMapping("create")
    @CrossOrigin("*")
    public ResponseEntity<?> Insert(@RequestBody @Valid TreatmentModel model, BindingResult result) {

        if (result.hasErrors())
        {
            return new ResponseEntity<String>("Creating treatment unsuccessful!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try
        {
            return new ResponseEntity<TreatmentModel>(treatmentService.CreateTreatment(model), HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<String>("Creating treatment unsuccessful!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("edit")
    @CrossOrigin("*")
    public ResponseEntity<?> Edit(@RequestBody @Valid TreatmentModel model, BindingResult result) {

        if (result.hasErrors())
        {
            return new ResponseEntity<String>("Editing treatment unsuccessful!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try
        {
            return new ResponseEntity<TreatmentModel>(treatmentService.EditTreatment(model), HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<String>("Editing treatment unsuccessful!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("delete")
    @CrossOrigin("*")
    public void Delete(@RequestBody TreatmentModel model) {
        treatmentService.DeleteTreatment(model.getTreatmentId());
    }

}
