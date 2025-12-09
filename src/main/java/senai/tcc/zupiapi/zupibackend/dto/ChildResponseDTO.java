package senai.tcc.zupiapi.zupibackend.dto;

import senai.tcc.zupiapi.zupibackend.model.Child;

import java.time.LocalDate;

public record ChildResponseDTO(
        Integer id,
        String name,
        LocalDate birthDate,
        String schoolClass,
        String condition,
        Integer age
) {
    public ChildResponseDTO(Child child){
        this(
                child.getId(),
                child.getName(),
                child.getBirthDate(),
                child.getSchoolClass(),
                child.getCondition(),
                child.getAge()
        );
    }
}
