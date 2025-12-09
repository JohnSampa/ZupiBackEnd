package senai.tcc.zupiapi.zupibackend.dto;

import senai.tcc.zupiapi.zupibackend.model.ChildReport;

import java.time.Instant;
import java.util.List;

public record ChildReportDTO(
        Long id,
        Instant date,
        List<ChildReportScoreDTO> scores
) {
    public ChildReportDTO(ChildReport childReport){
        this(
          childReport.getId(),
          childReport.getDate(),
          childReport.getScores().stream().map(ChildReportScoreDTO::new).toList()
        );
    }
}
