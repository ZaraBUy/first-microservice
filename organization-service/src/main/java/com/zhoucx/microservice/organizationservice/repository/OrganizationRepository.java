package com.zhoucx.microservice.organizationservice.repository;

import com.zhoucx.microservice.organizationservice.domian.Organization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrganizationRepository extends CrudRepository<Organization,String> {
    @Query(value = "select t from Organization t where t.id = ?1")
    Organization findByOrganizationId(String organizationId);
}
