package com.liseri.anprj.service;

import com.liseri.anprj.domain.LoanPrj;
import com.liseri.anprj.repository.LoanPrjRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

/**
 * Service Implementation for managing LoanPrj.
 */
@Service
@Transactional
public class LoanPrjService {

    private final Logger log = LoggerFactory.getLogger(LoanPrjService.class);

    @Inject
    private LoanPrjRepository loanPrjRepository;

    /**
     * Save a loanPrj.
     *
     * @param loanPrj the entity to save
     * @return the persisted entity
     */
    public LoanPrj save(LoanPrj loanPrj) {
        log.debug("Request to save LoanPrj : {}", loanPrj);
        LoanPrj result = loanPrjRepository.save(loanPrj);
        return result;
    }

    /**
     *  Get all the loanPrjs.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<LoanPrj> findAll(Pageable pageable) {
        log.debug("Request to get all LoanPrjs");
        Page<LoanPrj> result = loanPrjRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one loanPrj by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public LoanPrj findOne(Long id) {
        log.debug("Request to get LoanPrj : {}", id);
        LoanPrj loanPrj = loanPrjRepository.findOne(id);
        return loanPrj;
    }

    /**
     *  Delete the  loanPrj by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete LoanPrj : {}", id);
        loanPrjRepository.delete(id);
    }

    /**
     * 启动
     * @param id
     */
    public LoanPrj activate(Long id) {
        log.debug("Request to activate LoanPrj : {}", id);
        LoanPrj loanPrj = loanPrjRepository.findOne(id);
        if (loanPrj.isActivated() == false) {
            loanPrj.activated(true).activateDate(LocalDate.now());
        }
        loanPrjRepository.save(loanPrj);
        return loanPrj;
    }

    /**
     * 停止
     * @param id
     */
    public LoanPrj unactivate(Long id) {
        log.debug("Request to activate LoanPrj : {}", id);
        LoanPrj loanPrj = loanPrjRepository.findOne(id);
        if (loanPrj.isActivated() == true) {
            loanPrj.activated(false).activateDate(LocalDate.now());
        }
        loanPrjRepository.save(loanPrj);
        return loanPrj;
    }
}
