package XMLTools.MavenXML_Validator_Converter;
import java.util.HashMap;

public class Contact {
	private String customer_id;
	private String company_name;
	private String contact_name;
	private String contact_title;
	private String address;
	private String city;
	private String email;
	private String country;
	private String phone;
	private HashMap<String, String> optional_data;
	
//ALL ELEMENTS
	public Contact (String customer_id_in, String company_name_in, String contact_name_in, String contact_title_in,
		String address_in, String city_in, String email_in, String country_in, String phone_in, HashMap<String, String> optional_data_in) 
	{
		customer_id = customer_id_in;
		company_name = company_name_in;
		contact_name = contact_name_in;
		contact_title = contact_title_in;
		address = address_in;
		city = city_in;
		email = email_in;
		country = country_in;
		phone = phone_in;
		optional_data = optional_data_in;
	}
	
	public String get_customer_id() {
		return customer_id;
	}
	public String get_company_name() {
		return company_name;
	}
	
	public String get_contact_name() {
		return contact_name;
	}
	
	public String get_contact_title() {
		return contact_title;
	}
	
	public String get_address() {
		return address;
	}
	
	public String get_city() {
		return city;
	}
	
	public String get_email() {
		return email;
	}
	
	public String get_country() {
		return country;
	}	
	
	public String get_phone() {
		return phone;
	}
	
	public HashMap<String, String> get_optional_data() {
		return optional_data;
	}
	
	public String toString() {
		String object_string = "";
		
		object_string = "CustomerID: " + this.customer_id + "\n" +
						"CompanyName: " + this.company_name + "\n" +
						"ContactName: " + this.contact_name + "\n" +
						"ContactTitle: " + this.contact_title + "\n" +
						"Address: " + this.address + "\n" +
						"City: " + this.city + "\n" +
						"Email: " + this.email + "\n" +
						"Country: " + this.country + "\n" +
						"Phone: " + this.phone;
		
		for (String i : this.optional_data.keySet()) 
			object_string = object_string + "\n" + i + ": " + this.optional_data.get(i);
		
		return object_string;
	}
	
}
