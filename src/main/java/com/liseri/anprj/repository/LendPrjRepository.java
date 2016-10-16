package com.liseri.anprj.repository;

import com.liseri.anprj.domain.LendPrj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the LendPrj entity.
 */
@SuppressWarnings("unused")
public interface LendPrjRepository extends JpaRepository<LendPrj,Long> {
    Page<LendPrj> findByActivated(boolean activated, Pageable pageable);
}
