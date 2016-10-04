package com.liseri.anprj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.liseri.anprj.domain.LoanPrj;
import com.liseri.anprj.service.LoanPrjService;
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
 * REST controller for managing LoanPrj.
 */
@RestController
@RequestMapping("/api")
public class LoanPrjResource {

    private final Logger log = LoggerFactory.getLogger(LoanPrjResource.class);
        
    @Inject
    private LoanPrjService loanPrjService;

    /**
     * POST  /loan-prjs : Create a new loanPrj.
     *
     * @param loanPrj the loanPrj to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loanPrj, or with status 400 (Bad Request) if the loanPrj has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/loan-prjs",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LoanPrj> createLoanPrj(@Valid @RequestBody LoanPrj loanPrj) throws URISyntaxException {
        log.debug("REST request to save LoanPrj : {}", loanPrj);
        if (loanPrj.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("loanPrj", "idexists", "A new loanPrj cannot already have an ID")).body(null);
        }
        LoanPrj result = loanPrjService.save(loanPrj);
        return ResponseEntity.created(new URI("/api/loan-prjs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("loanPrj", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /loan-prjs : Updates an existing loanPrj.
     *
     * @param loanPrj the loanPrj to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated loanPrj,
     * or with status 400 (Bad Request) if the loanPrj is not valid,
     * or with status 500 (Internal Server Error) if the loanPrj couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/loan-prjs",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LoanPrj> updateLoanPrj(@Valid @RequestBody LoanPrj loanPrj) throws URISyntaxException {
        log.debug("REST request to update LoanPrj : {}", loanPrj);
        if (loanPrj.getId() == null) {
            return createLoanPrj(loanPrj);
        }
        LoanPrj result = loanPrjService.save(loanPrj);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("loanPrj", loanPrj.getId().toString()))
            .body(result);
    }

    /**
     * GET  /loan-prjs : get all the loanPrjs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of loanPrjs in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/loan-prjs",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<LoanPrj>> getAllLoanPrjs(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of LoanPrjs");
        Page<LoanPrj> page = loanPrjService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/loan-prjs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /loan-prjs/:id : get the "id" loanPrj.
     *
     * @param id the id of the loanPrj to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loanPrj, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/loan-prjs/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LoanPrj> getLoanPrj(@PathVariable Long id) {
        log.debug("REST request to get LoanPrj : {}", id);
        LoanPrj loanPrj = loanPrjService.findOne(id);
        return Optional.ofNullable(loanPrj)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /loan-prjs/:id : delete the "id" loanPrj.
     *
     * @param id the id of the loanPrj to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/loan-prjs/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteLoanPrj(@PathVariable Long id) {
        log.debug("REST request to delete LoanPrj : {}", id);
        loanPrjService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("loanPrj", id.toString())).build();
    }

}
