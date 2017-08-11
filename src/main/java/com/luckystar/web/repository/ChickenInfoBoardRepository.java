package com.luckystar.web.repository;

import com.luckystar.web.domain.ChickenInfo;
import com.luckystar.web.domain.ChickenInfoBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@Repository
public interface ChickenInfoBoardRepository extends JpaRepository<ChickenInfoBoard,Long> {
    @Query(value="SELECT ci.id as id , ci.user_name as user_name FROM labor_union lu, chicken_info ci, task_info ti, work_info wi WHERE lu.id = ci.labor_union_id AND ci.star_id = wi.star_id AND wi.task_info_id = ti.id AND lu.l_id = '5544' AND wi.cur_day = '2017-08-11'", nativeQuery = true)
    List<ChickenInfoBoard> findAllWithEagerRelationships();
}
