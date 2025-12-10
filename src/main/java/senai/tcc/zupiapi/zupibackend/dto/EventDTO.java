package senai.tcc.zupiapi.zupibackend.dto;

import java.time.Instant;

public record EventDTO(
        String title,
        Instant date,
        Instant finish,
        Integer childId,
        Integer skillArea,
        Long userId
) {

}
