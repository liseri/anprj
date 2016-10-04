package com.liseri.anprj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.liseri.anprj.domain.RealIdentity;
import com.liseri.anprj.service.RealIdentityService;
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
 * REST controller for managing RealIdentity.
 */
@RestController
@RequestMapping("/api")
public class RealIdentityResource {

    private final Logger log = LoggerFactory.getLogger(RealIdentityResource.class);
        
    @Inject
    private RealIdentityService realIdentityService;

    /**
     * POST  /real-identities : Create a new realIdentity.
     *
     * @param realIdentity the realIdentity to create
     * @return the ResponseEntity with status 201 (Created) and with body the new realIdentity, or with status 400 (Bad Request) if the realIdentity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/real-identities",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RealIdentity> createRealIdentity(@Valid @RequestBody RealIdentity realIdentity) throws URISyntaxException {
        log.debug("REST request to save RealIdentity : {}", realIdentity);
        if (realIdentity.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("realIdentity", "idexists", "A new realIdentity cannot already have an ID")).body(null);
        }
        RealIdentity result = realIdentityService.save(realIdentity);
        return ResponseEntity.created(new URI("/api/real-identities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("realIdentity", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /real-identities : Updates an existing realIdentity.
     *
     * @param realIdentity the realIdentity to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated realIdentity,
     * or with status 400 (Bad Request) if the realIdentity is not valid,
     * or with status 500 (Internal Server Error) if the realIdentity couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/real-identities",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RealIdentity> updateRealIdentity(@Valid @RequestBody RealIdentity realIdentity) throws URISyntaxException {
        log.debug("REST request to update RealIdentity : {}", realIdentity);
        if (realIdentity.getId() == null) {
            return createRealIdentity(realIdentity);
        }
        RealIdentity result = realIdentityService.save(realIdentity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("realIdentity", realIdentity.getId().toString()))
            .body(result);
    }

    /**
     * GET  /real-identities : get all the realIdentities.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of realIdentities in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/real-identities",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<RealIdentity>> getAllRealIdentities(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of RealIdentities");
        Page<RealIdentity> page = realIdentityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/real-identities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /real-identities/:id : get the "id" realIdentity.
     *
     * @param id the id of the realIdentity to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the realIdentity, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/real-identities/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RealIdentity> getRealIdentity(@PathVariable Long id) {
        log.debug("REST request to get RealIdentity : {}", id);
        RealIdentity realIdentity = realIdentityService.findOne(id);
        return Optional.ofNullable(realIdentity)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /real-identities/:id : delete the "id" realIdentity.
     *
     * @param id the id of the realIdentity to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/real-identities/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteRealIdentity(@PathVariable Long id) {
        log.debug("REST request to delete RealIdentity : {}", id);
        realIdentityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("realIdentity", id.toString())).build();
    }

}
