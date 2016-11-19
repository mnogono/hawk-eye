package mongodb.db.gnerate;

import com.mongodb.DBCollection;

import com.vaadin.data.util.BeanItem;
import com.wildcat.db.active.record.ActiveRecordFactory;
import com.wildcat.db.data.model.Curve;
import com.wildcat.db.data.model.Sample;
//import com.wildcat.db.data.model.User;
//import com.wildcat.db.data.model.Curve;
//import com.wildcat.db.data.model.curve.kind.Raw;
//import com.wildcat.db.data.model.curve.type.Fid;
//import com.wildcat.db.data.model.curve.type.Time;
import com.wildcat.db.mongodb.DbClient;
import com.wildcat.db.mongodb.active.record.CurveActiveRecord;
import com.wildcat.db.mongodb.active.record.SampleActiveRecord;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.tylproject.vaadin.addon.MongoContainer;
//import com.wildcat.db.mongodb.collection.DbCollectionCurves;
//import com.wildcat.db.mongodb.collection.DbCollectionSamples;
//import com.wildcat.db.mongodb.collection.DbCollectionUsers;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateMongoDB {
    public static void main(String[] args) throws UnknownHostException {
        MongoOperations operations = DbClient.getOperations();
        operations.dropCollection(Sample.class);
        operations.dropCollection(Curve.class);

        ActiveRecordFactory activeRecordFactory = ActiveRecordFactory.getInstance();
        activeRecordFactory.registerActiveRecord(Sample.class, new SampleActiveRecord());
        activeRecordFactory.registerActiveRecord(Curve.class, new CurveActiveRecord());

        Sample sample = new Sample();
        sample.setName("WTA");
        sample.setDepth(1000.0);
        sample.setType(Sample.Type.BACKGROUND);
        ActiveRecordFactory.getInstance().getActiveRecord(Sample.class).wrap(sample).save();

//        DbClient.get();
//
//        MongoOperations mongoOperations = new MongoTemplate(DbClient.get(), DbClient.dbName);

        //create some curves

        List<Double> data = new ArrayList<>(100);
        for (int i = 0; i < 100; ++i) {
            data.add(i * 1.);
        }

        Curve curve = new Curve();
        curve.setType(Curve.Type.TIME);
        curve.setKind(Curve.Kind.ORIGINAL);
        curve.setData(data);
        curve.setSampleId(sample.getId());

        ActiveRecordFactory.getInstance().getActiveRecord(Curve.class).wrap(curve).save();

//        mongoOperations.save(curve);

        /*
        MongoContainer<Sample> container = MongoContainer.Builder
                .forEntity(Sample.class, mongoOperations)
                .withProperty("name", String.class)
                .build();
        */

        //sample.addCurve(curve);

        //mongoOperations.save(sample);


        //ObjectId id = container.addEntity(sample);
        //System.out.println(id.toString());

        //List<Sample> samples = mongoOperations.findAll(Sample.class);
        //System.out.println("samples count: " + samples.size());



        //List<Sample> samples = mongoOperations.findAll(Sample.class, "samples");
        //System.out.println(samples.size());

        /*
        List<Sample> samples = DbCollectionSamples.getInstance().findAll();


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


        for (int s = 0; s < 100; ++s) {

            List<Double> timeData = new ArrayList<>();
            List<Double> fidData = new ArrayList<>();

            for (int i = 0; i < 10000; ++i) {
                timeData.add(i / 10.0);
                fidData.add(i * i * 1.0);
            }

            Curve time = new Curve();
            time.setType(Curve.Type.TIME);
            time.setData(timeData);
            DbCollectionCurves.get().insertOne(time);

            Curve fid = new Curve();
            fid.setType(Curve.Type.FID);
            fid.setData(fidData);
            DbCollectionCurves.get().insertOne(fid);

            Sample sample1 = new Sample();
            sample1.setName("WTA");
            sample1.setOperator(mnogono);
            sample1.setRawCurve(time);
            sample1.setRawCurve(fid);

            DbCollectionSamples.get().insertOne(sample1);
        }
            */

    }
}
