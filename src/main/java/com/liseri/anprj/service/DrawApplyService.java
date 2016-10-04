package com.liseri.anprj.service;

import com.liseri.anprj.domain.DrawApply;
import com.liseri.anprj.repository.DrawApplyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing DrawApply.
 */
@Service
@Transactional
public class DrawApplyService {

    private final Logger log = LoggerFactory.getLogger(DrawApplyService.class);
    
    @Inject
    private DrawApplyRepository drawApplyRepository;

    /**
     * Save a drawApply.
     *
     * @param drawApply the entity to save
     * @return the persisted entity
     */
    public DrawApply save(DrawApply drawApply) {
        log.debug("Request to save DrawApply : {}", drawApply);
        DrawApply result = drawApplyRepository.save(drawApply);
        return result;
    }

    /**
     *  Get all the drawApplies.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<DrawApply> findAll(Pageable pageable) {
        log.debug("Request to get all DrawApplies");
        Page<DrawApply> result = drawApplyRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one drawApply by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public DrawApply findOne(Long id) {
        log.debug("Request to get DrawApply : {}", id);
        DrawApply drawApply = drawApplyRepository.findOne(id);
        return drawApply;
    }

    /**
     *  Delete the  drawApply by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete DrawApply : {}", id);
        drawApplyRepository.delete(id);
    }
}
