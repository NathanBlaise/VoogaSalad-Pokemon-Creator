package data;

public class GameTypeReader {
	public static String getGameType(String name){
		return new PropertyReader("../resources/GameTypes.properties").getString(name);
	}
}
