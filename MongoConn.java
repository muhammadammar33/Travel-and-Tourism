package com.travel;

// import org.bson.Document;
// import org.bson.conversions.Bson;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
// import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoConn {

    MongoDatabase mongoDatabase;

    MongoConn() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/");
        MongoClient mongoClient = MongoClients.create(connectionString);

        mongoDatabase = mongoClient.getDatabase("Travel");
    }

    public static void main(String[] args) {
        // ConnectionString connectionString = new
        // ConnectionString("mongodb://localhost:27017/");
        // MongoClient mongoClient = MongoClients.create(connectionString);

        // MongoDatabase mongoDatabase = mongoClient.getDatabase("Travel");
        // MongoCollection mongoCollection = mongoDatabase.getCollection("User");

        // System.out.println("Connection created.");

        // Document document = new Document("user_id", 1);
        // document.append("name", "Ammar");
        // document.append("email", "ammar@gmail.com");
        // document.append("password", 12345678);
        // document.append("date_of_birth", "2003-06-17");
        // document.append("contact", "03267744260");
        // document.append("question", "Your Childhood name.");
        // document.append("answer", "theepa");

        // mongoCollection.insertOne(document);

        // Document filter = new Document("name", "Ammar");

        // if (filter != null) {
        // System.out.println("User found.");

        // Document update = new Document("$set", new Document("date_of_birth",
        // "2002-06-17"));
        // mongoCollection.updateOne(filter, update);
        // System.out.println("User Updated");
        // } else {
        // System.out.println("Not found.");
        // }

    }
}
