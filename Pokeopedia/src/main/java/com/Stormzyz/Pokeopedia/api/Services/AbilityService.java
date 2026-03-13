package com.Stormzyz.Pokeopedia.api.Services;

import com.Stormzyz.Pokeopedia.api.DTO.Abilities.CreateAbility;
import com.Stormzyz.Pokeopedia.api.DTO.Abilities.EditAbility;
import com.Stormzyz.Pokeopedia.api.DTO.Abilities.SeeAbility;
import com.Stormzyz.Pokeopedia.api.Models.Ability;
import com.Stormzyz.Pokeopedia.api.Repositories.AbilityRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Service
public class AbilityService {

    AbilityRepository abilityRepository;

    public List<SeeAbility> findAll(){

        return abilityRepository.findAll().stream().map(SeeAbility::new).toList();
    }

    public List<SeeAbility> findByName(String abilityName){
        return abilityRepository.findByAbilityNameContainingIgnoreCase(abilityName).stream().map(SeeAbility::new).toList();
    }

    public Ability findById(Long id){
        return abilityRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Ability not found")
        );
    }

    public SeeAbility SeeyId(Long id){
        Ability ability = findById(id);

        return new SeeAbility(ability);
    }

    public Ability create(CreateAbility createAbility){
        Ability ability = new Ability();
        ability.setAbilityDescription(createAbility.getAbilityDescription());
        ability.setAbilityName(createAbility.getAbilityName());

        return abilityRepository.save(ability);
    }

    public Ability edit(EditAbility editAbility, Long id){

        Ability ability = findById(id);

        if(editAbility.getAbilityName()!=null) {
            ability.setAbilityName(editAbility.getAbilityName());
        }
        if(editAbility.getAbilityDescription()!=null) {
            ability.setAbilityDescription(editAbility.getAbilityDescription());
        }

        return abilityRepository.save(ability);

    }

    public void delete(Long id){
        Ability ability = findById(id);
        abilityRepository.delete(ability);
    }
}
