// use case: save all the attributes of customized things when user is still unfinished into XML

import java.io.File;
import java.util.Map;
import XMLWriter

public class Writer{

	XMLWriter currentWriter = ...;

	write(File f, Map<Object, int[][]> map){
		currentWriter.writeXML(f, map);

	}

}
