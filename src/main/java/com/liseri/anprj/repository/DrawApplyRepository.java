package com.liseri.anprj.repository;

import com.liseri.anprj.domain.DrawApply;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the DrawApply entity.
 */
@SuppressWarnings("unused")
public interface DrawApplyRepository extends JpaRepository<DrawApply,Long> {

}
