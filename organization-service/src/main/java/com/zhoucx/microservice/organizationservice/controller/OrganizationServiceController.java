package com.zhoucx.microservice.organizationservice.controller;

import com.zhoucx.microservice.organizationservice.domian.Organization;
import com.zhoucx.microservice.organizationservice.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="v1/organizations")
public class OrganizationServiceController {
    @Autowired
    private OrganizationService orgService;


    @GetMapping(value="/getOrganization")
    public Organization getOrganization(String organizationId) {
        return orgService.getOrg(organizationId);
    }

    @PostMapping(value="/updateOrganization}")
    public void updateOrganization(String orgId, Organization org) {
        orgService.updateOrg( org );
    }

    @PostMapping(value="/saveOrganization")
    public void saveOrganization(@RequestBody Organization org) {
        orgService.saveOrg( org );
    }

    @PostMapping(value="/deleteOrganization")
    public void deleteOrganization( @PathVariable("orgId") String orgId,  @RequestBody Organization org) {
        orgService.deleteOrg( org );
    }
}
