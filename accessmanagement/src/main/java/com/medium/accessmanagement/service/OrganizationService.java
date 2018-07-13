package com.medium.accessmanagement.service;

import com.medium.accessmanagement.entity.Organization;
import com.medium.accessmanagement.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    public Organization saveOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }
}
