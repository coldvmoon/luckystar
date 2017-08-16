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
@Query(value = "select * FROM (SELECT ci.id,ci.user_name,  ci.nick_name,  ci.star_id,  (SELECT     SUM(work_time) AS all_time   FROM work_info wi2   WHERE ti.cur_month = wi2.cur_month       AND wi2.star_id = wi.star_id) AS total_time,  (SELECT     SUM(IF(work_time > 14400, 1, 0.5)) AS bean   FROM work_info wi2   WHERE ti.cur_month = wi2.cur_month       AND wi2.star_id = wi.star_id) AS total_day,  wi.work_time,  wi.cur_day FROM labor_union lu,  user_info ci,  task_info ti,  work_info wi WHERE lu.id  = ci.labor_union_id    AND ci.star_id = wi.star_id    AND wi.task_info_id = ti.id   "+
    "  AND lu.l_id = ?1  AND wi.cur_month = ?2 AND ci.user_name LIKE ?3 AND ci.nick_name LIKE ?4 AND ci.star_id LIKE ?5 AND ci.phone_number LIKE ?6 AND ci.qq LIKE ?7 AND ci.wei_chat like ?8 ) wi2 /* #pageable */", nativeQuery = true)
Page<WorkTimeBoard> getWorkTimeBoardCurMonth(Long laborUnionId, Long days, String userName, String nickName, String starId, String phoneNumber, String qq, String weiChar, Pageable pageable);

@Query(value = "select * FROM (SELECT ci.id,ci.user_name,  ci.nick_name,  ci.star_id,  (SELECT     SUM(work_time) AS all_time   FROM work_info wi2   WHERE ti.cur_month = wi2.cur_month       AND wi2.star_id = wi.star_id) AS total_time,  (SELECT     SUM(IF(work_time > 14400, 1, 0.5)) AS bean   FROM work_info wi2   WHERE ti.cur_month = wi2.cur_month       AND wi2.star_id = wi.star_id) AS total_day,  wi.work_time,  wi.cur_day FROM labor_union lu,  user_info ci,  task_info ti,  work_info wi WHERE lu.id  = ci.labor_union_id    AND ci.star_id = wi.star_id    AND wi.task_info_id = ti.id      "+
    "  AND lu.l_id = ?1  AND wi.cur_day IN ?2 AND ci.user_name LIKE ?3 AND ci.nick_name LIKE ?4 AND ci.star_id LIKE ?5 AND ci.phone_number LIKE ?6 AND ci.qq LIKE ?7 AND ci.wei_chat like ?8 ) wi2 /* #pageable */", nativeQuery = true)
    Page<WorkTimeBoard> getWorkTimeBoardByDay(Long laborUnionId, List<String> days, String userName, String nickName, String starId, String phoneNumber, String qq, String weiChar, Pageable pageable);
}
