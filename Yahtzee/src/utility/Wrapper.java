package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.GameRules;

public class Wrapper {


	public static void saveGame(GameRules GR) throws JAXBException{		
		JAXBContext context = JAXBContext.newInstance(GameRules.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Write to System.out
		//m.marshal(reg, System.out);

		// Write to File
		m.marshal(GR, new File("Game.xml"));
	}
	public static GameRules loadGame(File file) throws FileNotFoundException, JAXBException{

		JAXBContext context = JAXBContext.newInstance(GameRules.class);
		Unmarshaller um = context.createUnmarshaller();
		GameRules GR = (GameRules) um.unmarshal(file);

		return GR;
	}
}



