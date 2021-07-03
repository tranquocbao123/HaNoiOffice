package com.example.cs_office.Controller;

import com.example.cs_office.Model.Evaluate;
import com.example.cs_office.Service.EvaluateService;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PathResources.EVALUATE)
@CrossOrigin("*")
public class EvaluateController {

    private final EvaluateService evaluateService;

    @Autowired
    public EvaluateController(EvaluateService evaluateService) {
        this.evaluateService = evaluateService;
    }

    //list evaluate
    @GetMapping(PathResources.FIND_ALL)
    public List<Evaluate> getEvaluate() {
        return evaluateService.getEvaluate();
    }

    //list evaluate by status == fasle
    @GetMapping(PathResources.FIND_BY_STATUSFALSE)
    public List<Evaluate> getEvaluateByStatusFalse() {
        return evaluateService.getEvaluateByStatus(false);
    }

    //list evaluate by status == true
    @GetMapping(PathResources.FIND_BY_STATUSTRUE)
    public List<Evaluate> getEvaluateByStatusTrue() {
        return evaluateService.getEvaluateByStatus(true);
    }

    // insert evaluate
    @PostMapping(PathResources.SAVE)
    public void insertEvaluate(@RequestBody Evaluate evaluate) {
        evaluateService.addNewEvaluate(evaluate);
    }

    //search evaluate by id
    @GetMapping(path = PathResources.FIND_BY_ID)
    public Optional<Evaluate> getEvaluateById(
            @PathVariable("id") int evaluateId) {
        return evaluateService.getEvaluateById(evaluateId);
    }

    //delete evaluate by id
    @DeleteMapping(path = PathResources.DELETEBYID)
    public void deleteEvaluate(
            @PathVariable("id") int evaluateId) {
        evaluateService.deleteEvaluate(evaluateId);
    }

    //update evaluate by status
    @PutMapping(PathResources.UPDATESTATUSFALSE)
    public void updateEvaluateStatus(
            @RequestBody Evaluate evaluate
    ) {
        evaluateService.updateEvaluateStatus(evaluate);
    }

    //update evaluate black by status
    @PutMapping(path = PathResources.UPDATESTATUSTRUE)
    public void updateEvaluatesBlack(
            @RequestBody Evaluate evaluate
    ) {
        evaluateService.updateEvaluateBlack(evaluate);
    }

    //update evaluate by id
    @PutMapping(path = PathResources.UPDATEBYID)
    public Evaluate updateEvaluate
    (@RequestBody Evaluate evaluate,
     @PathVariable("id") int evaluateId) {
        return evaluateService.updateEvaluate(evaluate, evaluateId);
    }

}
