package com.company.controller.databaseController;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;

public class MongoController {

    private static final String usersCollectionName = "users";
    private static final String carsCollectionName = "cars";
    private final MongoClient client;
    private final String dbName;

    public MongoController(MongoCredentials mongoCredentials) {
        this.client = new MongoClient(new MongoClientURI(mongoCredentials.getConnectionURI()));
        this.dbName = mongoCredentials.getDbName();
    }

    public MongoController() {
        MongoCredentials envCreds = getEnvCreds();
        this.client = new MongoClient(new MongoClientURI(envCreds.getConnectionURI()));
        this.dbName = envCreds.getDbName();

    }

    private MongoCredentials getEnvCreds() {
        Map<String, String> env = System.getenv();
        String mongoUser = env.get("MONGO_USER");
        String mongoPassword = env.get("MONGO_PASSWORD");
        String mongoCluster = env.get("MONGO_CLUSTER");
        String mongoDbName = env.get("MONGO_DB_NAME");
        return new MongoCredentials(mongoUser, mongoPassword, mongoCluster, mongoDbName);
    }

    public MongoDatabase getDatabase() {
        return client.getDatabase(dbName);
    }

    public MongoCollection<Document> getUsersCollection() {
        return getDatabase().getCollection(usersCollectionName);
    }

    public MongoCollection<Document> getCarsCollection() {
        return getDatabase().getCollection(carsCollectionName);
    }

    public void addUser(String username, String password) {
        Document doc = new Document();
        doc.append("username", username);
        doc.append("password", password);
        getUsersCollection().insertOne(doc);
    }

    public Document getUserWithUsername(String username) {
        MongoCollection<Document> usersCollection = getUsersCollection();
        Bson bsonFilter = Filters.eq("username", username);
        return usersCollection.find(bsonFilter).first();
    }

    public void addCar(String brand, String model) {
        Document doc = new Document();
        doc.append("brand", brand);
        doc.append("model", model);
        getCarsCollection().insertOne(doc);
    }

    public ArrayList<Document> getVehicles() {

        MongoCollection<Document> carsCollection = getCarsCollection();
        MongoCursor<Document> cursor = carsCollection.find().cursor();
        ArrayList<Document> result = new ArrayList<>();

        try {
            while (cursor.hasNext()) {
                Document currentDoc = cursor.next();
                result.add(currentDoc);
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return result;
    }
}
