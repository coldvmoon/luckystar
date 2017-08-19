package com.luckystar.web.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.luckystar.web.domain.*;
import com.luckystar.web.repository.LaborUnionRepository;
import com.luckystar.web.repository.UserInfoBoardRepository;
import com.luckystar.web.repository.UserRepository;
import com.luckystar.web.repository.WorkTimeBoardRepository;
import com.luckystar.web.security.SecurityUtils;
import com.luckystar.web.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

@RestController
@RequestMapping("/api")
public class DashboardResource {
    private final Logger log = LoggerFactory.getLogger(DashboardResource.class);


    @Autowired
    private UserInfoBoardRepository userInfoBoardRepository;
    @Autowired
    private WorkTimeBoardRepository workTimeBoardRepository;
    @Autowired
    private LaborUnionRepository laborUnionRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/chicken-infos-board")
    @Timed
    public ResponseEntity<List<UserInfoBoard>> getAllChickenInfosBoard(Long laborUnionId, String day, String searchCondition, @ApiParam Pageable pageable) {
//        log.debug("REST request to get a page of ChickenInfos");
//        Query query = em.createNativeQuery("SELECT  ci.user_name,  ci.nick_name,  ci.star_id,  wi.star_level,  wi.rich_level,  wi.fans_count,  (wi.bean_total - wi.fisrt_bean) AS bean_by_day,  ti.min_task,  ti.max_task,  ci.reg_date,  (SELECT     SUM(wi2.bean_total - wi2.fisrt_bean)   FROM work_info wi2   WHERE ti.cur_month = wi2.cur_month       AND wi2.star_id = wi.star_id) AS bean_by_month,  (SELECT     SUM(wi2.work_time) AS bean   FROM work_info wi2   WHERE ti.cur_month = wi2.cur_month       AND wi2.star_id = wi.star_id) AS worktime_by_month1,  (SELECT     SUM(IF(work_time > 14400, 1, 0.5)) AS bean   FROM work_info wi2   WHERE ti.cur_month = wi2.cur_month       AND wi2.star_id = wi.star_id) AS worktime_by_month,wi.work_time,wi.star_name,wi.rich_name FROM labor_union lu,  user_info ci,  task_info ti,  work_info wi WHERE lu.id  = ci.labor_union_id    AND ci.star_id = wi.star_id    AND wi.task_info_id = ti.id     AND lu.l_id = '5544'    AND wi.cur_day = '"+day+"'");
//        List<Object[]> list = query.getResultList();
//        List<Map> data = new ArrayList<>();
//        for (Object[] obj : list) {
//            Map res = new HashMap<String, Object>();
//            res.put("userName", obj[0]);
//            res.put("nickName", obj[1]);
//            res.put("starId", obj[2]);
//            res.put("starLevel", obj[3]);
//            res.put("richLevel", obj[4]);
//            res.put("fansCount", obj[5]);
//            res.put("beanByDay", obj[6]);
//
//            res.put("minTask", obj[7]);
//            res.put("maxTask", obj[8]);
//            res.put("regDate", obj[9]);
//            res.put("beanByMonth", obj[10]);
//            res.put("workTimeByMonth", obj[11]);
//            res.put("workTimeByDay", obj[12]);
//            res.put("starName", obj[12]);
//            res.put("richName", obj[12]);
//            data.add(res);
//        }

        Page<UserInfoBoard> page = userInfoBoardRepository.getAllChickenInfosBoard(laborUnionId, day, fuzzyQuery(searchCondition), pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/labor-unions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    private String fuzzyQuery(String query) {
        if (StringUtils.isEmpty(query)) {
            return "%";
        } else {
            return "%" + query + "%";
        }
    }

    @GetMapping("/work-time-board")
    @Timed
    public ResponseEntity<List<WorkTimeBoard>> getWorkTimeBoard(Long laborUnionId, Integer day, String searchCondition, @ApiParam Pageable pageable) {
//        Query query = em.createNativeQuery("SELECT ci.id,ci.user_name,  ci.nick_name,  ci.star_id,  (SELECT     SUM(work_time) AS all_time   FROM work_info wi2   WHERE ti.cur_month = wi2.cur_month       AND wi2.star_id = wi.star_id) AS worktime_by_month1,  (SELECT     SUM(IF(work_time > 14400, 1, 0.5)) AS bean   FROM work_info wi2   WHERE ti.cur_month = wi2.cur_month       AND wi2.star_id = wi.star_id) AS worktime_by_month,  wi.work_time,  wi.cur_day FROM labor_union lu,  user_info ci,  task_info ti,  work_info wi WHERE lu.id  = ci.labor_union_id    AND ci.star_id = wi.star_id    AND wi.task_info_id = ti.id     AND lu.l_id = '5544'    AND wi.cur_month = 201708");
//        List<Object[]> list = query.getResultList();
//        List<Map> data = new ArrayList<>();
//        for (Object[] obj : list) {
//            Map res = new HashMap<String, Object>();
//            res.put("userName", obj[0]);
//            res.put("nickName", obj[1]);
//            res.put("starId", obj[2]);
//            res.put("totalTime", obj[3]);
//            res.put("totalDay", obj[4]);
//            res.put("workTime", obj[5]);
//            res.put("curDay", obj[6]);
//            data.add(res);
//        }
//    userInfoBoardRepository.find(pageable);

        DateTime dt = new DateTime();

        Page<WorkTimeBoard> page = null;
        if (day == null) {
            day = 1;
        }
        if (day.equals(30)) {
            page = workTimeBoardRepository.getWorkTimeBoardCurMonth(laborUnionId, Long.valueOf(dt.toString("yyyyMM")), fuzzyQuery(searchCondition), pageable);
        } else {
            List<String> days = new ArrayList<>();
            for (int i = 0; i <= day; i++) {
                days.add(dt.plusDays(-i).toString("yyyy-MM-dd"));
            }
            page = workTimeBoardRepository.getWorkTimeBoardByDay(laborUnionId, days, fuzzyQuery(searchCondition), pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/labor-unions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/recent-time")
    @Timed
    public ResponseEntity<HashMap<String,Object>> getRecentTime() {
        HashMap<String,Object> res = new HashMap<>();
        List<String> date = new ArrayList();

        DateTime dt = new DateTime();
        for (int i = 0; i < 7; i++) {
            date.add(dt.plusDays(-i).toString("yyyy-MM-dd"));
        }

        res.put("date",date);

        List<LaborUnion> laborUnions=null;
        Optional<User> user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin());
        if(user.get().getLogin().equals("system")){
            laborUnions = laborUnionRepository.findAll();
        }else {
            laborUnions = laborUnionRepository.findByUserIsCurrentUser(user.get().getId());
        }
        res.put("laborUnions",laborUnions);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
