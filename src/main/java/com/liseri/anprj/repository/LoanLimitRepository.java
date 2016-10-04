package com.liseri.anprj.repository;

import com.liseri.anprj.domain.LoanLimit;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the LoanLimit entity.
 */
@SuppressWarnings("unused")
public interface LoanLimitRepository extends JpaRepository<LoanLimit,Long> {

}
