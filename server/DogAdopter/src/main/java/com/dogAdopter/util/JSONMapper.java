package com.dogAdopter.util;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dogAdopter.entity.User;

public class JSONMapper {
	
	static ObjectMapper mapper = new ObjectMapper();


	public static Integer getID(String jsonRequest) {
		JSONObject json = new JSONObject(jsonRequest);
		return json.getInt("id");
	}

	public static Object getJSONArray(ArrayList list) {
		
		JSONObject returnObject = new JSONObject();
		JSONArray returnList = new JSONArray();
		if(list!=null && !list.isEmpty()){
			for (Object object : list) {
				try {
					returnList.put(new JSONObject(mapper.writeValueAsString(object)));
				} catch (JSONException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		returnObject.put("list", returnList);
		return returnObject;
	}
	
	

}
