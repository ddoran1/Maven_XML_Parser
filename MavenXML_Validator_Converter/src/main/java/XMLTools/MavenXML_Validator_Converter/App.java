package XMLTools.MavenXML_Validator_Converter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class App {
	private static String xml_path = "FILE_PATH\\ab.xml";
	private static String json_output_path = "FILE_PATH\\output.json";
	private static String xml_output_path = "FILE_PATH\\";
	public static ArrayList<Contact> contact_list = new ArrayList<Contact>();
	
	
	public static void main( String[] args ){
    	try {
    		File xml_file = new File(xml_path);
    		contact_list = Utility.xmlParsingEngine(xml_file);
		
    		Utility.outputContactListToJSON(contact_list, json_output_path);
    		Utility.outputContactListToXML(contact_list, xml_output_path);
    	}
    	catch(Exception e) {
			e.printStackTrace();
    	}
    }  
}
