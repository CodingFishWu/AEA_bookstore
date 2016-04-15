package daoImpl;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Created by fish on 3/27/16.
 */
public class ConnectDB {
    public static MongoClient mongoClient = new MongoClient();
    public static String name = "Bookstore";

    public static MongoDatabase getDB() {
        return mongoClient.getDatabase(name);
    }
}
