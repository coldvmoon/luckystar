package com.luckystar.web.repository;

import com.luckystar.web.domain.WorkInfo;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the WorkInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkInfoRepository extends JpaRepository<WorkInfo,Long> {
    
}
