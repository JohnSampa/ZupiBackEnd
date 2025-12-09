package senai.tcc.zupiapi.zupibackend.dto;

import senai.tcc.zupiapi.zupibackend.model.ChildReport;

import java.time.Instant;
import java.util.List;

public record ChildReportNewDTO(
        Instant date,
        List<ChildReportScoreDTO> scores
) {
    public ChildReportNewDTO(ChildReport childReport){
        this(
          childReport.getDate(),
          childReport.getScores().stream().map(ChildReportScoreDTO::new).toList()
        );
    }
}
