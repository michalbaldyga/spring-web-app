package com.spring.lab1.specialization.event.repository;

import com.spring.lab1.specialization.entity.Specialization;
import com.spring.lab1.specialization.event.dto.PostSpecializationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class SpecializationEventRepository {
    
    private RestTemplate restTemplate;
    
    @Autowired
    public SpecializationEventRepository(@Value("http://doc:8081/api/") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Specialization specialization) {
        restTemplate.delete("/specializations/{name}", specialization.getName());
    }

    public void create(Specialization specialization) {
        restTemplate.postForLocation("/specializations", PostSpecializationRequest.entityToDtoMapper().apply(specialization));
    }
}
