package com.liseri.anprj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.liseri.anprj.domain.RealIdentity;
import com.liseri.anprj.service.RealIdentityService;
import com.liseri.anprj.web.rest.util.HeaderUtil;
import com.liseri.anprj.web.rest.util.PaginationUtil;
import com.liseri.anprj.web.rest.vm.RealIdentityVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing RealIdentity.
 */
@RestController
@RequestMapping("/api")
public class RealIdentityResource {
    public static final String IDENTITY_PATH = "identity-dir";

    private final Logger log = LoggerFactory.getLogger(RealIdentityResource.class);

    @Inject
    private RealIdentityService realIdentityService;


    @RequestMapping(value = "/real-identities/upload",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> uploadFile(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                Path path = Paths.get(IDENTITY_PATH, file.getOriginalFilename());
                Files.copy(file.getInputStream(), path);
                return ResponseEntity.ok().headers(HeaderUtil.createUploadResultHead("success",path.toString())).build();
            } catch (IOException | RuntimeException e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().headers(HeaderUtil.createUploadResultHead("error", e.getMessage())).build();
            }
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * POST  /real-identities : Create a new realIdentity.
     *
     * @param realIdentityVM the realIdentity to create
     * @return the ResponseEntity with status 201 (Created) and with body the new realIdentity, or with status 400 (Bad Request) if the realIdentity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/real-identities/bind",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> binding(@Valid @RequestBody RealIdentityVM realIdentityVM) throws URISyntaxException {
        log.debug("REST request to createRealIdentity");

        RealIdentity result = realIdentityService.binding(realIdentityVM.getLogin(), realIdentityVM.getName(),
            realIdentityVM.getGenderType(), realIdentityVM.getIdentityCard(), realIdentityVM.getPicPath());
        return ResponseEntity.ok().build();
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
