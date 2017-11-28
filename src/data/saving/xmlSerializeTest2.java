package data.saving;

import java.io.IOException;
import java.util.ArrayList;

import tests.authoring.Specie1;
import tests.authoring.Specie2;
import data.Database;
import data.database.PokemonSpecieCollection;
import data.event.EventNPC;
import data.event.EventPokemon;
import data.event.Instruction;
import data.event.InstructionNPCDialogue;
import data.event.InstructionNPCFight;
import data.map.Cell;
import data.map.GameMap;
import data.model.Model;
import data.model.NPC;
import data.model.Pokemon;
import data.model.PokemonSpecie;
import data.player.Player;

public class xmlSerializeTest2 {

		public xmlSerializeTest2() {
			// TODO Auto-generated constructor stub
		}

		public static void main(String args[]) throws IOException{
			String path = "src/resources/defaultDatabase.xml";
			GameMap map = createMap();
			Model model = createModel();
			Player player = new Player();
			Database database = new Database(map, model, player);
			EventPokemon eventPokemon = new EventPokemon(new Pokemon(model.getPokemonSpecies().get(0), ""));
			EventNPC eventNPC = new EventNPC(new NPC("images/CaptainMap.png", "Caption Kirk"));
			ArrayList<Instruction> instructions = new ArrayList<Instruction>();
			instructions.add(new InstructionNPCFight(eventNPC.getNpc(), new Pokemon[InstructionNPCFight.getPokemonNum()]));
			instructions.add(new InstructionNPCDialogue(eventNPC.getNpc(), new ArrayList<String>()));
			eventNPC.setInstructions(instructions);
			
			new xmlWriter().writeXML(model, path);
			Model modelResult = new xmlReader<Model>().readXML(path);
			System.out.println(modelResult);
			
			
			new xmlWriter().writeXML(map, path);
			GameMap mapResult = new xmlReader<GameMap>().readXML(path);
			System.out.println(mapResult);
			
			new xmlWriter().writeXML(player, path);
			Player playerResult = new xmlReader<Player>().readXML(path);
			System.out.println(playerResult);
			
			new xmlWriter().writeXML(database, path);
			Database databaseResult = new xmlReader<Database>().readXML(path);
			System.out.println(databaseResult);
//			
//			new xmlWriter().writeXML(eventPokemon, path);
//			EventPokemon eventPokemonResult = new xmlReader<EventPokemon>().readXML(path);
//			System.out.println(eventPokemonResult);
//			
//			new xmlWriter().writeXML(eventNPC, path);
//			EventNPC eventNPCResult = new xmlReader<EventNPC>().readXML(path);
//			System.out.println(eventNPCResult);
		}
		
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
			ArrayList<NPC> NPCs = new ArrayList<NPC>();
			NPC npc = new NPC("images/CaptainMap.png", "Caption Kirk");
			NPCs.add(npc);
			Model model = new Model(NPCs, new ArrayList<PokemonSpecie>());
			PokemonSpecieCollection species = new PokemonSpecieCollection();
			species.passSpeciesToModel(model);
			return model;
		}

}
