package com.liseri.anprj.repository;

import com.liseri.anprj.domain.Phone;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Phone entity.
 */
@SuppressWarnings("unused")
public interface PhoneRepository extends JpaRepository<Phone,Long> {

}
