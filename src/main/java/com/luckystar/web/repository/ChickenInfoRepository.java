package com.luckystar.web.repository;

import com.luckystar.web.domain.ChickenInfo;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ChickenInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChickenInfoRepository extends JpaRepository<ChickenInfo,Long> {
    
}
