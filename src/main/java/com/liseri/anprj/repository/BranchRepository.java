package com.liseri.anprj.repository;

import com.liseri.anprj.domain.Branch;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Branch entity.
 */
@SuppressWarnings("unused")
public interface BranchRepository extends JpaRepository<Branch,Long> {

}
