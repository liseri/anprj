package com.liseri.anprj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.liseri.anprj.domain.Phone;
import com.liseri.anprj.service.PhoneService;
import com.liseri.anprj.web.rest.util.HeaderUtil;
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
    private PhoneService phoneService;

    /**
     * POST  /phones : Create a new phone.
     *
     * @param phone the phone to create
     * @return the ResponseEntity with status 201 (Created) and with body the new phone, or with status 400 (Bad Request) if the phone has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/phones",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Phone> createPhone(@Valid @RequestBody Phone phone) throws URISyntaxException {
        log.debug("REST request to save Phone : {}", phone);
        if (phone.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("phone", "idexists", "A new phone cannot already have an ID")).body(null);
        }
        Phone result = phoneService.save(phone);
        return ResponseEntity.created(new URI("/api/phones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("phone", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /phones : Updates an existing phone.
     *
     * @param phone the phone to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated phone,
     * or with status 400 (Bad Request) if the phone is not valid,
     * or with status 500 (Internal Server Error) if the phone couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/phones",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Phone> updatePhone(@Valid @RequestBody Phone phone) throws URISyntaxException {
        log.debug("REST request to update Phone : {}", phone);
        if (phone.getId() == null) {
            return createPhone(phone);
        }
        Phone result = phoneService.save(phone);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("phone", phone.getId().toString()))
            .body(result);
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
