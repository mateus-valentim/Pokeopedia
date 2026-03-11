package com.Stormzyz.Pokeopedia.api.Repositories;

import com.Stormzyz.Pokeopedia.api.Models.TypeEffectiveness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TypeEffectivenessRepository extends JpaRepository<TypeEffectiveness,Long> {



    @Query("SELECT t FROM TypeEffectiveness t " +
            "JOIN FETCH t.attackingType a " +
            "WHERE LOWER(a.typeName) LIKE LOWER(CONCAT('%', :name, '%')) ")
    List<TypeEffectiveness> findByAttackingNameContainingIgnoreCase(@Param("name") String name);


    @Query("SELECT t FROM TypeEffectiveness t " +
            "JOIN FETCH t.defendingType d " +
            "WHERE LOWER(d.typeName) LIKE LOWER(CONCAT('%', :name, '%')) ")
    List<TypeEffectiveness> findByDefendingNameContainingIgnoreCase(@Param("name") String name);


    @Query("SELECT t FROM TypeEffectiveness t " +
            "JOIN FETCH t.defendingType d " +
            "JOIN FETCH t.attackingType a " +
            "WHERE LOWER(d.typeName) LIKE LOWER(CONCAT('%', :defendingName, '%')) " +
            "AND LOWER(a.typeName) LIKE LOWER(CONCAT('%', :attackingName, '%'))")
    List<TypeEffectiveness> findByAttackingDefendingNameContainingIgnoreCase(@Param("attackingName") String attackingName, @Param("defendingName") String defendingName);


    Optional<TypeEffectiveness> findById(long id);
}
