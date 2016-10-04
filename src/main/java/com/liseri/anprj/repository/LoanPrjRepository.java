package com.liseri.anprj.repository;

import com.liseri.anprj.domain.LoanPrj;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the LoanPrj entity.
 */
@SuppressWarnings("unused")
public interface LoanPrjRepository extends JpaRepository<LoanPrj,Long> {

}
