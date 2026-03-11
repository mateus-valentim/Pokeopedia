package com.Stormzyz.Pokeopedia.api.Services;

import com.Stormzyz.Pokeopedia.api.DTO.TypeEffectiveness.CreateTypeEffectiveness;
import com.Stormzyz.Pokeopedia.api.DTO.TypeEffectiveness.SeeTypeEffectiveness;
import com.Stormzyz.Pokeopedia.api.Models.PokemonTypes;
import com.Stormzyz.Pokeopedia.api.Models.TypeEffectiveness;
import com.Stormzyz.Pokeopedia.api.Repositories.PokemonTypesRepository;
import com.Stormzyz.Pokeopedia.api.Repositories.TypeEffectivenessRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TypeEffectivenessService {

    private TypeEffectivenessRepository typeEffectivenessRepository;
    private PokemonTypesRepository pokemonTypesRepository;

    public List<SeeTypeEffectiveness> SeeAllTypeEffectiveness(){
        List<TypeEffectiveness> seeTypeEffectiveness = typeEffectivenessRepository.findAll();
        return seeTypeEffectiveness.stream().map(SeeTypeEffectiveness::new).toList();
    }

    public List<SeeTypeEffectiveness> SeeAttackingTypeEffectiveness(String name){
        List<TypeEffectiveness> seeTypeEffectiveness = typeEffectivenessRepository.findByAttackingNameContainingIgnoreCase(name);
        return seeTypeEffectiveness.stream().map(SeeTypeEffectiveness::new).toList();
    }

    public List<SeeTypeEffectiveness> SeeDefendingTypeEffectiveness(String name){
        List<TypeEffectiveness> seeTypeEffectiveness = typeEffectivenessRepository.findByDefendingNameContainingIgnoreCase(name);
        return seeTypeEffectiveness.stream().map(SeeTypeEffectiveness::new).toList();
    }

    public List<SeeTypeEffectiveness> SeeAttackingDefendingTypeEffectiveness(String atackingName, String defendingName){
        List<TypeEffectiveness> seeTypeEffectiveness = typeEffectivenessRepository.findByAttackingDefendingNameContainingIgnoreCase(atackingName, defendingName);
        return seeTypeEffectiveness.stream().map(SeeTypeEffectiveness::new).toList();
    }

    public TypeEffectiveness create(CreateTypeEffectiveness dto){

        PokemonTypes attackingType = pokemonTypesRepository.findById(dto.getAttackingId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pokemon type not found")
        );
        PokemonTypes defendingType = pokemonTypesRepository.findById(dto.getDefendingId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pokemon type not found")
        );

        TypeEffectiveness typeEffectiveness = new TypeEffectiveness();
        typeEffectiveness.setAttackingType(attackingType);
        typeEffectiveness.setDefendingType(defendingType);
        typeEffectiveness.setMultiplier(dto.getMultiplier());


        return typeEffectivenessRepository.save(typeEffectiveness);
    }

    public void delete(long id){
        TypeEffectiveness typeEffectiveness = typeEffectivenessRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokemon type effectiveness not found")
        );

        typeEffectivenessRepository.delete(typeEffectiveness);
    }
}
