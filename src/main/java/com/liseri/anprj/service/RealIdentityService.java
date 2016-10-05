package com.liseri.anprj.service;

import com.liseri.anprj.domain.RealIdentity;
import com.liseri.anprj.domain.enumeration.GenderType;
import com.liseri.anprj.repository.RealIdentityRepository;
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
 * Service Implementation for managing RealIdentity.
 */
@Service
@Transactional
public class RealIdentityService {

    private final Logger log = LoggerFactory.getLogger(RealIdentityService.class);

    @Inject
    private RealIdentityRepository realIdentityRepository;


    /**
     * 绑定身份证信息
     * @param login
     * @param identityCard
     * @param gender
     * @return
     */
    public RealIdentity binding(String login, String name,  GenderType gender, String identityCard) {
        log.debug("Request to save RealIdentity");
        RealIdentity realIdentity = new RealIdentity();
        realIdentity.login(login)
            .name(name)
            .gender(gender)
            .identityNumber(identityCard)
            .activated(false);
        RealIdentity result = realIdentityRepository.save(realIdentity);
        return result;
    }

    /**
     * 绑定审核通过
     * @param id
     */
    public void auditPass(Long id) {
        log.debug("Request to delete RealIdentity : {}", id);
        RealIdentity realIdentity = realIdentityRepository.findOne(id);
        realIdentity.activated(true)
            .activateDate(LocalDate.now());
        realIdentityRepository.save(realIdentity);
    }

    /**
     * 绑定审核不通过
     * @param id
     */
    public void auditReject(Long id) {
        log.debug("Request to delete RealIdentity : {}", id);
        RealIdentity realIdentity = realIdentityRepository.findOne(id);
        realIdentity.activated(false)
            .activateDate(LocalDate.now());
        realIdentityRepository.save(realIdentity);
    }

    /**
     *  Get all the realIdentities.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<RealIdentity> findAll(Pageable pageable) {
        log.debug("Request to get all RealIdentities");
        Page<RealIdentity> result = realIdentityRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one realIdentity by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public RealIdentity findOne(Long id) {
        log.debug("Request to get RealIdentity : {}", id);
        RealIdentity realIdentity = realIdentityRepository.findOne(id);
        return realIdentity;
    }

    /**
     *  Delete the  realIdentity by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete RealIdentity : {}", id);
        realIdentityRepository.delete(id);
    }
}
