package heatmapdata;

import heatmapdata.HeatMapData;
import heatmapdata.HeatMapDAO;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
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

import org.hibernate.validator.constraints.NotBlank;

@RestController
@RequestMapping("/heatmap")
public class HeatMapController {
	
	private AtomicLong along;
	private HeatMapDAO heatMapDAO;
	
	public HeatMapController() {
		final MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://karan:karan345@ds037067.mongolab.com:37067/heatmap273cmpe"));
        final MongoDatabase heatMapDatabase = mongoClient.getDatabase("heatmap273cmpe");
        heatMapDAO=new HeatMapDAO(heatMapDatabase);
		along = new AtomicLong(123456);
	}
	
	@RequestMapping(value = "/listall", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<FindIterable<Document>> getHeatMaps() {
		
			return new ResponseEntity<FindIterable<Document>>(heatMapDAO.getHeatMaps(),HttpStatus.OK);
		
	}

	@RequestMapping(value = "/creategeoloc", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HeatMapData> createGeoLocId( @RequestBody HeatMapData hmd) 
	{
		hmd.setIdentity((int) along.incrementAndGet());
		hmd.setCreatedAt(new Date().toString());
		heatMapDAO.save(hmd);
		return new ResponseEntity<HeatMapData>(hmd, HttpStatus.CREATED);
		
	}

	@RequestMapping(value = "/geoloc/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<HeatMapData> getHeatMap(@PathVariable int id) 
	{
					return new ResponseEntity<HeatMapData>(heatMapDAO.getHeatMapData(id),HttpStatus.OK);
	}

	@RequestMapping(value = "/geoloc/{id}", method = RequestMethod.PUT, produces="application/json")
	@ResponseBody
	public ResponseEntity<Object> updateHeatMap( @PathVariable int id, @RequestBody HeatMapData hmd) {
		hmd.setIdentity(id);
		heatMapDAO.updateHeatMapData(hmd);
		return new ResponseEntity<Object>(heatMapDAO.getHeatMapData(id),HttpStatus.OK) ;
	}
	
	@RequestMapping(value = "/geoloc/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Object> deleteHeatMap(@NotBlank @PathVariable int id){
			heatMapDAO.deleteGeoLocId(id);
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
}

