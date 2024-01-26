package com.example.microAdministracion.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {
	
private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();

public ArrayList<HashMap<String, String>> getMetaData() {
	return metadata;
}

public void setMetadaData (String type, String code, String date) {
	HashMap<String, String> map = new HashMap<String, String>();
	
	map.put("type", type);
	map.put("code", code);
	map.put("date", date);
	
	metadata.add(map);
}


}
