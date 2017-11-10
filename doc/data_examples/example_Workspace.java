
public class example_Workspace {
	int user_posX;
	int user_posY;
	Grid[][] map;
	
	// Event include the image of that event and the list of commands that event includes, so on.
	Vector<Event> events;
	
	//database include customized pokemons or default pokemons, so on.
	Database database;
	
	
	example_Workspace(int user_posX, int user_posY, Grid[][] map, Vector<Event> events, Database database){
		this.user_posX=user_posX;
		this.user_posY=user_posY;
		this.map = map;
		this.events = events;
		this.database = database;
	}
}
