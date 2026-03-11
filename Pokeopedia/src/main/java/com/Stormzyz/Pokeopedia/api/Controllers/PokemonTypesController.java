package com.Stormzyz.Pokeopedia.api.Controllers;

import com.Stormzyz.Pokeopedia.api.DTO.PokemonTypes.CreatePokemonType;
import com.Stormzyz.Pokeopedia.api.DTO.PokemonTypes.EditPokemonType;
import com.Stormzyz.Pokeopedia.api.DTO.PokemonTypes.SeePokemonType;
import com.Stormzyz.Pokeopedia.api.Models.PokemonTypes;
import com.Stormzyz.Pokeopedia.api.Services.PokemonTypeServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/typing")
public class PokemonTypesController {

    PokemonTypeServices  pokemonTypeServices;


    @GetMapping
    List<SeePokemonType> searchPokemonTypes(@RequestParam(required = false) String name){

        if(name==null) return pokemonTypeServices.SeeAllPokemonTypes();

        return pokemonTypeServices.SeePokemonTypesName(name);
    }

    @PostMapping
    ResponseEntity<SeePokemonType> createPokemonType(@Valid @RequestBody CreatePokemonType createPokemonType){

        PokemonTypes type = pokemonTypeServices.create(createPokemonType);

        return  ResponseEntity.status(HttpStatus.CREATED).body(new SeePokemonType(type));

    }

    @PatchMapping("/{id}")
    ResponseEntity<SeePokemonType> editPokemonType(@Valid @PathVariable long id, @Valid @RequestBody EditPokemonType editPokemonType){
        PokemonTypes type = pokemonTypeServices.edit(editPokemonType, id);
        return  ResponseEntity.status(HttpStatus.OK).body(new SeePokemonType(type));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePokemonType(@PathVariable long id){
        pokemonTypeServices.delete(id);
        return  ResponseEntity.status(HttpStatus.OK).body("Pokemon type has been deleted");
    }

}
