package com.zhoucx.microservice.licensesservice.controller;

import com.zhoucx.microservice.licensesservice.domain.License;
import com.zhoucx.microservice.licensesservice.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/organizations/licenses")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @GetMapping(value = "/getLicenses")
    public License getLicensesWithClient(String organizationId,String licenseId,String clientType) {
        return licenseService.getLicense(organizationId,licenseId, clientType);
    }

}

