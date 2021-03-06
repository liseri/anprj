package com.liseri.anprj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.liseri.anprj.domain.LendApply;
import com.liseri.anprj.service.LendApplyService;
import com.liseri.anprj.web.rest.util.HeaderUtil;
import com.liseri.anprj.web.rest.util.PaginationUtil;
import com.liseri.anprj.web.rest.vm.LendApplyVM;
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
 * REST controller for managing LendApply.
 */
@RestController
@RequestMapping("/api")
public class LendApplyResource {

    private final Logger log = LoggerFactory.getLogger(LendApplyResource.class);

    @Inject
    private LendApplyService lendApplyService;

    /**
     * POST  /lend-applies : Create a new lendApply.
     *
     * @param lendApplyVM the lendApply to create
     * @return the ResponseEntity with status 201 (Created) and with body the new lendApply, or with status 400 (Bad Request) if the lendApply has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/lend-applies",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LendApply> createLendApply(@Valid @RequestBody LendApplyVM lendApplyVM) throws URISyntaxException {
        log.debug("REST request to save LendApply : {}", lendApplyVM);
        if (lendApplyVM.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("lendApply", "idexists", "A new lendApply cannot already have an ID")).body(null);
        }
        LendApply result = lendApplyService.create(lendApplyVM);
        return ResponseEntity.created(new URI("/api/lend-applies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("lendApply", result.getId().toString()))
            .body(result);
    }

    /**
     * GET  /lend-applies : get all the lendApplies.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of lendApplies in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/lend-applies",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<LendApply>> getAllLendApplies(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of LendApplies");
        Page<LendApply> page = lendApplyService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/lend-applies");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /lend-applies/:id : get the "id" lendApply.
     *
     * @param id the id of the lendApply to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the lendApply, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/lend-applies/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LendApply> getLendApply(@PathVariable Long id) {
        log.debug("REST request to get LendApply : {}", id);
        LendApply lendApply = lendApplyService.findOne(id);
        return Optional.ofNullable(lendApply)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /lend-applies/:id : delete the "id" lendApply.
     *
     * @param id the id of the lendApply to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/lend-applies/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteLendApply(@PathVariable Long id) {
        log.debug("REST request to delete LendApply : {}", id);
        lendApplyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("lendApply", id.toString())).build();
    }

    /**
     * 审核通过
     * @param id
     * @return
     */
    @GetMapping(value = "/lend-applies/{id}/auditPass")
    @Timed
    public ResponseEntity<Void> auditPass(@PathVariable Long id) {
        log.debug("REST request to auditPass LendApply : {}", id);
        lendApplyService.auditPass(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityOperationAlert("lendApply", "auditPassed", id.toString())).build();
    }
    /**
     * 审核不通过
     * @param id
     * @return
     */
    @GetMapping(value = "/lend-applies/{id}/auditReject")
    @Timed
    public ResponseEntity<Void> auditReject(@PathVariable Long id) {
        log.debug("REST request to auditReject LendApply : {}", id);
        lendApplyService.auditReject(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityOperationAlert("lendApply", "auditRejected", id.toString())).build();
    }

    /**
     * 放款
     * @param id
     * @return
     */
    @GetMapping(value = "/lend-applies/{id}/lend")
    @Timed
    public ResponseEntity<Void> lend(@PathVariable Long id) {
        log.debug("REST request to lend LendApply : {}", id);
        lendApplyService.lend(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityOperationAlert("lendApply", "loaned", id.toString())).build();
    }

    /**
     * 还款
     * @param id
     * @return
     */
    @GetMapping(value = "/lend-applies/{id}/complete")
    @Timed
    public ResponseEntity<Void> complete(@PathVariable Long id) {
        log.debug("REST request to complete LendApply : {}", id);
        lendApplyService.complete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityOperationAlert("lendApply", "completed", id.toString())).build();
    }

}
