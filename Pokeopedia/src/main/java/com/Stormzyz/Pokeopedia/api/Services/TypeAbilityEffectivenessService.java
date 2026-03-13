package com.Stormzyz.Pokeopedia.api.Services;


import com.Stormzyz.Pokeopedia.api.DTO.TypeAbilityEffectiveness.CreateTypeAbilityEffectiveness;
import com.Stormzyz.Pokeopedia.api.DTO.TypeAbilityEffectiveness.SeeTypeAbilityEffectiveness;
import com.Stormzyz.Pokeopedia.api.Models.Ability;
import com.Stormzyz.Pokeopedia.api.Models.PokemonType;
import com.Stormzyz.Pokeopedia.api.Models.TypeAbilityEffectiveness;
import com.Stormzyz.Pokeopedia.api.Repositories.AbilityRepository;
import com.Stormzyz.Pokeopedia.api.Repositories.TypeAbilityEffectivenessRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TypeAbilityEffectivenessService {

    private TypeAbilityEffectivenessRepository typeAbilityEffectivenessRepository;
    private AbilityService abilityService;
    private PokemonTypeServices pokemonTypeServices;

    public List<SeeTypeAbilityEffectiveness> seeAll(){

        return typeAbilityEffectivenessRepository.findAll().stream().map(SeeTypeAbilityEffectiveness::new).toList();

    }

    public List<SeeTypeAbilityEffectiveness> findByAbilityName(String abilityName){

        return typeAbilityEffectivenessRepository.findByAbilityNameContainingIgnoreCase(abilityName).stream().map(SeeTypeAbilityEffectiveness::new).toList();
    }

    public List<SeeTypeAbilityEffectiveness> findByTypeName(String typeName){
        return typeAbilityEffectivenessRepository.findByTypeNameContainingIgnoreCase(typeName).stream().map(SeeTypeAbilityEffectiveness::new).toList();
    }

    public List<SeeTypeAbilityEffectiveness> findByTypeAbilityName(String abilityName, String typeName){
        return typeAbilityEffectivenessRepository.findByAbilityNameTypeNameContainingIgnoreCase(abilityName, typeName).stream().map(SeeTypeAbilityEffectiveness::new).toList();
    }

    public TypeAbilityEffectiveness findById(Long id){
        return typeAbilityEffectivenessRepository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.BAD_REQUEST, "TypeAbilityEffectiveness not Found")
        );
    }

    public TypeAbilityEffectiveness create(CreateTypeAbilityEffectiveness createTypeAbilityEffectiveness){

        Ability ability = abilityService.findById(createTypeAbilityEffectiveness.getAbilityId());
        PokemonType pokemonType = pokemonTypeServices.FindById(createTypeAbilityEffectiveness.getTypeId());

        TypeAbilityEffectiveness typeAbilityEffectiveness = new TypeAbilityEffectiveness();
        typeAbilityEffectiveness.setAttackingType(pokemonType);
        typeAbilityEffectiveness.setDefendingAbility(ability);
        typeAbilityEffectiveness.setMultiplier(createTypeAbilityEffectiveness.getMultiplier());

        return  typeAbilityEffectivenessRepository.save(typeAbilityEffectiveness);

    }

    public void delete(Long id){
        TypeAbilityEffectiveness typeAbilityEffectiveness = findById(id);
        typeAbilityEffectivenessRepository.delete(typeAbilityEffectiveness);
    }

}
