package com.Stormzyz.Pokeopedia.api.Controllers;

import com.Stormzyz.Pokeopedia.api.DTO.PokemonTypes.SeePokemonType;
import com.Stormzyz.Pokeopedia.api.DTO.TypeEffectiveness.CreateTypeEffectiveness;
import com.Stormzyz.Pokeopedia.api.DTO.TypeEffectiveness.SeeTypeEffectiveness;
import com.Stormzyz.Pokeopedia.api.Models.TypeEffectiveness;
import com.Stormzyz.Pokeopedia.api.Services.TypeEffectivenessService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/effectiveness")
public class TypeEffectivenessController {
    TypeEffectivenessService typeEffectivenessService;

    @GetMapping
    List<SeeTypeEffectiveness> seeTypeEffectivenesses(@RequestParam(required = false) String attacking, @RequestParam(required = false) String defending){
        if(attacking == null && defending == null) return typeEffectivenessService.SeeAllTypeEffectiveness();
        else if (defending == null) return typeEffectivenessService.SeeAttackingTypeEffectiveness(attacking);
        else if(attacking == null) return typeEffectivenessService.SeeDefendingTypeEffectiveness(defending);
        else return typeEffectivenessService.SeeAttackingDefendingTypeEffectiveness(attacking, defending);
    }

    @PostMapping
    ResponseEntity<SeeTypeEffectiveness > create(@Valid @RequestBody CreateTypeEffectiveness createTypeEffectiveness){
        TypeEffectiveness typeEffectiveness = typeEffectivenessService.create(createTypeEffectiveness);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SeeTypeEffectiveness(typeEffectiveness));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable long id){
        typeEffectivenessService.delete(id);
        return  ResponseEntity.status(HttpStatus.OK).body("Pokemon type effectiveness has been deleted");
    }

    @PostMapping("/test")
    public String test(){
        return "POST funcionando";
    }
}
