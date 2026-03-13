package com.Stormzyz.Pokeopedia.api.Controllers;

import com.Stormzyz.Pokeopedia.api.DTO.Abilities.CreateAbility;
import com.Stormzyz.Pokeopedia.api.DTO.Abilities.EditAbility;
import com.Stormzyz.Pokeopedia.api.DTO.Abilities.SeeAbility;
import com.Stormzyz.Pokeopedia.api.Models.Ability;
import com.Stormzyz.Pokeopedia.api.Services.AbilityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/abilities")
@AllArgsConstructor
public class AbilityController {

    AbilityService abilityService;

    @GetMapping
    List<SeeAbility> searchAbilities(@RequestParam(required = false) String name)
    {
        if(name==null) return abilityService.findAll();
        else return abilityService.findByName(name);
    }

    @PostMapping
    ResponseEntity<SeeAbility> createAbility(@Valid @RequestBody CreateAbility dto)
    {
        Ability ability = abilityService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SeeAbility(ability));
    }

    @PatchMapping("/{id}")
    ResponseEntity<SeeAbility> editAbility(@Valid @RequestBody EditAbility dto, @PathVariable Long id){
        Ability ability = abilityService.edit(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SeeAbility(ability));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteAbility(@PathVariable Long id){
        abilityService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
