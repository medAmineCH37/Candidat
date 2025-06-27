package com.example.Candidat.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidatsms")
public class CandidatRestApi {
    @Autowired
    private CandidatService candidatService;

    @RequestMapping("/jobs")
    public List<Job> getAllJobs() {
        return candidatService.getJobs();
    }
    @RequestMapping("jobs/{id}")
    public Job getJobById(@PathVariable int id) {
        return candidatService.getJobById(id);
    }

    @GetMapping("/{id}/favorite-jobs")
    public List<Job> getFavoriteJobs(@PathVariable int id) {
        return candidatService.getFavoriteJobs(id);
    }

    @PostMapping("/{id}/favorite-jobs/{jobId}")
    public ResponseEntity<String> saveFavoriteJob(@PathVariable int id, @PathVariable
    int jobId) {
        Job job = candidatService.getJobById(jobId);
        if (job != null) {
            candidatService.saveFavoriteJob(id, jobId);
            return ResponseEntity.status(HttpStatus.OK).body("Job saved as favorite successfully.");
        } else {
            // Gérer le cas où le job n'existe pas
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Job not found with ID: " + jobId);
        }
    }



    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Candidat> createCandidat(@RequestBody Candidat candidat) {
        return new ResponseEntity<>(candidatService.addCandidat(candidat), HttpStatus.OK);
    }

    @RequestMapping
    @GetMapping
    public ResponseEntity<List<Candidat>> getAll() {
        return new ResponseEntity<>(candidatService.getAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Candidat> updateCandidat(@PathVariable(value = "id") int id,
                                                   @RequestBody Candidat candidat){
        return new ResponseEntity<>(candidatService.updateCandidat(id, candidat),
                HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCandidat(@PathVariable(value = "id") int id){
        return new ResponseEntity<>(candidatService.deleteCandidat(id), HttpStatus.OK);
    }
}
