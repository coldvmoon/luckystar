package com.luckystar.web.repository;

import com.luckystar.web.domain.LaborUnion;
import com.luckystar.web.domain.UserInfoBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by coldvmoon on 13/08/2017.
 */
@SuppressWarnings("unused")
@Repository
public interface UserInfoBoardRepository extends JpaRepository<UserInfoBoard, Long> {

    @Query(value = "select * FROM (SELECT ci.id  as id,  ci.user_name,  ci.nick_name,  ci.star_id,  (SELECT     SUM(work_time) AS all_time   FROM work_info wi2   WHERE ti.cur_month = wi2.cur_month       AND wi2.star_id = wi.star_id) AS worktime_by_month1,  (SELECT     SUM(IF(work_time > 14400, 1, 0.5)) AS bean   FROM work_info wi2   WHERE ti.cur_month = wi2.cur_month       AND wi2.star_id = wi.star_id) AS worktime_by_month,  wi.work_time,  wi.cur_day FROM labor_union lu,  user_info ci ,  work_info wi,  task_info ti WHERE lu.id  = ci.labor_union_id    AND ci.star_id = wi.star_id    AND wi.task_info_id = ti.id     AND lu.l_id = '5544'    AND wi.cur_month = 201708 ) wi2 /* #pageable */", nativeQuery = true)
    Page<UserInfoBoard> find(Pageable pageable);

    @Query(value = "select * FROM (SELECT ci.id,  ci.user_name,  ci.nick_name,  ci.star_id,  wi.star_level,wi.star_name,  wi.rich_level,wi.rich_name,  wi.fans_count,  (wi.bean_total - wi.fisrt_bean) AS bean_by_day,  ti.min_task,  ti.max_task,  ci.reg_date,  (SELECT     SUM(wi2.bean_total - wi2.fisrt_bean)   FROM work_info wi2   WHERE ti.cur_month = wi2.cur_month       AND wi2.star_id = wi.star_id) AS bean_by_month,  (SELECT     SUM(wi2.work_time) AS bean   FROM work_info wi2   WHERE ti.cur_month = wi2.cur_month       AND wi2.star_id = wi.star_id) AS worktime_by_month1,  (SELECT     SUM(IF(work_time > 14400, 1, 0.5)) AS bean   FROM work_info wi2   WHERE ti.cur_month = wi2.cur_month       AND wi2.star_id = wi.star_id) AS worktime_by_month,         wi.work_time FROM labor_union lu,  user_info ci,  task_info ti,  work_info wi WHERE lu.id  = ci.labor_union_id    AND ci.star_id = wi.star_id    AND wi.task_info_id = ti.id     AND lu.l_id = '5544'    AND wi.cur_day = #{#day} AND ci.user_name LIKE #{userName} AND ci.nick_name LIKE #{#nickName} AND ci.star_id = #{#starId} AND ci.phone_number = #{#phoneNumber} AND ci.qq = #{#qq} AND ci.wei_chat like #{#weiChar} ) wi2 /* #pageable */", nativeQuery = true)
    Page<UserInfoBoard> getAllChickenInfosBoard(String day, String userName, String nickName, Long starId, Long phoneNumber, Long qq, String weiChar, Pageable pageable);
}
