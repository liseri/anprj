package com.liseri.anprj.service;

import com.liseri.anprj.domain.Phone;
import com.liseri.anprj.repository.PhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

/**
 * Service Implementation for managing Phone.
 */
@Service
@Transactional
public class PhoneService {

    private final Logger log = LoggerFactory.getLogger(PhoneService.class);

    @Inject
    private PhoneRepository phoneRepository;

    /**
     * 获取绑定激活码
     *
     * @param phoneNum the entity to save
     * @return the persisted entity
     */
    public void bandingApply(String login, String phoneNum) {
        log.debug("Request to Phone bandingApply");
        //给手机发送绑定验证码
    }

    /**
     * 根据验证码绑定手机号
     * @param login
     * @param phoneNum
     * @param bindingKey
     * @return
     */
    public Phone banding(String login, String phoneNum, String bindingKey) {
        log.debug("Request to Phone banding");
        //验证验证码
        Phone phone = new Phone();
        phone.login(login)
            .phone(phoneNum)
            .activated(true)
            .activateDate(LocalDate.now());
        return phoneRepository.save(phone);
    }

    /**
     *  Get all the phones.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Phone> findAll() {
        log.debug("Request to get all Phones");
        List<Phone> result = phoneRepository.findAll();

        return result;
    }

    /**
     *  Get one phone by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Phone findOne(Long id) {
        log.debug("Request to get Phone : {}", id);
        Phone phone = phoneRepository.findOne(id);
        return phone;
    }

    /**
     *  Delete the  phone by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Phone : {}", id);
        phoneRepository.delete(id);
    }


}
