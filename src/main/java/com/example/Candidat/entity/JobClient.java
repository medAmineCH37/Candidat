package com.example.Candidat.entity;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "Job", url = "http://localhost:8081")
public interface JobClient {
    @RequestMapping("jobs")
    public List<Job> getAllJobs();

    @RequestMapping("jobs/{id}")
    public Job getJobById(@PathVariable int id);
}

