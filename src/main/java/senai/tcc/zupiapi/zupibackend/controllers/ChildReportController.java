package senai.tcc.zupiapi.zupibackend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import senai.tcc.zupiapi.zupibackend.dto.ChildReportDTO;
import senai.tcc.zupiapi.zupibackend.dto.ChildReportNewDTO;
import senai.tcc.zupiapi.zupibackend.dto.ChildScoresAvaregesByAreaDTO;
import senai.tcc.zupiapi.zupibackend.services.ChildReportService;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/reports")
public class ChildReportController {

    @Autowired
    private ChildReportService childReportService;

    @GetMapping(value = "/lasted/{id}")
    public ResponseEntity<List<ChildReportDTO>> getLast3daysReports(@PathVariable Integer id) {
        return ResponseEntity.ok().body(childReportService.getChildLast3DaysReports(id));
    }

    @GetMapping(value = "/avg/{id}")
    public ResponseEntity<List<ChildScoresAvaregesByAreaDTO>> getAllAverage(@PathVariable Integer id) {

        return ResponseEntity.ok().body(childReportService.getChildScoresAreaAvareges(id));
    }

    @PostMapping(value = "/{id}")       
    public ResponseEntity<ChildReportDTO> saveChildReport(@RequestBody ChildReportNewDTO childReport, @PathVariable Integer id) {
        ChildReportDTO savedChildReport = childReportService.saveChildReport(childReport,id);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedChildReport.id()).toUri();

        return ResponseEntity.created(uri).body(savedChildReport);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ChildReportDTO> updateChildReport(@RequestBody ChildReportDTO childReport,@PathVariable Integer id) {
        ChildReportDTO savedChildReport = childReportService.updateChildReport(childReport,id);
        return ResponseEntity.ok().body(savedChildReport);
    }
}
