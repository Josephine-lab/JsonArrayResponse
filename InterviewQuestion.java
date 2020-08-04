package Cucumber.Automation;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonArray;






public class InterviewQuestion {
	
	HashMap<String, Integer> map = new HashMap<String, Integer>();
	
	//building the json array response to be used
	
	public JsonArray jsonResponse() {
		
		//build json response array
		
		JsonArray array = Json.createArrayBuilder()
					.add(Json.createObjectBuilder()
							.add("name", "Kenya")
							.add("currency", "SHS"))
					.add(Json.createObjectBuilder()
							.add("name", "Uganda")
							.add("currency","SHS"))
					.add(Json.createObjectBuilder()
							.add("name", "Czech Republic")
							.add("currency","CZK"))
					.add(Json.createObjectBuilder()
							.add("name", "Germany")
							.add("currency","EURO"))
					.add(Json.createObjectBuilder()
							.add("name", "France")
							.add("currency","EURO"))
					.add(Json.createObjectBuilder()
							.add("name", "India")
							.add("currency","INR"))
					.add(Json.createObjectBuilder()
							.add("name", "Belgium")
							.add("currency","EURO"))
					.add(Json.createObjectBuilder()
							.add("name", "America")
							.add("currency","DOLLAR"))
					
				.build();
				
		return array;
	}
	
	//Map<String, Integer>
	public void populateMap() {
		
		//Map<String, Integer> map = new HashMap<String, Integer>();
		
		JsonArray array = jsonResponse();
		for (int i=0; i<array.size();i++) {
			
			if(!map.containsKey(array.getJsonObject(i).getString("currency"))){
				map.put(array.getJsonObject(i).getString("currency"), 1);
			}

			else {
				//if map already includes key, increment value of key (currency) 
				
				int count = map.get(array.getJsonObject(i).getString("currency"));
			
				map.put(array.getJsonObject(i).getString("currency"), count+1);
			}

		}
	}
	
	public HashMap<String, Integer> sortMapByValue() {
		
		
		//convert HashMap into List
		List<Entry<String, Integer>> mapList = new LinkedList<Entry<String, Integer>>(map.entrySet());
		
		
		Collections.sort(mapList, new Comparator<Map.Entry<String, Integer>>(){
		
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
		
				Integer int1 = o1.getValue();
				Integer int2 = o2.getValue();
				
				//sort by descending order
				return int2.compareTo(int1);
				
			}
			
		});
		
		
		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		
		for(Map.Entry<String, Integer> entry: mapList) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
	
		return sortedMap;
		
	}
	
	void printMap(HashMap<String, Integer> sortedMap) {
		
		int count = 0;
		Iterator<Map.Entry<String, Integer>> iterator = sortedMap.entrySet().iterator();
		
		while (count <2 && iterator.hasNext()) {
			Map.Entry<String, Integer> entry = iterator.next();
			
			System.out.println(entry.getKey() +":"+entry.getValue());
			count++;
		}
		
	}

	public static void main(String[] args) {
		
		InterviewQuestion iq = new InterviewQuestion();
		
		iq.populateMap();
		HashMap<String, Integer> sortedMap = iq.sortMapByValue();
		iq.printMap(sortedMap);	

	}
	

}
