package com.Stormzyz.Pokeopedia.api.Repositories;

import com.Stormzyz.Pokeopedia.api.Models.PokemonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PokemonTypeRepository extends JpaRepository<PokemonType, Long> {

    List<PokemonType> findByTypeNameContainingIgnoreCase(String typeName);
    Optional<PokemonType> findByTypeName(String typeName);
}
