package com.liseri.anprj.service;

import com.liseri.anprj.domain.LendApply;
import com.liseri.anprj.domain.enumeration.LendApplyStatu;
import com.liseri.anprj.repository.LendApplyRepository;
import com.liseri.anprj.web.rest.vm.LendApplyVM;
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
 * Service Implementation for managing LendApply.
 */
@Service
@Transactional
public class LendApplyService {

    private final Logger log = LoggerFactory.getLogger(LendApplyService.class);

    @Inject
    private LendApplyRepository lendApplyRepository;
    @Inject
    private UserService userService;
    /**
     * Save a lendApply.
     *
     * @param lendApplyVM the entity to save
     * @return the persisted entity
     */
    public LendApply create(LendApplyVM lendApplyVM) {
        log.debug("Request to save LendPrj : {}", lendApplyVM);
        LendApply lendApply = lendApplyVM.newLendApply(userService.getUserWithAuthorities().getLogin());
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

    /**
     * 审核通过
     * @param id
     */
    public void auditPass(Long id) {
        log.debug("Request to auditPass LendApply : {}", id);
        LendApply lendApply = lendApplyRepository.findOne(id);
        lendApply.lendStatu(LendApplyStatu.PASSED).auditDate(LocalDate.now());
    }

    /**
     * 审核未通过
     * @param id
     */
    public void auditReject(Long id) {
        log.debug("Request to auditReject LendApply : {}", id);
        LendApply lendApply = lendApplyRepository.findOne(id);
        lendApply.lendStatu(LendApplyStatu.REJECTED).auditDate(LocalDate.now());
    }

    /**
     * 放款完成
     * @param id
     */
    public void lend(Long id) {
        log.debug("Request to loan LendApply : {}", id);
        LendApply lendApply = lendApplyRepository.findOne(id);
        lendApply.lendStatu(LendApplyStatu.LENDED).startDate(LocalDate.now());
    }

    /**
     * 还完借款，本次借款完成
     * @param id
     */
    public void complete(Long id) {
        log.debug("Request to complete LendApply : {}", id);
        LendApply lendApply = lendApplyRepository.findOne(id);
        lendApply.lendStatu(LendApplyStatu.COMPLETED).completeDate(LocalDate.now());
    }
}
