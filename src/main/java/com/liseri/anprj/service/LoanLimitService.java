package com.liseri.anprj.service;

import com.liseri.anprj.domain.LoanLimit;
import com.liseri.anprj.repository.LoanLimitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing LoanLimit.
 */
@Service
@Transactional
public class LoanLimitService {

    private final Logger log = LoggerFactory.getLogger(LoanLimitService.class);
    
    @Inject
    private LoanLimitRepository loanLimitRepository;

    /**
     * Save a loanLimit.
     *
     * @param loanLimit the entity to save
     * @return the persisted entity
     */
    public LoanLimit save(LoanLimit loanLimit) {
        log.debug("Request to save LoanLimit : {}", loanLimit);
        LoanLimit result = loanLimitRepository.save(loanLimit);
        return result;
    }

    /**
     *  Get all the loanLimits.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<LoanLimit> findAll(Pageable pageable) {
        log.debug("Request to get all LoanLimits");
        Page<LoanLimit> result = loanLimitRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one loanLimit by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public LoanLimit findOne(Long id) {
        log.debug("Request to get LoanLimit : {}", id);
        LoanLimit loanLimit = loanLimitRepository.findOne(id);
        return loanLimit;
    }

    /**
     *  Delete the  loanLimit by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete LoanLimit : {}", id);
        loanLimitRepository.delete(id);
    }
}
