package senai.tcc.zupiapi.zupibackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import senai.tcc.zupiapi.zupibackend.model.*;
import senai.tcc.zupiapi.zupibackend.repositories.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
@Profile("dev")
public class Test implements CommandLineRunner {

    @Autowired
    private SkillAreaRepository skillAreaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private ChildReportRepository childReportRepository;

    @Autowired
    private ChildReportScoreRepository childReportScoreRepository;



    @Override
    public void run(String... args) throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        SkillArea skillArea = new SkillArea(
                null,
                "Concentração"
        );

        SkillArea skillArea2 = new SkillArea(
                null,
                "Interação Social"
        );

        skillAreaRepository.saveAll(Arrays.asList(skillArea, skillArea2));

        User user = new User(
                null,
                "Auri Rios",
                "auri@email.com",
                "12345567"
        );

        User user2 = new User(
                null,
                "Jose Cleudo",
                "jose@email.com",
                "123455678"
        );

        userRepository.saveAll(Arrays.asList(user, user2));

        Child child = new Child(
                null,
                "John",
                LocalDate.parse("2003-01-20",dtf2),
                "Pré",
                "AUTISMO",
                user

        );

        Child child3 = new Child(
                null,
                "Nelson",
                LocalDate.parse("2022-01-20",dtf2),
                "Pré",
                "AUTISMO",
                user

        );

        Child child2 = new Child(
                null,
                "Tefinha",
                LocalDate.parse("2007-01-02",dtf2),
                "Pré",
                "AUTISMO",
                user2
        );

        childRepository.saveAll(Arrays.asList(child, child2, child3));

        ChildReport childReport = new ChildReport(
                null,
                Instant.now(),
                child
        );

        childReportRepository.save(childReport);

        ChildReportScore childReportScore = new ChildReportScore(
                null,
                childReport,
                skillArea,
                82
        );

        ChildReportScore childReportScore2 = new ChildReportScore(
                null,
                childReport,
                skillArea,
                54
        );

        childReportScoreRepository.saveAll(Arrays.asList(childReportScore, childReportScore2));

        Event event = new Event(
                null,
                "Atividade de Casa",
                Instant.parse("2025-12-23T10:30:00Z"),
                Instant.parse("2025-12-23T11:30:00Z"),
                child,
                skillArea,
                user
        );

        Event event2 = new Event(
                null,
                "Atividade Motora",
                Instant.parse("2025-12-24T09:30:00Z"),
                Instant.parse("2025-12-24T10:30:00Z"),
                child2,
                skillArea2,
                user2
        );

        eventRepository.saveAll(Arrays.asList(event, event2));

    }
}
