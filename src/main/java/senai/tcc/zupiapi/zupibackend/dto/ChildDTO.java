package senai.tcc.zupiapi.zupibackend.dto;

import senai.tcc.zupiapi.zupibackend.model.Child;

import java.time.LocalDate;

public record ChildDTO(
        String name,
        LocalDate birthDate,
        String schoolClass,
        String condition,
        Long responsibleId
) {
    public ChildDTO(Child child){
        this(
                child.getName(),
                child.getBirthDate(),
                child.getSchoolClass(),
                child.getCondition(),
                child.getResponsible().getId()
        );
    }
}
