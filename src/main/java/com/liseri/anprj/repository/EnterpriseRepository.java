package com.liseri.anprj.repository;

import com.liseri.anprj.domain.Enterprise;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Enterprise entity.
 */
@SuppressWarnings("unused")
public interface EnterpriseRepository extends JpaRepository<Enterprise,Long> {

}
