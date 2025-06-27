package com.example.Candidat.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatService {
    @Autowired
    private CandidatRepository candidateRepository;

    @Autowired
    private JobClient jobServiceClient;
    public List<Job> getJobs() {
        return jobServiceClient.getAllJobs();
    }
    public Job getJobById(int id) {
        return jobServiceClient.getJobById(id);
    }

    public List<Job> getFavoriteJobs(int candidateId) {
        Candidat candidate = candidateRepository.findById(candidateId).get();
        return candidate.getFavoriteJobs().stream()
                .map(jobServiceClient::getJobById)
                .collect(Collectors.toList());
    }
    public void saveFavoriteJob(int candidateId, int jobId) {
        Candidat candidate = candidateRepository.findById(candidateId).get();
        candidate.getFavoriteJobs().add(jobId);
        candidateRepository.save(candidate);
    }



    public Candidat addCandidat(Candidat candidate) {
        return candidateRepository.save(candidate);
    }
    public List<Candidat> getAll()
    {return candidateRepository.findAll();}
    public Candidat updateCandidat(int id, Candidat newCandidat) {
        if (candidateRepository.findById(id).isPresent()) {

            Candidat existingCandidat = candidateRepository.findById(id).get();
            existingCandidat.setNom(newCandidat.getNom());
            existingCandidat.setPrenom(newCandidat.getPrenom());
            existingCandidat.setEmail(newCandidat.getEmail());
            return candidateRepository.save(existingCandidat);
        } else
            return null;
    }
    public String deleteCandidat(int id) {
        if (candidateRepository.findById(id).isPresent()) {
            candidateRepository.deleteById(id);
            return "candidat supprimé";
        } else
            return "candidat non supprimé";
    }
}
