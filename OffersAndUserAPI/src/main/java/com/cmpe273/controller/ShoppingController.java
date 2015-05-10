package com.cmpe273.controller;


import java.util.List;

import com.cmpe273.model.User;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe273.dao.OffersDAO;
import com.cmpe273.dao.UserDAO;
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

    @Autowired
    public UserDAO userDAO;

    static{
        dbConnection.setDbConnection();
        // new ScheduledTasks();
    }
	@RequestMapping(value="/getoffers/beaconid/{beaconId}/rss/{rss}",method = RequestMethod.GET)
	public List<Document> getOffers(@PathVariable String beaconId,@PathVariable String rss,@RequestParam("uid") String userId){
        return offersDAO.getOffers(beaconId,rss,userId);
        
	}
	
	@RequestMapping(value="/postoffer",method = RequestMethod.POST)
	public void postOffers(@RequestBody Offer offer){
         offersDAO.postOffer(offer);
        
	}
	
	@RequestMapping(value="/removeoffer",method = RequestMethod.POST)
	public void removeOffers(@RequestBody Offer offer){
        // offersDAO.postOffer(offer);
        
	}

    @RequestMapping(value="/getusers/{userId}",method = RequestMethod.GET)
    public Document getUsers(@PathVariable String userId){
        Document doc =  userDAO.getUsers(userId);
        return doc;
    }

    @RequestMapping(value="/createusers", method = RequestMethod.POST)
    public void createUsers(@RequestBody User user){
        userDAO.postUser(user);
    }

    @RequestMapping(value="/updateusers/{userId}", method = RequestMethod.PUT)
    public void updateUsers(@PathVariable String userId, @RequestBody User user){
        userDAO.updateUser(user, userId);
    }
}

