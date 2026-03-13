package com.Stormzyz.Pokeopedia.api.Repositories;

import com.Stormzyz.Pokeopedia.api.Models.TypeAbilityEffectiveness;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Registered
public interface TypeAbilityEffectivenessRepository extends JpaRepository<TypeAbilityEffectiveness, Long> {


    @Query("SELECT t FROM TypeAbilityEffectiveness t " +
            "JOIN FETCH t.defendingAbility d " +
            "WHERE LOWER(d.abilityName) LIKE LOWER(CONCAT('%', :abilityName, '%'))")
    List<TypeAbilityEffectiveness> findByAbilityNameContainingIgnoreCase(@Param("abilityName") String abilityName);

    @Query("SELECT t FROM TypeAbilityEffectiveness t " +
            "JOIN FETCH t.attackingType a " +
            "WHERE LOWER(a.typeName) LIKE LOWER(CONCAT('%', :typeName, '%'))")
    List<TypeAbilityEffectiveness> findByTypeNameContainingIgnoreCase(@Param("typeName") String typeName);

    @Query("SELECT t FROM TypeAbilityEffectiveness t " +
            "JOIN FETCH t.defendingAbility d " +
            "JOIN FETCH t.attackingType a " +
            "WHERE LOWER(d.abilityName) LIKE LOWER(CONCAT('%', :abilityName, '%')) " +
            "AND LOWER(a.typeName) LIKE LOWER(CONCAT('%',:typeName,'%'))")
    List<TypeAbilityEffectiveness> findByAbilityNameTypeNameContainingIgnoreCase(@Param("abilityName") String abilityName, @Param("typeName") String typeName);
}
