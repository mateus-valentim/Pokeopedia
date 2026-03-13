package com.Stormzyz.Pokeopedia.api.Repositories;

import com.Stormzyz.Pokeopedia.api.Models.Ability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AbilityRepository extends JpaRepository<Ability,Long> {
    List<Ability> findByAbilityNameContainingIgnoreCase(String ability_name);
    Optional<Ability> findById(Long id);
}
