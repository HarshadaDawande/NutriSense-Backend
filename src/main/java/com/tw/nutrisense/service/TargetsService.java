package com.tw.nutrisense.service;

import com.tw.nutrisense.repository.Targets.TargetsDocument;
import com.tw.nutrisense.repository.Targets.TargetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TargetsService {
    private final TargetsRepository targetsRepository;

    @Autowired
    public TargetsService(TargetsRepository targetsRepository) {
        this.targetsRepository = targetsRepository;
    }

    public void saveTargets(TargetsDocument targetsDocument) {
        targetsRepository.save(targetsDocument);
    }
}
