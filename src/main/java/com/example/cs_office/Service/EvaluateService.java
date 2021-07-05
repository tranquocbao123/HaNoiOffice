package com.example.cs_office.Service;

import com.example.cs_office.Model.Entity.Evaluate;
import com.example.cs_office.Repository.EvaluateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EvaluateService {

    private final EvaluateRepository evaluateRepository;

    @Autowired
    public EvaluateService(EvaluateRepository evaluateRepository) {
        this.evaluateRepository = evaluateRepository;
    }

    public List<Evaluate> getEvaluate() {
        return evaluateRepository.findAll();
    }

    public List<Evaluate> getEvaluateByStatus(boolean status) {

        return evaluateRepository.findEvaluateByStatus(status);
    }

    public Optional<Evaluate> getEvaluateById(int evaluateId) {
        Optional<Evaluate> evaluate = evaluateRepository.findById(evaluateId);
        return evaluate;
    }

    public void addNewEvaluate(Evaluate evaluate) {
        Optional<Evaluate> evaluateOptional =
                evaluateRepository.findEvaluateById(evaluate.getId());
        if (evaluateOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }
        evaluateRepository.save(evaluate);
    }

    public void deleteEvaluate(int evaluateId) {
        boolean exists = evaluateRepository.existsById(evaluateId);
        if (!exists) {
            throw new IllegalStateException("evaluate with id " + evaluateId + " does not exists");
        }
        evaluateRepository.deleteById(evaluateId);
    }

    @Transactional
    public Evaluate updateEvaluateStatus(Evaluate evaluate) {
        evaluate.setStatus(false);
        return evaluateRepository.save(evaluate);
    }

    @Transactional
    public Evaluate updateEvaluateBlack(Evaluate evaluate) {
        evaluate.setStatus(true);
        return evaluateRepository.save(evaluate);
    }

    @Transactional
    public Evaluate updateEvaluate(Evaluate evaluate, int evaluateId){
        Evaluate evaluate1 = this.evaluateRepository.getOne(evaluateId);
        BeanUtils.copyProperties(evaluate,evaluate1);
        return evaluateRepository.saveAndFlush(evaluate1);
    }
}

