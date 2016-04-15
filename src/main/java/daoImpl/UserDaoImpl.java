package daoImpl;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import dao.UserDao;
import model.User;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.print.Doc;
import java.util.ArrayList;

/**
 * Created by fish on 3/25/16.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Stateless
public class UserDaoImpl implements UserDao{

    @Override
    public User[] list() {
        final ArrayList<User> users = new ArrayList<User>();
        FindIterable<Document>iterable = getColl().find(Filters.eq("isDeleted", false));
        iterable.forEach(new Block<Document>() {

            @Override
            public void apply(Document document) {
                users.add(documentToUser(document));
            }
        });
        return users.toArray(new User[0]);
    }

    @Override
    public Boolean add(String name, String password, String role) {
        // duplicated name
        if (get(name) != null) {
            return false;
        }

        Document document = new Document();
        document.append("name", name);
        document.append("password", password);
        document.append("role", role);
        document.append("isDeleted", false);
        try {
            getColl().insertOne(document);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean add(String name, String password) {
        return add(name, password, "user");
    }

    @Override
    public User get(String name) {
        FindIterable<Document> iterable = getColl().find(Filters.and(Filters.eq("name", name), Filters.eq("isDeleted", false)));

        Document document = iterable.first();
        if (document == null) {
            return null;
        }

        return documentToUser(document);
    }

    @Override
    public Boolean update(String name, User user) {
        UpdateResult result = getColl().updateOne(new Document("name", new ObjectId(name)).append("isDeleted", false),
                new Document("$set", new Document("password", user.getPassword())));
        if (result.getModifiedCount() == 0) {
            return false;
        }

        return true;
    }

    @Override
    public Boolean delete(String name) {
        UpdateResult result = getColl().updateOne(new Document("name", new ObjectId(name)).append("isDeleted", false),
                new Document("$set", new Document("isDeleted", true)));
        if (result.getModifiedCount() == 0) {
            return false;
        }

        return true;
    }


    private MongoCollection<Document> getColl() {
        return ConnectDB.getDB().getCollection("user");
    }


    private User documentToUser(Document document) {
        User user = new User();
        user.setName(document.getString("name"));
        user.setPassword(document.getString("password"));
        user.setRole(document.getString("role"));
        return user;
    }
}
