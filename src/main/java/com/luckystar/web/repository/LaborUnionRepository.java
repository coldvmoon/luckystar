package com.luckystar.web.repository;

import com.luckystar.web.domain.LaborUnion;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the LaborUnion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LaborUnionRepository extends JpaRepository<LaborUnion,Long> {

    @Query("select distinct labor_union from LaborUnion labor_union left join fetch labor_union.users")
    List<LaborUnion> findAllWithEagerRelationships();

    @Query("select labor_union from LaborUnion labor_union left join fetch labor_union.users where labor_union.id =:id")
    LaborUnion findOneWithEagerRelationships(@Param("id") Long id);

}
