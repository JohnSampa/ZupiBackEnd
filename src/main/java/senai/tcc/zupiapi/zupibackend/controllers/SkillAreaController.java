package senai.tcc.zupiapi.zupibackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import senai.tcc.zupiapi.zupibackend.model.SkillArea;
import senai.tcc.zupiapi.zupibackend.services.SkillAreaService;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/skillAreas")
public class SkillAreaController {

    @Autowired
    private SkillAreaService skillAreaService;

    @GetMapping
    public ResponseEntity<List<SkillArea>>  findAllSkillAreas(){
        List<SkillArea> skillAreas = skillAreaService.findAllSkillAreas();

        return ResponseEntity.ok().body(skillAreas);
    }

    @PostMapping
    public ResponseEntity<SkillArea> save(@RequestBody SkillArea skillArea){
        SkillArea saved = skillAreaService.save(skillArea);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(uri).body(saved);
    }
}
