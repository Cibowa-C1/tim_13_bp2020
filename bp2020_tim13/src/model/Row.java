package model;

import java.util.HashMap;
import java.util.Map;



public class Row {
	
	 private String name;
	    private Map<String, Object> fields;


	    public Row(String name) {
	        this.fields = new HashMap<>();
	        this.name = name;
	    }

	    public void addField(String fieldName, Object value) {
	        this.fields.put(fieldName, value);
	    }

	    public void removeField(String fieldName) {
	        this.fields.remove(fieldName);
	    }
	    
}
