package com.luckystar.web.repository;

import com.luckystar.web.domain.TaskInfo;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TaskInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskInfoRepository extends JpaRepository<TaskInfo,Long> {
    
}
