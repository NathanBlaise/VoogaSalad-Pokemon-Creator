package data.database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import util.FilePathConverter;
import util.fileParsers.NPCFileParser;
import data.model.Model;
import data.model.NPC;

/**
 * for giving the model the npc models
 * @author cy122
 * @author Dan Sun
 *
 */
public class NPCCollection extends DataCollectionAbstract{
	private static String defaultNPCsRelativePath = "src/resources/defaultNPC";
	    //stores mapping from name to class
	    private Map<String,NPC> NPCs; 
	    private NPCFileParser fileParser;

	    /**
	     * Constructor for the class.
	     * Initializes instance variables used by this class,
	     * and reads default NPC files from the folder
	     * that is holding them
	     * 
	     */
	    public NPCCollection() {
			NPCs = new HashMap<>();
			fileParser = new NPCFileParser();
			readDefaultNPCs();
	    }
	    
	    /**
	     * This method hands all the NPCs it 
	     * has to the model given
	     * @param model The receiver of all the NPCs
	     */
	    public void passNPCToModel(Model model) {
			ArrayList<NPC> NPCList = new ArrayList<>();
			for(String name:NPCs.keySet()) {
			    NPCList.add(NPCs.get(name));
			}
			model.setNPCs(NPCList);
	    }

	    private void readDefaultNPCs() {
			File[] files = getFilesWithinAsArray(
				FilePathConverter.getAbsolutePath(defaultNPCsRelativePath));
			for(File NPCFile:files) {
			    NPC NPC = null;
			    try {
			    	NPC = fileParser.parseFile(NPCFile);
			    } catch (ParserConfigurationException | SAXException | IOException e) {
					//TODO: handle exception better
					System.out.println("Failed to parse NPC from file " + 
						NPCFile.getAbsolutePath());
					e.printStackTrace();
					System.exit(1);
			    }
			    NPCs.put(NPC.getName(), NPC);
			}
	    }

}
