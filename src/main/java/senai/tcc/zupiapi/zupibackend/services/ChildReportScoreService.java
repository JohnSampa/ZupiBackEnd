package senai.tcc.zupiapi.zupibackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senai.tcc.zupiapi.zupibackend.dto.ChildReportScoreDTO;
import senai.tcc.zupiapi.zupibackend.model.Child;
import senai.tcc.zupiapi.zupibackend.model.ChildReport;
import senai.tcc.zupiapi.zupibackend.model.ChildReportScore;
import senai.tcc.zupiapi.zupibackend.model.SkillArea;
import senai.tcc.zupiapi.zupibackend.repositories.ChildReportScoreRepository;
import senai.tcc.zupiapi.zupibackend.repositories.SkillAreaRepository;

import java.util.List;

@Service
public class ChildReportScoreService {

    @Autowired
    private ChildReportScoreRepository childReportScoreRepository;

    @Autowired
    private SkillAreaRepository skillAreaRepository;


    public ChildReportScore save(ChildReportScoreDTO childReportScore, ChildReport childReport) {

        ChildReportScore childReportScoreEntity = new ChildReportScore();

        childReportScoreEntity.setScore(childReportScore.score());
        childReportScoreEntity.setSkillArea(skillAreaRepository.findByName(childReportScore.theme()));
        childReportScoreEntity.setChildReport(childReport);

        return childReportScoreRepository.save(childReportScoreEntity);
    }


}
