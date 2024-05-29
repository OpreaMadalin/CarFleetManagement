package com.company.controller.databaseController;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
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

    public MongoController() {
        MongoCredentials envCreds = getEnvCreds();

        if (null == envCreds.getDbName() || null == envCreds.getConnectionURI()) {
            String connectionUri = "mongodb+srv://madalinoprea:pass@cluster0.9oiuuhp.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(connectionUri))
                    .build();

            this.client = MongoClients.create(settings);
            this.dbName = "devDB";
        } else {
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(envCreds.getConnectionURI()))
                    .build();

            this.client = MongoClients.create(settings);
            this.dbName = envCreds.getDbName();
        }
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

    public void deleteVehicle(String carId) {
        MongoCollection<Document> carsCollection = getCarsCollection();
        try (MongoCursor<Document> cursor = carsCollection.find().cursor()) {
            while (cursor.hasNext()) {
                Document currentCar = cursor.next();
                String currentCarId = currentCar.get("_id").toString();
                if (carId.equals(currentCarId)) {
                    carsCollection.findOneAndDelete(currentCar);
                }
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Document> getUsers() {
        MongoCollection<Document> usersCollection = getUsersCollection();
        MongoCursor<Document> cursor = usersCollection.find().cursor();
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
