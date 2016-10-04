package com.liseri.anprj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.liseri.anprj.domain.LendPrj;
import com.liseri.anprj.service.LendPrjService;
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
 * REST controller for managing LendPrj.
 */
@RestController
@RequestMapping("/api")
public class LendPrjResource {

    private final Logger log = LoggerFactory.getLogger(LendPrjResource.class);
        
    @Inject
    private LendPrjService lendPrjService;

    /**
     * POST  /lend-prjs : Create a new lendPrj.
     *
     * @param lendPrj the lendPrj to create
     * @return the ResponseEntity with status 201 (Created) and with body the new lendPrj, or with status 400 (Bad Request) if the lendPrj has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/lend-prjs",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LendPrj> createLendPrj(@Valid @RequestBody LendPrj lendPrj) throws URISyntaxException {
        log.debug("REST request to save LendPrj : {}", lendPrj);
        if (lendPrj.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("lendPrj", "idexists", "A new lendPrj cannot already have an ID")).body(null);
        }
        LendPrj result = lendPrjService.save(lendPrj);
        return ResponseEntity.created(new URI("/api/lend-prjs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("lendPrj", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /lend-prjs : Updates an existing lendPrj.
     *
     * @param lendPrj the lendPrj to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated lendPrj,
     * or with status 400 (Bad Request) if the lendPrj is not valid,
     * or with status 500 (Internal Server Error) if the lendPrj couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/lend-prjs",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LendPrj> updateLendPrj(@Valid @RequestBody LendPrj lendPrj) throws URISyntaxException {
        log.debug("REST request to update LendPrj : {}", lendPrj);
        if (lendPrj.getId() == null) {
            return createLendPrj(lendPrj);
        }
        LendPrj result = lendPrjService.save(lendPrj);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("lendPrj", lendPrj.getId().toString()))
            .body(result);
    }

    /**
     * GET  /lend-prjs : get all the lendPrjs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of lendPrjs in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/lend-prjs",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<LendPrj>> getAllLendPrjs(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of LendPrjs");
        Page<LendPrj> page = lendPrjService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/lend-prjs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /lend-prjs/:id : get the "id" lendPrj.
     *
     * @param id the id of the lendPrj to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the lendPrj, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/lend-prjs/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LendPrj> getLendPrj(@PathVariable Long id) {
        log.debug("REST request to get LendPrj : {}", id);
        LendPrj lendPrj = lendPrjService.findOne(id);
        return Optional.ofNullable(lendPrj)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /lend-prjs/:id : delete the "id" lendPrj.
     *
     * @param id the id of the lendPrj to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/lend-prjs/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteLendPrj(@PathVariable Long id) {
        log.debug("REST request to delete LendPrj : {}", id);
        lendPrjService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("lendPrj", id.toString())).build();
    }

}
