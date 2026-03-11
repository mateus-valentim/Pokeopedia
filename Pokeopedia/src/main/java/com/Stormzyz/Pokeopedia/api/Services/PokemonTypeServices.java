package com.Stormzyz.Pokeopedia.api.Services;

import com.Stormzyz.Pokeopedia.api.DTO.PokemonTypes.CreatePokemonType;
import com.Stormzyz.Pokeopedia.api.DTO.PokemonTypes.EditPokemonType;
import com.Stormzyz.Pokeopedia.api.DTO.PokemonTypes.SeePokemonType;
import com.Stormzyz.Pokeopedia.api.Models.PokemonTypes;
import com.Stormzyz.Pokeopedia.api.Repositories.PokemonTypesRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@AllArgsConstructor
@Service
public class PokemonTypeServices {

    PokemonTypesRepository pokemonTypesRepository;


    public List<SeePokemonType> SeeAllPokemonTypes(){
        return pokemonTypesRepository.findAll().stream().map(SeePokemonType::new).toList();
    }

    public List<SeePokemonType> SeePokemonTypesName(String name){
        return pokemonTypesRepository.findByTypeNameContainingIgnoreCase(name).stream().map(SeePokemonType::new).toList();
    }

    public PokemonTypes create(CreatePokemonType dto){

        PokemonTypes type = new PokemonTypes();
        type.setTypeName(dto.getTypeName());
        type.setColorhex(dto.getColorhex());

        return pokemonTypesRepository.save(type);

    }

    public PokemonTypes edit(EditPokemonType dto, long id){
        PokemonTypes type = pokemonTypesRepository.findById(id)
                .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokemon type not found")
        );

        if(dto.getTypeName()!=null){
            type.setTypeName(dto.getTypeName());
        }
        if(dto.getColorhex()!=null){
            type.setColorhex(dto.getColorhex());
        }

        return pokemonTypesRepository.save(type);
    }

    public void delete(long id){
        PokemonTypes type = pokemonTypesRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokemon type not found")
                );


        pokemonTypesRepository.delete(type);
    }
}


