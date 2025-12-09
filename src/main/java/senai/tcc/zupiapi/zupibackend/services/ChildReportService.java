package senai.tcc.zupiapi.zupibackend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senai.tcc.zupiapi.zupibackend.dto.ChildReportDTO;
import senai.tcc.zupiapi.zupibackend.dto.ChildReportNewDTO;
import senai.tcc.zupiapi.zupibackend.dto.ChildScoresAvaregesByAreaDTO;
import senai.tcc.zupiapi.zupibackend.model.ChildReport;
import senai.tcc.zupiapi.zupibackend.model.ChildReportScore;
import senai.tcc.zupiapi.zupibackend.repositories.ChildReportRepository;
import senai.tcc.zupiapi.zupibackend.repositories.ChildRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class ChildReportService {

    @Autowired
    private ChildReportRepository childReportRepository;

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private ChildReportScoreService childReportScoreService;

    public List<ChildReportDTO> getChildLast3DaysReports(Integer id) {
        Instant daysBefore3 = LocalDate.now()
                .minusDays(3)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant();

        return childReportRepository.findAllByChildIdAndDateAfter(id, daysBefore3)
                .stream()
                .map(ChildReportDTO::new)
                .toList();

    }

    public List<ChildScoresAvaregesByAreaDTO> getChildScoresAreaAvareges(Integer id) {
        return childReportRepository.findChildScoresAreaAvareges(id);
    }

    public ChildReportDTO saveChildReport(ChildReportNewDTO childReport, Integer childId) {

        ChildReport report = new ChildReport();

        report.setDate(childReport.date());
        report.setChild(childRepository.findById(childId).orElseThrow());

        childReportRepository.save(report);

        childReport.scores().forEach(
                childReportScoreDTO -> {

                    ChildReportScore score = childReportScoreService.save(childReportScoreDTO, report);
                    report.getScores().add(score);
                }
        );

        return new ChildReportDTO(childReportRepository.save(report));
    }

    public ChildReportDTO updateChildReport(ChildReportDTO childReport,Integer childId) {

        ChildReport report = childReportRepository.findByIdAndChildId(childReport.id(),childId);

        childReport.scores().forEach(
                childReportScoreDTO -> {

                    ChildReportScore score = childReportScoreService.save(childReportScoreDTO, report);
                    report.getScores().add(score);
                }
        );

        return new ChildReportDTO(childReportRepository.save(report));
    }
}
