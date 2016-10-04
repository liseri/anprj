package com.liseri.anprj.service;

import com.liseri.anprj.domain.LoanApply;
import com.liseri.anprj.repository.LoanApplyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing LoanApply.
 */
@Service
@Transactional
public class LoanApplyService {

    private final Logger log = LoggerFactory.getLogger(LoanApplyService.class);
    
    @Inject
    private LoanApplyRepository loanApplyRepository;

    /**
     * Save a loanApply.
     *
     * @param loanApply the entity to save
     * @return the persisted entity
     */
    public LoanApply save(LoanApply loanApply) {
        log.debug("Request to save LoanApply : {}", loanApply);
        LoanApply result = loanApplyRepository.save(loanApply);
        return result;
    }

    /**
     *  Get all the loanApplies.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<LoanApply> findAll(Pageable pageable) {
        log.debug("Request to get all LoanApplies");
        Page<LoanApply> result = loanApplyRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one loanApply by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public LoanApply findOne(Long id) {
        log.debug("Request to get LoanApply : {}", id);
        LoanApply loanApply = loanApplyRepository.findOne(id);
        return loanApply;
    }

    /**
     *  Delete the  loanApply by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete LoanApply : {}", id);
        loanApplyRepository.delete(id);
    }
}
