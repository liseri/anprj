package com.liseri.anprj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.liseri.anprj.domain.LoanApply;
import com.liseri.anprj.service.LoanApplyService;
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
 * REST controller for managing LoanApply.
 */
@RestController
@RequestMapping("/api")
public class LoanApplyResource {

    private final Logger log = LoggerFactory.getLogger(LoanApplyResource.class);

    @Inject
    private LoanApplyService loanApplyService;

    /**
     * POST  /loan-applies : Create a new loanApply.
     *
     * @param loanApply the loanApply to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loanApply, or with status 400 (Bad Request) if the loanApply has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/loan-applies",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LoanApply> createLoanApply(@Valid @RequestBody LoanApply loanApply) throws URISyntaxException {
        log.debug("REST request to save LoanApply : {}", loanApply);
        if (loanApply.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("loanApply", "idexists", "A new loanApply cannot already have an ID")).body(null);
        }
        LoanApply result = loanApplyService.create(loanApply);
        return ResponseEntity.created(new URI("/api/loan-applies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("loanApply", result.getId().toString()))
            .body(result);
    }


    /**
     * GET  /loan-applies : get all the loanApplies.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of loanApplies in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/loan-applies",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<LoanApply>> getAllLoanApplies(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of LoanApplies");
        Page<LoanApply> page = loanApplyService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/loan-applies");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /loan-applies/:id : get the "id" loanApply.
     *
     * @param id the id of the loanApply to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loanApply, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/loan-applies/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LoanApply> getLoanApply(@PathVariable Long id) {
        log.debug("REST request to get LoanApply : {}", id);
        LoanApply loanApply = loanApplyService.findOne(id);
        return Optional.ofNullable(loanApply)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /loan-applies/:id : delete the "id" loanApply.
     *
     * @param id the id of the loanApply to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/loan-applies/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteLoanApply(@PathVariable Long id) {
        log.debug("REST request to delete LoanApply : {}", id);
        loanApplyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("loanApply", id.toString())).build();
    }

}
