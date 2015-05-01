package com.cmpe273.controller;


import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe273.dao.OffersDAO;
import com.cmpe273.database.DatabaseConnection;
import com.cmpe273.model.Offer;
import com.mongodb.client.MongoCollection;

@RestController
@RequestMapping("/theshop/api/v1")
public class ShoppingController {
    private static DatabaseConnection dbConnection = new DatabaseConnection();
    public MongoCollection<Document> offersCollection;
    public MongoCollection<Document> usersCollection;

    @Autowired
    public OffersDAO offersDAO;
    static{
        dbConnection.setDbConnection();
        // new ScheduledTasks();
    }
	@RequestMapping(value="/getoffers/beaconid/{beaconId}/rss/{rss}",method = RequestMethod.GET)
	public List<Document> getOffers(@PathVariable String beaconId,@PathVariable String rss,@RequestParam("uid") String userId){
        return offersDAO.getOffers(beaconId,rss,userId);
        
	}
	
	@RequestMapping(value="/postoffer",method = RequestMethod.POST)
	public void getOffers(@RequestBody Offer offer){
         offersDAO.postOffer(offer);
        
	}





}
