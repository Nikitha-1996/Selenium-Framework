package data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataDrivenfromJSON {
	
	public List<HashMap<String, String>> dataReadfromJSON() throws IOException {
		
		//string converted from json
		String file =FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\data\\data.json"),StandardCharsets.UTF_8);
		//string to Hashmap
		ObjectMapper objectMapper = new ObjectMapper(); //jackson Datbid depencies from maven we got this
		List<HashMap<String,String>> hashMapinputs =objectMapper.readValue(file, new TypeReference<List<HashMap<String, String>>>(){
		});
		return hashMapinputs;
		
	}

}
