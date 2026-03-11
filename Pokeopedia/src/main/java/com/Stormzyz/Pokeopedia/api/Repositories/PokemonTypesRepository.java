package com.Stormzyz.Pokeopedia.api.Repositories;

import com.Stormzyz.Pokeopedia.api.Models.PokemonTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PokemonTypesRepository extends JpaRepository<PokemonTypes, Long> {

    List<PokemonTypes> findByTypeNameContainingIgnoreCase(String typeName);
    Optional<PokemonTypes> findByTypeName(String typeName);
}
