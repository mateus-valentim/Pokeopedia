package com.Stormzyz.Pokeopedia.api.Services;

import com.Stormzyz.Pokeopedia.api.DTO.PokemonTypes.CreatePokemonType;
import com.Stormzyz.Pokeopedia.api.DTO.PokemonTypes.EditPokemonType;
import com.Stormzyz.Pokeopedia.api.DTO.PokemonTypes.SeePokemonType;
import com.Stormzyz.Pokeopedia.api.Models.PokemonType;
import com.Stormzyz.Pokeopedia.api.Repositories.PokemonTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@AllArgsConstructor
@Service
public class PokemonTypeServices {

    PokemonTypeRepository pokemonTypeRepository;


    public List<SeePokemonType> SeeAllPokemonTypes(){
        return pokemonTypeRepository.findAll().stream().map(SeePokemonType::new).toList();
    }

    public List<SeePokemonType> SeePokemonTypesName(String name){
        return pokemonTypeRepository.findByTypeNameContainingIgnoreCase(name).stream().map(SeePokemonType::new).toList();
    }

    public PokemonType FindById(Long id){
        return pokemonTypeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pokemon Type Not Found")
        );
    }

    public PokemonType create(CreatePokemonType dto){

        PokemonType type = new PokemonType();
        type.setTypeName(dto.getTypeName());
        type.setColorhex(dto.getColorhex());

        return pokemonTypeRepository.save(type);

    }

    public PokemonType edit(EditPokemonType dto, long id){
        PokemonType type = pokemonTypeRepository.findById(id)
                .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokemon type not found")
        );

        if(dto.getTypeName()!=null){
            type.setTypeName(dto.getTypeName());
        }
        if(dto.getColorhex()!=null){
            type.setColorhex(dto.getColorhex());
        }

        return pokemonTypeRepository.save(type);
    }

    public void delete(long id){
        PokemonType type = pokemonTypeRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokemon type not found")
                );


        pokemonTypeRepository.delete(type);
    }
}


