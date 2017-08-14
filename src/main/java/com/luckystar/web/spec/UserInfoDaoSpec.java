package com.luckystar.web.spec;

import com.luckystar.web.domain.UserInfo;
import com.luckystar.web.domain.UserInfoBoard;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by coldvmoon on 14/08/2017.
 */
public class UserInfoDaoSpec {
    public static Specification<UserInfoBoard> getSpec(final String name) {
        return new Specification<UserInfoBoard>() {


            @Override
            public Predicate toPredicate(Root<UserInfoBoard> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }
}
