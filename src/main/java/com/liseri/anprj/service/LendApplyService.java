package com.liseri.anprj.service;

import com.liseri.anprj.domain.LendApply;
import com.liseri.anprj.repository.LendApplyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing LendApply.
 */
@Service
@Transactional
public class LendApplyService {

    private final Logger log = LoggerFactory.getLogger(LendApplyService.class);
    
    @Inject
    private LendApplyRepository lendApplyRepository;

    /**
     * Save a lendApply.
     *
     * @param lendApply the entity to save
     * @return the persisted entity
     */
    public LendApply save(LendApply lendApply) {
        log.debug("Request to save LendApply : {}", lendApply);
        LendApply result = lendApplyRepository.save(lendApply);
        return result;
    }

    /**
     *  Get all the lendApplies.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<LendApply> findAll(Pageable pageable) {
        log.debug("Request to get all LendApplies");
        Page<LendApply> result = lendApplyRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one lendApply by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public LendApply findOne(Long id) {
        log.debug("Request to get LendApply : {}", id);
        LendApply lendApply = lendApplyRepository.findOne(id);
        return lendApply;
    }

    /**
     *  Delete the  lendApply by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete LendApply : {}", id);
        lendApplyRepository.delete(id);
    }
}
