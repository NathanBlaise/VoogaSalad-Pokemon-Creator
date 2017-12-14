package authoring.eventManage;

import authoring.databaseEditor.PokemonChooser;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import data.Database;
import data.event.InstructionPokemonFight;
/**
 * edit the pokemon fight
 * @author cy122
 *
 */
public class InstructionPokemonFightEditor implements InstructionEditor{

	private Callback<InstructionPokemonFight, Integer> saver;
	private Database database;
	private InstructionPokemonFight instruction;
	
	public InstructionPokemonFightEditor(InstructionPokemonFight instruction, Database database, Callback<InstructionPokemonFight, Integer> saver) {
		this.saver = saver;
		this.database = database;
		this.instruction = instruction;
		saver.call(instruction);
	}

	@Override
	public Node showEditor() {
		GridPane gridPane = new PokemonChooser(database.getModel().getPokemonSpecies(), instruction.getPokemon(), e->{
			instruction.setPokemon(e);
			saver.call(instruction);
			return null;
		}).showPokemon();
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(gridPane);	
		return borderPane;
	}
	
}
