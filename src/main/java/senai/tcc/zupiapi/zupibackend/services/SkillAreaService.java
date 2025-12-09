package senai.tcc.zupiapi.zupibackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senai.tcc.zupiapi.zupibackend.model.SkillArea;
import senai.tcc.zupiapi.zupibackend.repositories.SkillAreaRepository;

import java.util.List;

@Service
public class SkillAreaService {

    @Autowired
    private SkillAreaRepository skillAreaRepository;

    public List<SkillArea> findAllSkillAreas() {
        return skillAreaRepository.findAll();
    }

    public SkillArea save(SkillArea skillArea) {
        return skillAreaRepository.save(skillArea);
    }
}
