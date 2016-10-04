package com.liseri.anprj.repository;

import com.liseri.anprj.domain.LoanApply;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the LoanApply entity.
 */
@SuppressWarnings("unused")
public interface LoanApplyRepository extends JpaRepository<LoanApply,Long> {

}
