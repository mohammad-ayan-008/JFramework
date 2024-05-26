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

    public static Initializer getInstance(String connectionURL,String Database)throws Exception {
        if (instance==null){
            instance= new Initializer(connectionURL,Database);
        }
        return instance;
    }

    public void save(String Collection, Document doc){
         var collection = database.getCollection(Collection);
         collection.insertOne(doc);
    }

    public String findALL(String Collection){
        var collection = database.getCollection(Collection);
        var cursor = collection.find().cursor();
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        while (cursor.hasNext()){
            builder.append(cursor.next().toJson());
        }
        builder.append("]");
        return builder.toString();

    }

}
