package heatmapdata;

import heatmapdata.HeatMapData;
import heatmapdata.HeatMapDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Random;
import java.lang.Math;

import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;

import com.mongodb.*;



import java.net.UnknownHostException;

import com.mongodb.ParallelScanOptions; 
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

@RestController
@RequestMapping("/heatmap")
public class HeatMapController {
	
	private AtomicLong along;
	private HeatMapDAO heatMapDAO;
	
	public HeatMapController() {
		final MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://karan:karan345@ds053429.mongolab.com:53429/heatmapdata273"));
        final MongoDatabase heatMapDatabase = mongoClient.getDatabase("heatmapdata273");
        heatMapDAO=new HeatMapDAO(heatMapDatabase);
		along = new AtomicLong(123456);
	}
	


	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<FindIterable<Document>> getHeatMaps() {
		
			return new ResponseEntity<FindIterable<Document>>(heatMapDAO.getHeatMaps(),HttpStatus.OK);
		
	}

//	@RequestMapping(method=RequestMethod.POST)
//	public HeatMapData create(@RequestBody HeatMapData data) {
//	    return repo.save(data);
//	  }
//	

}

