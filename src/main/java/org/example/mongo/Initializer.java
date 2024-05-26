package org.example.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Initializer{

    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection collection;
    private static   Initializer instance;

    private Initializer(String connectionURL,String Database){
         client = MongoClients.create(connectionURL);
         database = client.getDatabase(Database);
    }

    private static Initializer getInstance(String connectionURL,String Database)throws Exception {
        if (instance==null){
            new Initializer(connectionURL,Database);
        }
        return instance;
    }

    public void save(String Collection, Document doc){
         var collection = database.getCollection(Collection);
         collection.insertOne(doc);
    }

}
