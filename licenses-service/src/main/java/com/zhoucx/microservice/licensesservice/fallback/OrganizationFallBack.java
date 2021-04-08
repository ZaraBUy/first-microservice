package com.zhoucx.microservice.licensesservice.fallback;

import com.zhoucx.microservice.licensesservice.client.OrganizationFeignClient;
import com.zhoucx.microservice.licensesservice.domain.Organization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationFallBack implements OrganizationFeignClient {
    @Override
    public Organization getOrganization(String organizationId) {
        System.out.println("111111111111111");
        return new Organization();
    }
}
