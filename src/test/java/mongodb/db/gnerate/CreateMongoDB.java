package mongodb.db.gnerate;

import com.wildcat.db.data.model.Sample;
import com.wildcat.db.data.model.User;
import com.wildcat.db.mongodb.DbClient;
import com.wildcat.db.mongodb.collection.DbCollectionSamples;
import com.wildcat.db.mongodb.collection.DbCollectionUsers;
import org.bson.Document;

public class CreateMongoDB {
    public static void main(String[] args) {
        DbClient.get().dropDatabase(DbClient.dbName);

        //create several users
        User mnogono = new User();
        mnogono.setLogin("mnogono");
        mnogono.setPassword("1");

        User max = new User();
        max.setLogin("max");
        max.setPassword("1");

//        Document doc = new Document();
//        System.out.println(doc.getObjectId("_id"));
//        doc.put("name", "asdasd");

//        DbClient.getDb().getCollection("samples").insertOne(doc);
//        System.out.println(doc.getObjectId("_id"));

        //DbClient.getDb().getCollection("users").insertOne(mnogono);

        DbCollectionUsers.get().insertOne(mnogono);
        DbCollectionUsers.get().insertOne(max);

        Sample sample1 = new Sample();
        sample1.setName("WTA");
        sample1.setOperator(mnogono);
        DbCollectionSamples.get().insertOne(sample1);
    }
}
