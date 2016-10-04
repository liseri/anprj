package com.liseri.anprj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.liseri.anprj.domain.DrawApply;
import com.liseri.anprj.service.DrawApplyService;
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
 * REST controller for managing DrawApply.
 */
@RestController
@RequestMapping("/api")
public class DrawApplyResource {

    private final Logger log = LoggerFactory.getLogger(DrawApplyResource.class);
        
    @Inject
    private DrawApplyService drawApplyService;

    /**
     * POST  /draw-applies : Create a new drawApply.
     *
     * @param drawApply the drawApply to create
     * @return the ResponseEntity with status 201 (Created) and with body the new drawApply, or with status 400 (Bad Request) if the drawApply has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/draw-applies",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DrawApply> createDrawApply(@Valid @RequestBody DrawApply drawApply) throws URISyntaxException {
        log.debug("REST request to save DrawApply : {}", drawApply);
        if (drawApply.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("drawApply", "idexists", "A new drawApply cannot already have an ID")).body(null);
        }
        DrawApply result = drawApplyService.save(drawApply);
        return ResponseEntity.created(new URI("/api/draw-applies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("drawApply", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /draw-applies : Updates an existing drawApply.
     *
     * @param drawApply the drawApply to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated drawApply,
     * or with status 400 (Bad Request) if the drawApply is not valid,
     * or with status 500 (Internal Server Error) if the drawApply couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/draw-applies",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DrawApply> updateDrawApply(@Valid @RequestBody DrawApply drawApply) throws URISyntaxException {
        log.debug("REST request to update DrawApply : {}", drawApply);
        if (drawApply.getId() == null) {
            return createDrawApply(drawApply);
        }
        DrawApply result = drawApplyService.save(drawApply);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("drawApply", drawApply.getId().toString()))
            .body(result);
    }

    /**
     * GET  /draw-applies : get all the drawApplies.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of drawApplies in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/draw-applies",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<DrawApply>> getAllDrawApplies(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of DrawApplies");
        Page<DrawApply> page = drawApplyService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/draw-applies");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /draw-applies/:id : get the "id" drawApply.
     *
     * @param id the id of the drawApply to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the drawApply, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/draw-applies/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DrawApply> getDrawApply(@PathVariable Long id) {
        log.debug("REST request to get DrawApply : {}", id);
        DrawApply drawApply = drawApplyService.findOne(id);
        return Optional.ofNullable(drawApply)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /draw-applies/:id : delete the "id" drawApply.
     *
     * @param id the id of the drawApply to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/draw-applies/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDrawApply(@PathVariable Long id) {
        log.debug("REST request to delete DrawApply : {}", id);
        drawApplyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("drawApply", id.toString())).build();
    }

}
