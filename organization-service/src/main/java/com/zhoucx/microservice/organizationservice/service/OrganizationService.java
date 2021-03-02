package com.zhoucx.microservice.organizationservice.service;

import com.zhoucx.microservice.organizationservice.domian.Organization;
import com.zhoucx.microservice.organizationservice.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository orgRepository;

    public Organization getOrg(String organizationId) {
        return orgRepository.findByOrganizationId(organizationId);
    }

    public void saveOrg(Organization org){
        org.setId(UUID.randomUUID().toString());

        orgRepository.save(org);

    }

    public void updateOrg(Organization org){
        orgRepository.save(org);
    }

    public void deleteOrg(Organization org){
        orgRepository.deleteById(org.getId());
    }
}

