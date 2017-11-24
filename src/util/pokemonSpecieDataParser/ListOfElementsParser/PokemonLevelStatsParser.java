package util.pokemonSpecieDataParser.ListOfElementsParser;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import data.model.PokemonStat;
import util.pokemonSpecieDataParser.LeafElementParser.LevelParser;
import util.pokemonSpecieDataParser.LeafElementParser.MaxHpParser;
import util.pokemonSpecieDataParser.LeafElementParser.NormalAttackParser;
import util.pokemonSpecieDataParser.LeafElementParser.NormalDefenseParser;
import util.pokemonSpecieDataParser.LeafElementParser.SpecialAttackParser;
import util.pokemonSpecieDataParser.LeafElementParser.SpeedParser;
import util.pokemonSpecieDataParser.LeafElementParser.SpecialDefenseParser;
/**
 * This class parses pokemon level stats specified 
 * @author Dan Sun
 *
 */
public class PokemonLevelStatsParser extends ListOfElementsParserAbstract{
    private static String levelStatsTag = "levelStats";
    private static String statTag = "stat";
    
    /**
     * This method parses the document element and gets all the stats for this pokemon
     * @param rootNode the document element of the pokemon species xml file 
     * @return A Map<Integer,PokemonStat> that specifies the stats of this pokemon for every level
     */
    public static Map<Integer,PokemonStat> parse(Element rootNode){
	Map<Integer, PokemonStat> movesMap = new HashMap<>();
	Element levelStats = getListRootElement(rootNode,levelStatsTag);//getLevelStatsElement(rootNode);
	NodeList allStats = getAllElementsInList(levelStats,statTag);//getAllStats(levelStats);
	addAllStatsToMap(allStats,movesMap);
	return movesMap;
    }
    
    private static void addAllStatsToMap(NodeList allStats, Map<Integer, PokemonStat> statsMap) {
	for(int i = 0; i < allStats.getLength(); i++) {
	    parseAndAddStatElementToMap(
		    checkAndConvertNodeToElement(allStats.item(i)),
		    statsMap);
	}
	
    }

    private static void parseAndAddStatElementToMap(Element currentStatElement, Map<Integer, PokemonStat> StatsMap) {
	Integer level = (int)LevelParser.parse(currentStatElement);
	int speed = (int) SpeedParser.parse(currentStatElement);
	int specialAttack = (int)SpecialAttackParser.parse(currentStatElement);
	int specialDefense = (int)SpecialDefenseParser.parse(currentStatElement);
	int normalAttack = (int)NormalAttackParser.parse(currentStatElement);
	int normalDefense = (int)NormalDefenseParser.parse(currentStatElement);
	int maxHp = (int)MaxHpParser.parse(currentStatElement);
	PokemonStat pokemonStat = new PokemonStat(maxHp,normalAttack,
		normalDefense,specialAttack,specialDefense, speed);
	StatsMap.put(level, pokemonStat);	
    }
//implemented in abstract superclass
//    private static NodeList getAllStats(Element levelMoves) {
//	return levelMoves.getElementsByTagName(statTag);
//    }
//
//    private static Element getLevelStatsElement(Element rootNode) {
//	NodeList levelStats = rootNode.getElementsByTagName(levelStatsTag);
//	Element levelMovesElement = (Element)levelStats.item(0);
//	return levelMovesElement;
//    }
}
