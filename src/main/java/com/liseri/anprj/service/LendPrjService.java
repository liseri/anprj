package com.liseri.anprj.service;

import com.liseri.anprj.domain.LendPrj;
import com.liseri.anprj.repository.LendPrjRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing LendPrj.
 */
@Service
@Transactional
public class LendPrjService {

    private final Logger log = LoggerFactory.getLogger(LendPrjService.class);
    
    @Inject
    private LendPrjRepository lendPrjRepository;

    /**
     * Save a lendPrj.
     *
     * @param lendPrj the entity to save
     * @return the persisted entity
     */
    public LendPrj save(LendPrj lendPrj) {
        log.debug("Request to save LendPrj : {}", lendPrj);
        LendPrj result = lendPrjRepository.save(lendPrj);
        return result;
    }

    /**
     *  Get all the lendPrjs.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<LendPrj> findAll(Pageable pageable) {
        log.debug("Request to get all LendPrjs");
        Page<LendPrj> result = lendPrjRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one lendPrj by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public LendPrj findOne(Long id) {
        log.debug("Request to get LendPrj : {}", id);
        LendPrj lendPrj = lendPrjRepository.findOne(id);
        return lendPrj;
    }

    /**
     *  Delete the  lendPrj by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete LendPrj : {}", id);
        lendPrjRepository.delete(id);
    }
}
