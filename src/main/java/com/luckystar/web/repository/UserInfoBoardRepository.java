package com.luckystar.web.repository;

import com.luckystar.web.domain.LaborUnion;
import com.luckystar.web.domain.UserInfoBoard;
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
public interface UserInfoBoardRepository extends JpaRepository<UserInfoBoard,Long> {

    @Query(value = "select * from user_info",nativeQuery = true)
    List<UserInfoBoard> find();

}
