package senai.tcc.zupiapi.zupibackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import senai.tcc.zupiapi.zupibackend.dto.ChildDTO;
import senai.tcc.zupiapi.zupibackend.dto.ChildResponseDTO;
import senai.tcc.zupiapi.zupibackend.model.Child;
import senai.tcc.zupiapi.zupibackend.services.ChildService;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/child")
public class ChildController {

    @Autowired
    private ChildService childService;

    @GetMapping
    public ResponseEntity<List<Child>> findAll() {
        return ResponseEntity.ok(childService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity< List<ChildResponseDTO>> findByResponsibleId(@PathVariable Long id) {
        return ResponseEntity.ok().body(childService.findByResponsibleId(id));
    }

    @PostMapping
    public ResponseEntity<ChildResponseDTO> save(@RequestBody ChildDTO child) {

        ChildResponseDTO savedChild = childService.save(child);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedChild.id()).toUri();

        return ResponseEntity.created(uri).body(savedChild);
    }

}
