package com.zhoucx.microservice.licensesservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zhoucx.microservice.licensesservice.client.OrganizationDiscoveryClient;
import com.zhoucx.microservice.licensesservice.client.OrganizationFeignClient;
import com.zhoucx.microservice.licensesservice.client.OrganizationRestTemplateClient;
import com.zhoucx.microservice.licensesservice.config.ServiceConfig;
import com.zhoucx.microservice.licensesservice.domain.License;
import com.zhoucx.microservice.licensesservice.domain.Organization;
import com.zhoucx.microservice.licensesservice.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicenseService {

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    private OrganizationDiscoveryClient organizationDiscoveryClient;

    @Autowired
    private OrganizationRestTemplateClient organizationRestClient;

    @Autowired
    OrganizationFeignClient organizationFeignClient;

    @Autowired
    private ServiceConfig serviceConfig;

    private Organization retrieveOrgInfo(String organizationId, String clientType){
        Organization organization = null;

        switch (clientType) {
            case "discovery":
                System.out.println("I am using the discovery client");
                organization = organizationDiscoveryClient.getOrganization(organizationId);
                break;
            case "rest":
                System.out.println("I am using the rest client");
                organization = organizationRestClient.getOrganization(organizationId);
                break;
            case "feign":
                System.out.println("I am using the feign client");
                organization = organizationFeignClient.getOrganization(organizationId);
                break;
            default:
                organization = organizationDiscoveryClient.getOrganization(organizationId);
        }

        return organization;
    }

    public License getLicense(String organizationId, String licenseId, String clientType) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);

        Organization org = retrieveOrgInfo(organizationId, clientType);

        return license
                .withOrganizationName( org.getName())
                .withContactName( org.getContactName())
                .withContactEmail( org.getContactEmail() )
                .withContactPhone( org.getContactPhone() )
                .withComment(serviceConfig.getExampleProperty());
    }

    public List<License> getLicensesByOrg(String organizationId){
        try {
            Thread.sleep(99999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return licenseRepository.findByOrganizationId(organizationId);
    }

}
