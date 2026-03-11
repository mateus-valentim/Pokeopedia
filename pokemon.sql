DROP DATABASE IF EXISTS Pokepedia;
CREATE DATABASE Pokepedia;


CREATE TABLE Pokepedia.pokemon(
	pokedex_number BIGINT NOT NULL,
    pokemon_name VARCHAR(255) NOT NULL,
    
    PRIMARY KEY(pokedex_number)
);

CREATE TABLE Pokepedia.forms(
	
    id BIGINT NOT NULL AUTO_INCREMENT,
    form_type ENUM('BASE','MEGA','GIGANTAMAX','REGIONAL','OTHER') NOT NULL,
    form_name VARCHAR(255),
	base_hp int NOT NULL,
    base_defense int NOT NULL,
    base_attack int NOT NULL,
    base_spe_defence int NOT NULL,
    base_spe_attack int NOT NULL,
    base_speed int NOT NULL,
    pokemon_id BIGINT,
    
    PRIMARY KEY (id),
    FOREIGN KEY (pokemon_id) REFERENCES Pokepedia.pokemon(pokedex_number)
    
);


CREATE TABLE Pokepedia.evolutions(
	to_pokemon BIGINT NOT NULL,
    from_pokemon BIGINT NOT NULL,
    method TEXT NULL,
    
	PRIMARY KEY(to_pokemon, from_pokemon),
    FOREIGN KEY (to_pokemon) REFERENCES Pokepedia.forms(id), 
    FOREIGN KEY (from_pokemon) REFERENCES Pokepedia.forms(id)
);

CREATE TABLE Pokepedia.pokemon_types(
	id BIGINT NOT NULL AUTO_INCREMENT,
    type_name VARCHAR(255) UNIQUE NOT NULL,
    colorhex VARCHAR(7) NOT NULL,
    
    PRIMARY KEY (id)
);

CREATE TABLE Pokepedia.type_effectiveness(
	id BIGINT NOT NULL AUTO_INCREMENT,
	attacking_type_id BIGINT NOT NULL,
    defending_type_id BIGINT NOT NULL,
    multiplier DECIMAL(3,2),
    
    PRIMARY KEY (id),
    FOREIGN KEY (attacking_type_id) REFERENCES Pokepedia.pokemon_types(id),
    FOREIGN KEY (defending_type_id) REFERENCES Pokepedia.pokemon_types(id)
);

CREATE TABLE Pokepedia.pokemon_type_link(
	pokemon_id BIGINT NOT NULL,
    pokemon_type_id BIGINT NOT NULL,
    
	PRIMARY KEY (pokemon_id, pokemon_type_id),
    FOREIGN KEY (pokemon_type_id) REFERENCES Pokepedia.pokemon_types(id),
    FOREIGN KEY (pokemon_id) REFERENCES Pokepedia.forms(id)
);

CREATE TABLE Pokepedia.generations(
	id BIGINT NOT NULL,
    generation_name VARCHAR(50),
    
    PRIMARY KEY (id)
    
);

CREATE TABLE Pokepedia.version_groups(
	id BIGINT NOT NULL AUTO_INCREMENT,
    version_name VARCHAR(255) NOT NULL,
    generation_id BIGINT NOT NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (generation_id) REFERENCES Pokepedia.generations(id)
);

CREATE TABLE Pokepedia.games(

	id BIGINT NOT NULL AUTO_INCREMENT,
	title VARCHAR(255) UNIQUE NOT NULL,
    version_group_id BIGINT NOT NULL,
    
	PRIMARY KEY(id),
    FOREIGN KEY (version_group_id) REFERENCES Pokepedia.version_groups(id)
);

CREATE TABLE Pokepedia.pokemon_descriptions(
	id BIGINT NOT NULL AUTO_INCREMENT,
    pokemon_description TEXT NOT NULL,
    pokemon_id BIGINT NOT NULL,
    game_id BIGINT NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY (pokemon_id) REFERENCES Pokepedia.forms(id),
    FOREIGN KEY (game_id) REFERENCES Pokepedia.games(id)
    
);

CREATE TABLE Pokepedia.moves(
	id BIGINT NOT NULL AUTO_INCREMENT,
    move_name VARCHAR(255) UNIQUE NOT NULL,
    move_description TEXT NOT NULL,
    power_points INT NOT NULL,
    damage INT NOT NULL,
    accuracy INT NOT NULL,
    move_type BIGINT NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY (move_type) REFERENCES Pokepedia.pokemon_types(id) 
);

CREATE TABLE Pokepedia.pokemon_moves_link(
	id BIGINT NOT NULL AUTO_INCREMENT,
    method ENUM('Level Up','TM','TR','HM','EGG MOVES','EVOLUTION') NOT NULL,
    method_data VARCHAR(255),
    pokemon_id BIGINT NOT NULL,
    version_group_id BIGINT NOT NULL,
    move_id BIGINT NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY (pokemon_id) REFERENCES Pokepedia.forms(id),
    FOREIGN KEY (version_group_id) REFERENCES Pokepedia.version_groups(id),
    FOREIGN KEY (move_id) REFERENCES Pokepedia.moves(id) 
    
);
    
CREATE TABLE Pokepedia.abilities(
	id BIGINT NOT NULL AUTO_INCREMENT,
    ability_name VARCHAR(255) NOT NULL,
    ability_description TEXT NOT NULL,

	PRIMARY KEY (id)
);

CREATE TABLE Pokepedia.pokemon_abilities_link(
	id BIGINT NOT NULL AUTO_INCREMENT,
    hidden BOOLEAN NOT NULL,
    ability_id BIGINT NOT NULL,
    pokemon_id BIGINT NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY (pokemon_id) REFERENCES Pokepedia.forms(id),
    FOREIGN KEY (ability_id) REFERENCES Pokepedia.abilities(id)
    
);

CREATE TABLE Pokepedia.type_ability_effectiveness(
	attacking_type_id BIGINT NOT NULL,
    defending_ability_id BIGINT NOT NULL,
    multiplier DECIMAL(3,2),
    
    PRIMARY KEY (attacking_type_id, defending_ability_id),
    FOREIGN KEY (attacking_type_id) REFERENCES Pokepedia.pokemon_types(id),
    FOREIGN KEY (defending_ability_id) REFERENCES Pokepedia.abilities(id)
    
);

CREATE TABLE Pokepedia.sprites(

	id BIGINT NOT NULL AUTO_INCREMENT,
    sprite_type ENUM("DEFAULT_FRONT", "DEFAULT_BACK", "SHINY_FRONT", "SHINY_BACK") NOT NULL,
    pokemon_id BIGINT NOT NULL,
    image_url TEXT NOT NULL,
    sprite_generation BIGINT NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY (pokemon_id) REFERENCES Pokepedia.forms(id),
    FOREIGN KEY (sprite_generation) REFERENCES Pokepedia.generations(id)

);

CREATE TABLE Pokepedia.models(

	id BIGINT NOT NULL AUTO_INCREMENT,
    model_type ENUM("DEFAULT", "SHINY") NOT NULL,
    pokemon_id BIGINT NOT NULL,
    model_url TEXT NOT NULL,
    model_generation BIGINT NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY (pokemon_id) REFERENCES Pokepedia.forms(id),
    FOREIGN KEY (model_generation) REFERENCES Pokepedia.generations(id)

);