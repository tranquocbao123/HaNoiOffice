package com.example.cs_office.Controller;

import com.example.cs_office.Model.Branch;
import com.example.cs_office.Model.Evaluate;
import com.example.cs_office.Model.Role;
import com.example.cs_office.Service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evaluate")
@CrossOrigin("*")
public class EvaluateController {

    private final EvaluateService evaluateService;

    @Autowired
    public EvaluateController(EvaluateService evaluateService) {
        this.evaluateService = evaluateService;
    }

    //list evaluate
    @GetMapping()
    public List<Evaluate> getEvaluate() {
        return evaluateService.getEvaluate();
    }

    //list evaluate by status == fasle
    @GetMapping("/false")
    public List<Evaluate> getEvaluateByStatusFalse() {
        return evaluateService.getEvaluateByStatus(false);
    }

    //list evaluate by status == true
    @GetMapping("/true")
    public List<Evaluate> getEvaluateByStatusTrue() {
        return evaluateService.getEvaluateByStatus(true);
    }

    // insert evaluate
    @PostMapping
    public void insertEvaluate(@RequestBody Evaluate evaluate) {
        evaluateService.addNewEvaluate(evaluate);
    }

    //search evaluate by id
    @GetMapping(path = "{evaluateId}")
    public Optional<Evaluate> getEvaluateById(
            @PathVariable("evaluateId") int evaluateId) {
        return evaluateService.getEvaluateById(evaluateId);
    }

    //delete evaluate by id
    @DeleteMapping(path = "{evaluateId}")
    public void deleteEvaluate(
            @PathVariable("evaluateId") int evaluateId) {
        evaluateService.deleteEvaluate(evaluateId);
    }

    //update evaluate by id
    @PutMapping(path = "/{evaluateId}")
    public Evaluate updateEvaluate
    (@RequestBody Evaluate evaluate,
     @PathVariable("evaluateId") int id) {
        return evaluateService.updateEvaluate(evaluate, id);
    }
}
