package com.liseri.anprj.service;

import com.liseri.anprj.domain.Enterprise;
import com.liseri.anprj.repository.EnterpriseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Enterprise.
 */
@Service
@Transactional
public class EnterpriseService {

    private final Logger log = LoggerFactory.getLogger(EnterpriseService.class);
    
    @Inject
    private EnterpriseRepository enterpriseRepository;

    /**
     * Save a enterprise.
     *
     * @param enterprise the entity to save
     * @return the persisted entity
     */
    public Enterprise save(Enterprise enterprise) {
        log.debug("Request to save Enterprise : {}", enterprise);
        Enterprise result = enterpriseRepository.save(enterprise);
        return result;
    }

    /**
     *  Get all the enterprises.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Enterprise> findAll(Pageable pageable) {
        log.debug("Request to get all Enterprises");
        Page<Enterprise> result = enterpriseRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one enterprise by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Enterprise findOne(Long id) {
        log.debug("Request to get Enterprise : {}", id);
        Enterprise enterprise = enterpriseRepository.findOne(id);
        return enterprise;
    }

    /**
     *  Delete the  enterprise by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Enterprise : {}", id);
        enterpriseRepository.delete(id);
    }
}
