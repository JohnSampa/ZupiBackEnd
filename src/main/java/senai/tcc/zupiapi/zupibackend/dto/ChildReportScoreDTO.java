package senai.tcc.zupiapi.zupibackend.dto;

import senai.tcc.zupiapi.zupibackend.model.ChildReportScore;

public record ChildReportScoreDTO(
        String theme,
        Integer score
) {
    public ChildReportScoreDTO(ChildReportScore score){
        this(
                score.getSkillArea().getName(),
                score.getScore()
        );
    }
}
