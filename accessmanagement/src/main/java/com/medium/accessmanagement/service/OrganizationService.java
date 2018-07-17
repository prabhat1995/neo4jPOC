package com.medium.accessmanagement.service;

import com.medium.accessmanagement.entity.InputRelationship;
import com.medium.accessmanagement.entity.Organization;
import com.medium.accessmanagement.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    public Organization saveOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    public Organization updateOrganization(InputRelationship body) {

        Organization organization = organizationRepository.findByOrganizationId(body.getOrganizationId());
        organization.setStatus(body.getStatus());
        return organizationRepository.save(organization);
    }
}
