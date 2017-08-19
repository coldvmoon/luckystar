package com.luckystar.web.repository;

import com.luckystar.web.domain.UserInfoBoard;
import com.luckystar.web.domain.WorkTimeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by coldvmoon on 16/08/2017.
 */
@SuppressWarnings("unused")
@Repository

public interface WorkTimeBoardRepository  extends JpaRepository<WorkTimeBoard, Long> {
@Query(value = "SELECT * from (SELECT wi.id, ci.user_name, ci.nick_name, ci.star_id, (SELECT    SUM(wi2.work_time)  "
		+ "FROM work_info wi2  WHERE ti.cur_month = wi2.cur_month      AND wi2.star_id = wi.star_id) AS worktime_by_month, "
		+ "(SELECT    CONCAT(SUM(IF(work_time >= 14400, 1, 0)),'/',SUM(IF(work_time < 14400 AND work_time > 0, 1, 0)),'/',SUM(IF(work_time = 0, 1, 0)))  "
		+ "FROM work_info wi2  WHERE ti.cur_month = wi2.cur_month      AND wi2.star_id = wi.star_id) AS judge_by_month,    "
		+ "wi.work_time, wi.cur_day FROM labor_union lu, user_info ci, task_info ti, work_info wi WHERE lu.id = ci.labor_union_id   "
		+ "AND ci.star_id = wi.star_id   AND wi.task_info_id = ti.id   AND lu.l_id = ?1   AND wi.cur_month = ?2   "
		+ "AND (ci.user_name LIKE ?3   OR ci.nick_name LIKE ?3   OR ci.star_id LIKE ?3   OR ci.phone_number LIKE ?3   OR ci.qq LIKE ?3   "
		+ "OR ci.wei_chat LIKE ?3)) wi2 /* #pageable */",
    countQuery = "SELECT COUNT(*) FROM labor_union lu, user_info ci, task_info ti, work_info wi WHERE lu.id = ci.labor_union_id   "
		+ "AND ci.star_id = wi.star_id   AND wi.task_info_id = ti.id   AND lu.l_id = ?1   AND wi.cur_month = ?2   "
		+ "AND (ci.user_name LIKE ?3   OR ci.nick_name LIKE ?3   OR ci.star_id LIKE ?3   OR ci.phone_number LIKE ?3   OR ci.qq LIKE ?3   "
		+ "OR ci.wei_chat LIKE ?3)",nativeQuery = true)
Page<WorkTimeBoard> getWorkTimeBoardCurMonth(Long laborUnionId, Long days, String searchCondition, Pageable pageable);

@Query(value = "SELECT * from (SELECT wi.id, ci.user_name, ci.nick_name, ci.star_id, (SELECT    SUM(wi2.work_time)  "
		+ "FROM work_info wi2  WHERE ti.cur_month = wi2.cur_month      AND wi2.star_id = wi.star_id) AS worktime_by_month, "
		+ "(SELECT    CONCAT(SUM(IF(work_time >= 14400, 1, 0)),'/',SUM(IF(work_time < 14400 AND work_time > 0, 1, 0)),'/',SUM(IF(work_time = 0, 1, 0)))  "
		+ "FROM work_info wi2  WHERE ti.cur_month = wi2.cur_month      AND wi2.star_id = wi.star_id) AS judge_by_month,    "
		+ "wi.work_time, wi.cur_day FROM labor_union lu, user_info ci, task_info ti, work_info wi WHERE lu.id = ci.labor_union_id   "
		+ "AND ci.star_id = wi.star_id   AND wi.task_info_id = ti.id   AND lu.l_id = ?1   AND wi.cur_day IN ?2   "
		+ "AND (ci.user_name LIKE ?3   OR ci.nick_name LIKE ?3   OR ci.star_id LIKE ?3   OR ci.phone_number LIKE ?3   OR ci.qq LIKE ?3   "
		+ "OR ci.wei_chat LIKE ?3)) wi2 /* #pageable */",
    countQuery = "SELECT COUNT(*) FROM labor_union lu, user_info ci, task_info ti, work_info wi WHERE lu.id = ci.labor_union_id   "
		+ "AND ci.star_id = wi.star_id   AND wi.task_info_id = ti.id   AND lu.l_id = ?1   AND wi.cur_day IN ?2   "
		+ "AND (ci.user_name LIKE ?3   OR ci.nick_name LIKE ?3   OR ci.star_id LIKE ?3   OR ci.phone_number LIKE ?3   OR ci.qq LIKE ?3   "
		+ "OR ci.wei_chat LIKE ?3)",nativeQuery = true)
    Page<WorkTimeBoard> getWorkTimeBoardByDay(Long laborUnionId, List<String> days, String searchCondition, Pageable pageable);
}
