package com.liseri.anprj.repository;

import com.liseri.anprj.domain.RealIdentity;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the RealIdentity entity.
 */
@SuppressWarnings("unused")
public interface RealIdentityRepository extends JpaRepository<RealIdentity,Long> {

}
