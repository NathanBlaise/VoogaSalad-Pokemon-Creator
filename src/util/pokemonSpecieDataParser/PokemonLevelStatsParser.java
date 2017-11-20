package util.pokemonSpecieDataParser;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import data.model.PokemonStat;
import util.pokemonSpecieDataParser.LeafElementParser.LevelParser;
import util.pokemonSpecieDataParser.LeafElementParser.MaxHpParser;
import util.pokemonSpecieDataParser.LeafElementParser.NormalAttackParser;
import util.pokemonSpecieDataParser.LeafElementParser.NormalDefenseParser;
import util.pokemonSpecieDataParser.LeafElementParser.SpecialAttackParser;
import util.pokemonSpecieDataParser.LeafElementParser.SpeedParser;
import util.pokemonSpecieDataParser.LeafElementParser.specialDefenseParser;

public class PokemonLevelStatsParser {
    private static String levelStatsTag = "levelStats";
    private static String statTag = "stat";
    
    
    public static Map<Integer,PokemonStat> parse(Element rootNode){
	Map<Integer, PokemonStat> movesMap = new HashMap<>();
	Element levelStats = getLevelStatsElement(rootNode);
	NodeList allStats = getAllStats(levelStats);
	addAllStatsToMap(allStats,movesMap);
	return movesMap;
    }
    
    private static void addAllStatsToMap(NodeList allMoves, Map<Integer, PokemonStat> statsMap) {
	for(int i = 0; i < allMoves.getLength(); i++) {
	    Node currentMoveNode = allMoves.item(i);
	    assert(currentMoveNode.getNodeType() == Node.ELEMENT_NODE);
	    Element currentStatElement = (Element)currentMoveNode;
	    parseAndAddStatElementToMap(currentStatElement,statsMap);
	}
	
    }

    private static void parseAndAddStatElementToMap(Element currentStatElement, Map<Integer, PokemonStat> StatsMap) {
	Integer level = LevelParser.parse(currentStatElement);
	double speed = SpeedParser.parse(currentStatElement);
	double specialAttack = SpecialAttackParser.parse(currentStatElement);
	double specialDefense = specialDefenseParser.parse(currentStatElement);
	double normalAttack = NormalAttackParser.parse(currentStatElement);
	double normalDefense = NormalDefenseParser.parse(currentStatElement);
	double maxHp = MaxHpParser.parse(currentStatElement);
	PokemonStat pokemonStat = new PokemonStat(maxHp,normalAttack,
		normalDefense,specialAttack,specialDefense, speed);
	StatsMap.put(level, pokemonStat);	
    }

    private static NodeList getAllStats(Element levelMoves) {
	return levelMoves.getElementsByTagName(statTag);
    }

    private static Element getLevelStatsElement(Element rootNode) {
	NodeList levelStats = rootNode.getElementsByTagName(levelStatsTag);
	Element levelMovesElement = (Element)levelStats.item(0);
	return levelMovesElement;
    }
}
