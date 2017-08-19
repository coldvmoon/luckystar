package com.luckystar.web.repository;

import com.luckystar.web.domain.TaskInfo;
import com.luckystar.web.domain.WorkInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TaskInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskInfoRepository extends JpaRepository<TaskInfo,Long> {
    @Query(value = "SELECT task_info.* FROM task_info, user_info, labor_union, labor_union_user WHERE task_info.user_info_id = user_info.id  AND user_info.labor_union_id = labor_union.id  AND labor_union.id = labor_union_user.labor_unions_id  AND labor_union_user.users_id = ?1 /* #pageable */",nativeQuery = true)
    Page<TaskInfo> findByUserIsCurrentUser(Long id, Pageable pageable);
}
