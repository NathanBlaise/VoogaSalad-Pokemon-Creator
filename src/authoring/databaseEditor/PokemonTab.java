package authoring.databaseEditor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import data.LanguageReader;
import data.model.Pokemon;
import data.model.PokemonSpecie;
import data.model.moves.Move;

public class PokemonTab {
	public PokemonTab(List<PokemonSpecie> pokemonSpecies, PokemonSpecie selectedPokemon){
		
	}

	private void createPokemonText(PokemonSpecie currentPokemonSpecie, String name, int currentLevel){
		Pokemon currentPokemon = new Pokemon(currentPokemonSpecie, name, currentLevel);
	}
	
	public GridPane createMoveList(Move[] moves){
		GridPane movePane = new GridPane();
		int gridWidth = (int)Math.round(Math.sqrt(Pokemon.getMoveNum()));
		int gridHeight = (int)Math.round(Pokemon.getMoveNum()/new Double(gridWidth));
		for(int i=0;i<Pokemon.getMoveNum();i++){
			if(moves[i]!=null){
				movePane.add(new Label(moves[i].getMoveName()), i%gridWidth, i%gridHeight);
			}
		}
		return movePane;
	}
	
	/**
	 * create the ability table of pokemon
	 * @param stat
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TableView<List<String>> createStatList(Map<String, Integer> stat){
		TableView<List<String>> table = new TableView<List<String>>();
		table.setEditable(false); 
        TableColumn<List<String>, String> abilityCol = new TableColumn<List<String>, String>(LanguageReader.convertLanguage("English", "ability"));
        abilityCol.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> p) {
                StringProperty result = new SimpleStringProperty();
                result.set(p.getValue().get(0));
                return result;
            }
         });
        TableColumn<List<String>, String> valueCol = new TableColumn<List<String>, String>(LanguageReader.convertLanguage("English", "value"));
        valueCol.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> p) {
                StringProperty result = new SimpleStringProperty();
                result.set(p.getValue().get(1));
                return result;
            }
         });
        ObservableList<List<String>> data = FXCollections.observableArrayList();
        for(String key: stat.keySet()){
        	List<String> tempList = new ArrayList<String>();
        	tempList.add(key);
        	tempList.add(String.valueOf(stat.get(key)));
        	data.add(tempList);
        }
        table.setItems(data);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(abilityCol, valueCol);
        table.setPrefSize(300, 180);
        return table;
	}
	
}
