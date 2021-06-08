package XMLTools.MavenXML_Validator_Converter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Utility {
	
	public static ArrayList<Contact> contact_list = new ArrayList<Contact>();
	
	public static ArrayList<Contact> xmlParsingEngine(File xml_file) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xml_file);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("Contact");  
		
			HashMap<String, String> map = new HashMap<String, String>();
			
			for (int itr = 0; itr < nodeList.getLength(); itr++){  
				Node node = nodeList.item(itr);   
				if (node.getNodeType() == Node.ELEMENT_NODE)  
					contact_list.add(xmlNodeParser(node));				  
			} 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return contact_list;
	}
	
	public static Contact xmlNodeParser(Node node) {
		HashMap<String, String> map = new HashMap<String, String>();
		String customer_id = "", company_name = "", contact_name = "", contact_title = "",
			address = "", city = "", email = "", region = "", postal_code = "", country = "", phone = "", fax = "";
		Element eElement = (Element) node;  
		
		try {
			customer_id = eElement.getElementsByTagName("CustomerID").item(0).getTextContent();
			company_name = eElement.getElementsByTagName("CompanyName").item(0).getTextContent();  
			contact_name = eElement.getElementsByTagName("ContactName").item(0).getTextContent();
			contact_title = eElement.getElementsByTagName("ContactTitle").item(0).getTextContent();  
			address = eElement.getElementsByTagName("Address").item(0).getTextContent();  
			city = eElement.getElementsByTagName("City").item(0).getTextContent();  
			email = eElement.getElementsByTagName("Email").item(0).getTextContent();  
			try { 
				region = eElement.getElementsByTagName("Region").item(0).getTextContent();
				map.put("Region", region);
			} catch(Exception e) {
				region = null;
			}
			try {
				postal_code = eElement.getElementsByTagName("PostalCode").item(0).getTextContent();
				map.put("PostalCode", postal_code);
			} catch(Exception e) {
				postal_code = null;
			}
			country = eElement.getElementsByTagName("Country").item(0).getTextContent();  
			phone = "Phone: "+ eElement.getElementsByTagName("Phone").item(0).getTextContent();
			try {
				fax = eElement.getElementsByTagName("Fax").item(0).getTextContent();
				map.put("Fax", fax);
			} catch(Exception e) {
				fax = null;
			}
		}
		catch(NullPointerException e) {
			System.out.println("ERROR: " + e.getStackTrace());
		}
		
		Contact contact = new Contact(customer_id, company_name, contact_name, contact_title,
				address, city, email, country, phone, map);
		
		return contact;
	}
	
	@SuppressWarnings("unchecked")
	public static void outputContactListToJSON(ArrayList<Contact> contact_list, String json_output_path) {
		try {
    		FileWriter file = new FileWriter(json_output_path);
		
    		//Creating a JSONObject object
    		JSONArray address_book_list = new JSONArray();
    		JSONObject address_book = new JSONObject();;
    		JSONObject json_object = null;
    		HashMap<String, String> optional_data = null;
    		for(int i = 0; i < contact_list.size(); i++) {
    			json_object = new JSONObject();
    			optional_data = contact_list.get(i).get_optional_data();
    			
    			//Input contact object data into JSON Object
    			json_object.put("CustomerID", contact_list.get(i).get_customer_id());
    			json_object.put("CompanyName", contact_list.get(i).get_company_name());
    			json_object.put("ContactName", contact_list.get(i).get_contact_name());
    			json_object.put("ContactTitle", contact_list.get(i).get_contact_title());
    			json_object.put("Address", contact_list.get(i).get_address());
    			json_object.put("City", contact_list.get(i).get_city());
    			json_object.put("Email", contact_list.get(i).get_email());
    			json_object.put("Phone", contact_list.get(i).get_phone());
    			//loading optional data into object
    			for (String key : optional_data.keySet()) 
    				json_object.put(key, optional_data.get(key));

    			address_book_list.add(json_object);
    			json_object = null;
    		}
    		
    		address_book.put("AddressBook", address_book_list);
    		file.write(address_book.toJSONString());
    	         
    		System.out.println("JSON file created!!");
    		
    		file.close();

    	}
    	catch(Exception e) {
			e.printStackTrace();
    	}
	}
	
	public static void outputContactListToXML(ArrayList<Contact> contact_list, String xml_output_path) {
		try {
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.newDocument();
	         HashMap<String, String> optional_data = null;
	         // root element
	         Element rootElement = doc.createElement("AddressBook");
	         doc.appendChild(rootElement);
	         	
	         for(int i = 0; i < contact_list.size(); i++) {
	        	 // Contact element
	        	 Element contact = doc.createElement("Contact");
	        	 rootElement.appendChild(contact);

	        	 //Load Contact obj data into Contact node
	        	 //customer_id
	        	 Element customer_id = doc.createElement("CustomerID");
	        	 customer_id.appendChild(doc.createTextNode(contact_list.get(i).get_customer_id()));
	        	 contact.appendChild(customer_id);

	        	 Element company_name = doc.createElement("CompanyName");
	        	 company_name.appendChild(doc.createTextNode(contact_list.get(i).get_company_name()));
	        	 contact.appendChild(company_name);
	        	 
	        	 Element contact_name = doc.createElement("ContactName");
	        	 contact_name.appendChild(doc.createTextNode(contact_list.get(i).get_contact_name()));
	        	 contact.appendChild(contact_name);
	        	 
	        	 Element contact_title = doc.createElement("ContactTitle");
	        	 customer_id.appendChild(doc.createTextNode(contact_list.get(i).get_contact_title()));
	        	 contact.appendChild(contact_title);
	        	 
	        	 Element address = doc.createElement("Address");
	        	 address.appendChild(doc.createTextNode(contact_list.get(i).get_address()));
	        	 contact.appendChild(address);
	        	 
	        	 Element city = doc.createElement("City");
	        	 city.appendChild(doc.createTextNode(contact_list.get(i).get_city()));
	        	 contact.appendChild(city);
	        	 
	        	 Element email = doc.createElement("Email");
	        	 email.appendChild(doc.createTextNode(contact_list.get(i).get_email()));
	        	 contact.appendChild(email);
	        	 
	        	 Element country = doc.createElement("Country");
	        	 country.appendChild(doc.createTextNode(contact_list.get(i).get_country()));
	        	 contact.appendChild(country);
	        	 
	        	 Element phone = doc.createElement("Phone");
	        	 phone.appendChild(doc.createTextNode(contact_list.get(i).get_phone()));
	        	 contact.appendChild(phone);
	        	 
	    		//loading optional data into object
	        	optional_data = contact_list.get(i).get_optional_data();
	        	Element opt = null;
	    		for (String key : optional_data.keySet()) {
		        	 opt = doc.createElement(key);
		        	 opt.appendChild(doc.createTextNode(optional_data.get(key)));
		        	 contact.appendChild(opt);
		        	 opt = null;
	    		}
	        	 
	         }

	         // write the content into xml file
	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         DOMSource source = new DOMSource(doc);
	         StreamResult result = new StreamResult(new File(xml_output_path + "AddressBook.xml"));
	         transformer.transform(source, result);
	         
	         // Output to console for testing
	         StreamResult consoleResult = new StreamResult(System.out);
	         transformer.transform(source, consoleResult);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	}
}
