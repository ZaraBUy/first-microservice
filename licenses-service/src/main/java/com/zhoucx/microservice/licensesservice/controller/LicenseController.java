package com.zhoucx.microservice.licensesservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhoucx.microservice.licensesservice.domain.License;
import com.zhoucx.microservice.licensesservice.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "v1/organizations/licenses")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @GetMapping(value = "/getLicenses")
    public License getLicensesWithClient(String organizationId,String licenseId,String clientType) {
        return licenseService.getLicense(organizationId,licenseId, clientType);
    }

    @GetMapping(value = "/getLicenseByOrg")
    @HystrixCommand(fallbackMethod = "getErroInfo",
            commandProperties= {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000")})
    public List<License> getLicenseByOrg(String organizationId) {
        return licenseService.getLicensesByOrg(organizationId);
    }

    public List<License> getErroInfo(String organizationId){
        System.out.println("2222222222");
        ArrayList<License> licenses = new ArrayList<>();
        return licenses;
    }
}

