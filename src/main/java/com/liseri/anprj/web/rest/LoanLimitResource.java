package com.liseri.anprj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.liseri.anprj.domain.LoanLimit;
import com.liseri.anprj.service.LoanLimitService;
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
 * REST controller for managing LoanLimit.
 */
@RestController
@RequestMapping("/api")
public class LoanLimitResource {

    private final Logger log = LoggerFactory.getLogger(LoanLimitResource.class);
        
    @Inject
    private LoanLimitService loanLimitService;

    /**
     * POST  /loan-limits : Create a new loanLimit.
     *
     * @param loanLimit the loanLimit to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loanLimit, or with status 400 (Bad Request) if the loanLimit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/loan-limits",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LoanLimit> createLoanLimit(@Valid @RequestBody LoanLimit loanLimit) throws URISyntaxException {
        log.debug("REST request to save LoanLimit : {}", loanLimit);
        if (loanLimit.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("loanLimit", "idexists", "A new loanLimit cannot already have an ID")).body(null);
        }
        LoanLimit result = loanLimitService.save(loanLimit);
        return ResponseEntity.created(new URI("/api/loan-limits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("loanLimit", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /loan-limits : Updates an existing loanLimit.
     *
     * @param loanLimit the loanLimit to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated loanLimit,
     * or with status 400 (Bad Request) if the loanLimit is not valid,
     * or with status 500 (Internal Server Error) if the loanLimit couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/loan-limits",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LoanLimit> updateLoanLimit(@Valid @RequestBody LoanLimit loanLimit) throws URISyntaxException {
        log.debug("REST request to update LoanLimit : {}", loanLimit);
        if (loanLimit.getId() == null) {
            return createLoanLimit(loanLimit);
        }
        LoanLimit result = loanLimitService.save(loanLimit);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("loanLimit", loanLimit.getId().toString()))
            .body(result);
    }

    /**
     * GET  /loan-limits : get all the loanLimits.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of loanLimits in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/loan-limits",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<LoanLimit>> getAllLoanLimits(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of LoanLimits");
        Page<LoanLimit> page = loanLimitService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/loan-limits");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /loan-limits/:id : get the "id" loanLimit.
     *
     * @param id the id of the loanLimit to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loanLimit, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/loan-limits/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LoanLimit> getLoanLimit(@PathVariable Long id) {
        log.debug("REST request to get LoanLimit : {}", id);
        LoanLimit loanLimit = loanLimitService.findOne(id);
        return Optional.ofNullable(loanLimit)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /loan-limits/:id : delete the "id" loanLimit.
     *
     * @param id the id of the loanLimit to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/loan-limits/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteLoanLimit(@PathVariable Long id) {
        log.debug("REST request to delete LoanLimit : {}", id);
        loanLimitService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("loanLimit", id.toString())).build();
    }

}
