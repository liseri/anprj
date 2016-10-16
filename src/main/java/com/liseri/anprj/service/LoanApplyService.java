package com.liseri.anprj.service;

import com.liseri.anprj.domain.LoanApply;
import com.liseri.anprj.domain.enumeration.LoanApplyStatu;
import com.liseri.anprj.repository.LoanApplyRepository;
import com.liseri.anprj.web.rest.vm.LoanApplyVM;
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
 * Service Implementation for managing LoanApply.
 */
@Service
@Transactional
public class LoanApplyService {

    private final Logger log = LoggerFactory.getLogger(LoanApplyService.class);

    @Inject
    private LoanApplyRepository loanApplyRepository;
    @Inject
    private UserService userService;
    /**
     * 申请创建
     *
     * @param loanApplyVM the entity to save
     * @return the persisted entity
     */
    public LoanApply create(LoanApplyVM loanApplyVM) {
        log.debug("Request to save LoanApply : {}", loanApplyVM);
        LoanApply loanApply = loanApplyVM.newLoanApply(userService.getUserWithAuthorities().getLogin());
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

    /**
     * 审核通过
     * @param id
     */
    public void auditPass(Long id) {
        log.debug("Request to auditPass LoanApply : {}", id);
        LoanApply loanApply = loanApplyRepository.findOne(id);
        loanApply.applyStatu(LoanApplyStatu.PASSED).auditDate(LocalDate.now());
    }

    /**
     * 审核未通过
     * @param id
     */
    public void auditReject(Long id) {
        log.debug("Request to auditReject LoanApply : {}", id);
        LoanApply loanApply = loanApplyRepository.findOne(id);
        loanApply.applyStatu(LoanApplyStatu.REJECTED).auditDate(LocalDate.now());
    }

    /**
     * 放款完成
     * @param id
     */
    public void loan(Long id) {
        log.debug("Request to loan LoanApply : {}", id);
        LoanApply loanApply = loanApplyRepository.findOne(id);
        loanApply.applyStatu(LoanApplyStatu.LOANED).loanDate(LocalDate.now());
    }

    /**
     * 还完借款，本次借款完成
     * @param id
     */
    public void complete(Long id) {
        log.debug("Request to complete LoanApply : {}", id);
        LoanApply loanApply = loanApplyRepository.findOne(id);
        loanApply.applyStatu(LoanApplyStatu.COMPLETED).completeDate(LocalDate.now());
    }
}
