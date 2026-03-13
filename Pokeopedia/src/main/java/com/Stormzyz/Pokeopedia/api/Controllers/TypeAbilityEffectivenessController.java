package com.Stormzyz.Pokeopedia.api.Controllers;

import com.Stormzyz.Pokeopedia.api.DTO.TypeAbilityEffectiveness.CreateTypeAbilityEffectiveness;
import com.Stormzyz.Pokeopedia.api.DTO.TypeAbilityEffectiveness.SeeTypeAbilityEffectiveness;
import com.Stormzyz.Pokeopedia.api.Models.TypeAbilityEffectiveness;
import com.Stormzyz.Pokeopedia.api.Services.TypeAbilityEffectivenessService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("abilityTypeEffectiveness")
public class TypeAbilityEffectivenessController {

    TypeAbilityEffectivenessService typeAbilityEffectivenessService;

    @GetMapping
    public List<SeeTypeAbilityEffectiveness> searchEffectiveness(@RequestParam(required = false) String abilityName, @RequestParam(required = false) String typeName){
        if(abilityName == null && typeName == null){
            return typeAbilityEffectivenessService.seeAll();
        } else if (abilityName == null) {
            return typeAbilityEffectivenessService.findByTypeName(typeName);

        } else if (typeName == null) {
            return typeAbilityEffectivenessService.findByAbilityName(abilityName);
        }else{
            return  typeAbilityEffectivenessService.findByTypeAbilityName(abilityName, typeName);
        }
    }

    @PostMapping
    public ResponseEntity<SeeTypeAbilityEffectiveness> createTypeAbility(@Valid @RequestBody CreateTypeAbilityEffectiveness dto){
        TypeAbilityEffectiveness typeAbilityEffectiveness = typeAbilityEffectivenessService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SeeTypeAbilityEffectiveness(typeAbilityEffectiveness));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTypeAbility(Long id){
        typeAbilityEffectivenessService.delete(id);
        return ResponseEntity.ok().build();
    }
}
