package authoring.databaseEditor;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import data.LanguageReader;
import data.model.Pokemon;
import data.model.PokemonSpecie;
import data.model.moves.Move;

public class PokemonChooser {
	private Pokemon localPokemon;
	private List<PokemonSpecie> pokemonSpecies;
	private Callback<Pokemon, Integer> saver;
	GridPane root = new GridPane();
	
	public PokemonChooser(List<PokemonSpecie> pokemonSpecies, Pokemon selectedPokemon, Callback<Pokemon, Integer> saver){
		if(selectedPokemon instanceof Pokemon){
			localPokemon = selectedPokemon;
		}else{
			localPokemon = new Pokemon(pokemonSpecies.get(0), "");
		}
		this.pokemonSpecies = pokemonSpecies;
		this.saver = saver;
		showPokemon();
	}
	
	public GridPane showPokemon(){
		root.getChildren().clear();
		//set the constraints
		ColumnConstraints cons1 = new ColumnConstraints();
        cons1.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().add(cons1);
        ColumnConstraints cons2 = new ColumnConstraints();
        cons2.setHgrow(Priority.ALWAYS);
        ColumnConstraints cons3 = new ColumnConstraints();
        cons3.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().addAll(cons1, cons2, cons3);
        RowConstraints rcons1 = new RowConstraints();
        rcons1.setVgrow(Priority.NEVER);
        RowConstraints rcons2 = new RowConstraints();
        rcons2.setVgrow(Priority.NEVER);  
        RowConstraints rcons3 = new RowConstraints();
        rcons2.setVgrow(Priority.SOMETIMES); 
        root.getRowConstraints().addAll(rcons1, rcons2, rcons3);
		//add components
		root.add(showStatList(localPokemon.getCurrentStat().getStatMap()),2,1,2,2);
		root.add(saveButton(),3,0,1,1);
        root.add(showMoveList(localPokemon.getMoves()), 1, 2,1,1);
        root.add(showImage(localPokemon.getCurrentImagePath()), 1, 1, 1, 1);
        root.add(showNickName(localPokemon.getNickName()), 1, 0, 1, 1);
        root.add(showLevelPicker(localPokemon.getMaxLevel()), 2, 0, 1, 1);
        root.add(showSpecieList(pokemonSpecies),0,0,1,3);
        root.setPrefSize(660, 230);
        root.getStylesheets().add("resources/sceneStyle.css");
        saver.call(localPokemon);
        return root;
	}

	public Pokemon getPokemon(){
		return new Pokemon(localPokemon);
	}
	
	public Button saveButton(){
		Button result = new Button("save");
		result.setOnMouseClicked(e->{showPokemon();});
		return result;
	}
	
	public TilePane showMoveList(Move[] moves){
		List<Move> availableMoves = localPokemon.getAvailableMoves();
        Map<String, Move> name2move =
        		availableMoves.stream().collect(Collectors.toMap(Move::getMoveName,
        	                                              Function.identity()));
		TilePane movePane = new TilePane();
		int gridWidth = (int)Math.round(Math.sqrt(localPokemon.getMoveNum()));
		movePane.setPrefColumns(gridWidth);
		for(int i=0;i<localPokemon.getMoveNum();i++){
			ComboBox<String> comboBox;
			if(moves[i]!=null){
				comboBox = createMoveComboBox(availableMoves,
						name2move);
				comboBox.getSelectionModel().select(moves[i].getMoveName());
			}else{
				comboBox = createMoveComboBox(availableMoves,
						name2move);
				comboBox.getSelectionModel().select(null);
			}
			movePane.getChildren().add(comboBox);
		}
		return movePane;
	}

	private ComboBox<String> createMoveComboBox(List<Move> availableMoves,
			Map<String, Move> name2move) {
		ComboBox<String> comboBox = new ComboBox<String>();
        Iterator<Move> availableMovesIterator = availableMoves.iterator();
        while(availableMovesIterator.hasNext()){
        	comboBox.getItems().add(availableMovesIterator.next().getMoveName());
        	comboBox.valueProperty().addListener(new ChangeListener<String>() {
                @Override 
                public void changed(ObservableValue<? extends String> ov, String oldMoveName, String newMoveName) {
                	localPokemon.changeMove(name2move.get(oldMoveName), name2move.get(newMoveName));
                }    
              });
        }
        comboBox.setPrefSize(100, 50);
		return comboBox;
	}
	
	public ListView<PokemonSpecie> showSpecieList(List<PokemonSpecie> pokemonSpecies){ 
		ListView<PokemonSpecie> list = new ListView<PokemonSpecie>();
		ObservableList<PokemonSpecie> items =FXCollections.observableArrayList(pokemonSpecies);
		list.setItems(items);
        list.setCellFactory(new Callback<ListView<PokemonSpecie>, 
                ListCell<PokemonSpecie>>() {
                    @Override 
                    public ListCell<PokemonSpecie> call(ListView<PokemonSpecie> list) {
                        return new ListCell<PokemonSpecie>(){
                        	@Override
                            public void updateItem(PokemonSpecie item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setText(item.getSpecieName());
                                }
                            }
                        };
                    }
                }
            );
        list.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<PokemonSpecie>() {
                    public void changed(ObservableValue<? extends PokemonSpecie> ov, 
                    		PokemonSpecie old_val, PokemonSpecie new_val) {
                            refresh(new_val, localPokemon.getNickName(), localPokemon.getCurrentLevel());
                }
        });
        list.setPrefWidth(100);
		return list;
	}
	
	protected void refresh(PokemonSpecie pokemonSpecie, String name, int currentLevel) {
		localPokemon = new Pokemon(pokemonSpecie, name, currentLevel);
		showPokemon();
	}
	
	public HBox showLevelPicker(int maxLevel){
		HBox result =new HBox();
		Label levelLabel = new Label("Lv: " + String.valueOf(localPokemon.getCurrentLevel()));
		Slider slider = new Slider();
        levelLabel.textProperty().bind(
                Bindings.format(
                    "Lv: %.0f",
                    slider.valueProperty()
                )
         );
		slider.setMin(1);
		slider.setMax(maxLevel);
		slider.setValue(localPokemon.getCurrentLevel());
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(maxLevel/2+1);
		slider.setMinorTickCount(5);
		slider.setBlockIncrement(1);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    localPokemon.changeCurrentLevel(new_val.intValue());
            }
        });
		result.getChildren().addAll(slider, levelLabel);
		return result;
	}
	
	public VBox showNickName(String initialName){
		VBox result = new VBox();
		Label nickNameLabel = new Label("Nick Name:");
		result.getChildren().add(nickNameLabel);
		TextField nickNameField = new TextField(initialName);
		nickNameField.setOnKeyPressed(new EventHandler<KeyEvent>()
			    {
			        @Override
			        public void handle(KeyEvent ke)
			        {
			            if (ke.getCode().equals(KeyCode.ENTER))
			            {
			            	localPokemon.setName(nickNameField.getText());
			    		    showPokemon();
			            }
			        }
			   });
		result.getChildren().add(nickNameField);
		return result;
	}
	
	public ImageView showImage(String imgPath){
		ImageView imageView = new ImageView();
		File file = new File(imgPath);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        return imageView;
	}
	
	/**
	 * create the ability table of pokemon
	 * @param stat
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TableView<List<String>> showStatList(Map<String, Integer> stat){
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
