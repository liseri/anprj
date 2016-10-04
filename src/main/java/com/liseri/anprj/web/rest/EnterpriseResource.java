package com.liseri.anprj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.liseri.anprj.domain.Enterprise;
import com.liseri.anprj.service.EnterpriseService;
import com.liseri.anprj.web.rest.util.HeaderUtil;
import com.liseri.anprj.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
 * REST controller for managing Enterprise.
 */
@RestController
@RequestMapping("/api")
public class EnterpriseResource {

    private final Logger log = LoggerFactory.getLogger(EnterpriseResource.class);
        
    @Inject
    private EnterpriseService enterpriseService;

    /**
     * POST  /enterprises : Create a new enterprise.
     *
     * @param enterprise the enterprise to create
     * @return the ResponseEntity with status 201 (Created) and with body the new enterprise, or with status 400 (Bad Request) if the enterprise has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/enterprises",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Enterprise> createEnterprise(@Valid @RequestBody Enterprise enterprise) throws URISyntaxException {
        log.debug("REST request to save Enterprise : {}", enterprise);
        if (enterprise.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("enterprise", "idexists", "A new enterprise cannot already have an ID")).body(null);
        }
        Enterprise result = enterpriseService.save(enterprise);
        return ResponseEntity.created(new URI("/api/enterprises/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("enterprise", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /enterprises : Updates an existing enterprise.
     *
     * @param enterprise the enterprise to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated enterprise,
     * or with status 400 (Bad Request) if the enterprise is not valid,
     * or with status 500 (Internal Server Error) if the enterprise couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/enterprises",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Enterprise> updateEnterprise(@Valid @RequestBody Enterprise enterprise) throws URISyntaxException {
        log.debug("REST request to update Enterprise : {}", enterprise);
        if (enterprise.getId() == null) {
            return createEnterprise(enterprise);
        }
        Enterprise result = enterpriseService.save(enterprise);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("enterprise", enterprise.getId().toString()))
            .body(result);
    }

    /**
     * GET  /enterprises : get all the enterprises.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of enterprises in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/enterprises",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Enterprise>> getAllEnterprises(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Enterprises");
        Page<Enterprise> page = enterpriseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/enterprises");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /enterprises/:id : get the "id" enterprise.
     *
     * @param id the id of the enterprise to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the enterprise, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/enterprises/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Enterprise> getEnterprise(@PathVariable Long id) {
        log.debug("REST request to get Enterprise : {}", id);
        Enterprise enterprise = enterpriseService.findOne(id);
        return Optional.ofNullable(enterprise)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /enterprises/:id : delete the "id" enterprise.
     *
     * @param id the id of the enterprise to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/enterprises/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteEnterprise(@PathVariable Long id) {
        log.debug("REST request to delete Enterprise : {}", id);
        enterpriseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("enterprise", id.toString())).build();
    }

}
