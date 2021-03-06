package com.zhoucx.microservice.licensesservice.client;

import com.zhoucx.microservice.licensesservice.domain.Organization;
import com.zhoucx.microservice.licensesservice.fallback.OrganizationFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "organizationservice", fallback = OrganizationFallBack.class)
public interface OrganizationFeignClient {

    @GetMapping(value="/v1/organizations/getOrganization")
    Organization getOrganization(@RequestParam("organizationId") String organizationId);
}
