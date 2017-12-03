package data.saving;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import data.Database;
import data.database.NPCCollection;
import data.database.PokemonSpecieCollection;
import data.database.TileCollection;
import data.map.Cell;
import data.map.GameMap;
import data.model.Model;
import data.model.NPC;
import data.model.Tile;
import data.model.PokemonSpecie;
import data.player.Player;

/**
 * for creating the xml file of default pokemon
 * @author cy122
 *
 */
public class CreateDefaultDatabase{
		
		private static GameMap createMap(){
			GameMap map = new GameMap(10, 15);
			for(int i=0; i<10; i++){
				for(int j=0; j<15; j++){
					map.setCell(i,j,new Cell("images/reg_tile_scaled.png",true,false,null));
				}
			}
			return map;
		}
		
		private static Model createModel(){		
			Model model = new Model(new ArrayList<NPC>(), new ArrayList<PokemonSpecie>(), new ArrayList<Tile>());
			new NPCCollection().passNPCToModel(model);
			new TileCollection().passTileToModel(model);
			
			
			PokemonSpecieCollection species = new PokemonSpecieCollection();
			species.passSpeciesToModel(model);
			
			
			
			//add new specie
			ArrayList<PokemonSpecie> newSpecies = model.getPokemonSpecies();
			
			// from index 1 to 386; retrieve the pokemon image from the database
			
			for (int num = 1; num < 386; num ++) {
				PokemonSpecie specie1 = new PokemonSpecie(newSpecies.get(0));
				specie1.setSpecieName("Tony " + num);
				Map<Integer, String> evoMap = new HashMap<Integer,String>();
				evoMap.put(1, "images/pokemon_sprites/"+num+".gif" ); 
				specie1.setLevelEvolutionImagePath(evoMap);
				newSpecies.add(specie1);
				model.setPokemonSpecies(newSpecies);
			}
			
		
			
			return model;
		}

		public CreateDefaultDatabase(){
			String path = "src/resources/defaultDatabase.xml";
			GameMap map = createMap();
			Model model = createModel();
			Player player = new Player();
			Database database = new Database(map, model, player);
//			
//			
//			new xmlWriter().writeXML(model, path);
//			Model modelResult = new xmlReader<Model>().readXML(path);
//			System.out.println(modelResult);
//			
//			
//			new xmlWriter().writeXML(map, path);
//			GameMap mapResult = new xmlReader<GameMap>().readXML(path);
//			System.out.println(mapResult);
//			
//			new xmlWriter().writeXML(player, path);
//			Player playerResult = new xmlReader<Player>().readXML(path);
//			System.out.println(playerResult);
			
			new xmlWriter().writeXML(database, path);
//			Database databaseResult = new xmlReader<Database>().readXML(path);
//			System.out.println(databaseResult);
		}

}
