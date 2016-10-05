package com.liseri.anprj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.liseri.anprj.domain.Phone;
import com.liseri.anprj.repository.PhoneRepository;
import com.liseri.anprj.service.PhoneService;
import com.liseri.anprj.web.rest.util.HeaderUtil;
import com.liseri.anprj.web.rest.vm.PhoneVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Phone.
 */
@RestController
@RequestMapping("/api")
public class PhoneResource {

    private final Logger log = LoggerFactory.getLogger(PhoneResource.class);

    @Inject
    private PhoneRepository phoneRepository;
    @Inject
    private PhoneService phoneService;

    /**
     * 请求验证码
     *
     * @param phoneVM the phoneVM to create
     * @return the ResponseEntity with status 201 (Created) and with body the new phone, or with status 400 (Bad Request) if the phone has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/phones/apply",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> bindingApply(@Valid @RequestBody PhoneVM phoneVM) throws URISyntaxException {
        log.debug("REST request to save Phone : {}", phoneVM);
        phoneService.bandingApply(phoneVM.getLogin(), phoneVM.getPhone());
        return ResponseEntity.ok().build();
    }

    /**
     * 绑定验证码
     *
     * @param phoneVM the phoneVM to create
     * @return the ResponseEntity with status 201 (Created) and with body the new phone, or with status 400 (Bad Request) if the phone has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/phones/bind",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> binding(@Valid @RequestBody PhoneVM phoneVM) throws URISyntaxException {
        log.debug("REST request to save Phone : {}", phoneVM);
        Phone phone = phoneRepository.findByLogin(phoneVM.getLogin());
        if (phone == null || !phone.getPhone().equalsIgnoreCase(phoneVM.getLogin()) || !phone.getKey().equalsIgnoreCase(phoneVM.getKey()))
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("phone", "invalidkey", "invalidkey")).body(null);
        phoneService.banding(phone);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityCreationAlert("phone", "")).build();
    }

    /**
     * GET  /phones : get all the phones.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of phones in body
     */
    @RequestMapping(value = "/phones",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Phone> getAllPhones() {
        log.debug("REST request to get all Phones");
        return phoneService.findAll();
    }

    /**
     * GET  /phones/:id : get the "id" phone.
     *
     * @param id the id of the phone to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the phone, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/phones/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Phone> getPhone(@PathVariable Long id) {
        log.debug("REST request to get Phone : {}", id);
        Phone phone = phoneService.findOne(id);
        return Optional.ofNullable(phone)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /phones/:id : delete the "id" phone.
     *
     * @param id the id of the phone to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/phones/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deletePhone(@PathVariable Long id) {
        log.debug("REST request to delete Phone : {}", id);
        phoneService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("phone", id.toString())).build();
    }

}
