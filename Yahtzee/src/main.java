import java.io.FileNotFoundException;
import java.util.*;
import javax.xml.bind.JAXBException;
import controller.*;
import model.*;
import view.*;

public class main {
private static Controller cont = new Controller();
	public static void main(String[] args) {
		try {
			cont.setup();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
