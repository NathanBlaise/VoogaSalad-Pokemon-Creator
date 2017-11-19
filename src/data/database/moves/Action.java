package data.database.moves;

import java.io.Serializable;

import data.model.PokemonStat;

/**
 * 
 * @author cy122
 * 
 * This is an interface that holds a lambda that actually takes a action from a friend Pokemon to enermy Pokemon
 * it gives back the statuses of both pokemons, where PokemonStat[0] should be friend Pokemon, PokemonStat[1] should be enermy Pokemon
 *
 */

@FunctionalInterface
public interface Action extends Serializable{
	PokemonStat[] move(PokemonStat friend, PokemonStat enermy);
}
