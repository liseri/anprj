package com.liseri.anprj.repository;

import com.liseri.anprj.domain.LendApply;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the LendApply entity.
 */
@SuppressWarnings("unused")
public interface LendApplyRepository extends JpaRepository<LendApply,Long> {

}
