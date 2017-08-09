package com.luckystar.web.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.luckystar.web.domain.ChickenInfo;

import com.luckystar.web.repository.ChickenInfoRepository;
import com.luckystar.web.web.rest.util.HeaderUtil;
import com.luckystar.web.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ChickenInfo.
 */
@RestController
@RequestMapping("/api")
public class ChickenInfoResource {

    private final Logger log = LoggerFactory.getLogger(ChickenInfoResource.class);

    private static final String ENTITY_NAME = "chickenInfo";

    private final ChickenInfoRepository chickenInfoRepository;

    public ChickenInfoResource(ChickenInfoRepository chickenInfoRepository) {
        this.chickenInfoRepository = chickenInfoRepository;
    }

    /**
     * POST  /chicken-infos : Create a new chickenInfo.
     *
     * @param chickenInfo the chickenInfo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new chickenInfo, or with status 400 (Bad Request) if the chickenInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/chicken-infos")
    @Timed
    public ResponseEntity<ChickenInfo> createChickenInfo(@Valid @RequestBody ChickenInfo chickenInfo) throws URISyntaxException {
        log.debug("REST request to save ChickenInfo : {}", chickenInfo);
        if (chickenInfo.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new chickenInfo cannot already have an ID")).body(null);
        }
        ChickenInfo result = chickenInfoRepository.save(chickenInfo);
        return ResponseEntity.created(new URI("/api/chicken-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /chicken-infos : Updates an existing chickenInfo.
     *
     * @param chickenInfo the chickenInfo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated chickenInfo,
     * or with status 400 (Bad Request) if the chickenInfo is not valid,
     * or with status 500 (Internal Server Error) if the chickenInfo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/chicken-infos")
    @Timed
    public ResponseEntity<ChickenInfo> updateChickenInfo(@Valid @RequestBody ChickenInfo chickenInfo) throws URISyntaxException {
        log.debug("REST request to update ChickenInfo : {}", chickenInfo);
        if (chickenInfo.getId() == null) {
            return createChickenInfo(chickenInfo);
        }
        ChickenInfo result = chickenInfoRepository.save(chickenInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, chickenInfo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /chicken-infos : get all the chickenInfos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of chickenInfos in body
     */
    @GetMapping("/chicken-infos")
    @Timed
    public ResponseEntity<List<ChickenInfo>> getAllChickenInfos(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of ChickenInfos");
        Page<ChickenInfo> page = chickenInfoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/chicken-infos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /chicken-infos/:id : get the "id" chickenInfo.
     *
     * @param id the id of the chickenInfo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the chickenInfo, or with status 404 (Not Found)
     */
    @GetMapping("/chicken-infos/{id}")
    @Timed
    public ResponseEntity<ChickenInfo> getChickenInfo(@PathVariable Long id) {
        log.debug("REST request to get ChickenInfo : {}", id);
        ChickenInfo chickenInfo = chickenInfoRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(chickenInfo));
    }

    /**
     * DELETE  /chicken-infos/:id : delete the "id" chickenInfo.
     *
     * @param id the id of the chickenInfo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/chicken-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteChickenInfo(@PathVariable Long id) {
        log.debug("REST request to delete ChickenInfo : {}", id);
        chickenInfoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
