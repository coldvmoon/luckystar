package com.luckystar.web.repository;

import com.luckystar.web.domain.LaborUnion;
import com.luckystar.web.domain.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UserInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    @Query(value = "SELECT user_info.* FROM user_info, labor_union, labor_union_user WHERE user_info.labor_union_id = labor_union.id AND labor_union.id = labor_union_user.labor_unions_id  AND labor_union_user.users_id =?1 /* #pageable */",nativeQuery = true)
    Page<UserInfo> findByUserIsCurrentUser(Long id, Pageable pageable);
}
