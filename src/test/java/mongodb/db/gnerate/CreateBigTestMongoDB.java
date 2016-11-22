package mongodb.db.gnerate;

import com.wildcat.data.upload.ImporterDataFactory;
import com.wildcat.db.active.record.ActiveRecordFactory;
import com.wildcat.db.data.model.Curve;
import com.wildcat.db.data.model.Sample;
import com.wildcat.db.mongodb.DbClient;
import com.wildcat.db.mongodb.active.record.CurveActiveRecord;
import com.wildcat.db.mongodb.active.record.SampleActiveRecord;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.File;
import java.util.*;

public class CreateBigTestMongoDB {
    static public void main(String[] args) {
        ActiveRecordFactory activeRecordFactory = ActiveRecordFactory.getInstance();
        activeRecordFactory.registerActiveRecord(Sample.class, new SampleActiveRecord());
        activeRecordFactory.registerActiveRecord(Curve.class, new CurveActiveRecord());

        MongoOperations operations = DbClient.getOperations();
        //operations.dropCollection(Sample.class);
        //operations.dropCollection(Curve.class);

        ImporterDataFactory.getInstance().importDataFile(new File("F:\\W\\hawk-eye\\src\\test\\test_160K_A_BACKGROUND.json"));

        SampleActiveRecord sampleActiveRecord = (SampleActiveRecord) activeRecordFactory.getActiveRecord(Sample.class);
        //List<Sample> samples = DbClient.getOperations().findAll(Sample.class);
        //Sample sample = samples.get(0);
        Query query = new Query();
        query.addCriteria(new Criteria("_id").ne(null));
        Sample sample = operations.findOne(query, Sample.class);

        List<Curve> curves = sampleActiveRecord.wrap(sample).getCurves();

        Curve fid = sampleActiveRecord.wrap(sample).getCurve(Curve.Type.FID, Curve.Kind.ORIGINAL);
        List<Double> fidData = fid.getData();

        CurveActiveRecord curveActiveRecord = (CurveActiveRecord) activeRecordFactory.getActiveRecord(Curve.class);

        //create samples with curves
        double n = 100000;
        for (double itSample = 0; itSample < n; ++itSample) {
            if (itSample % 100 == 0) {
                System.out.println(itSample / n);
            }
            Sample newSample = new Sample();
            newSample.setName(UUID.randomUUID().toString());

            sampleActiveRecord.wrap(newSample).save();

            for (Curve curve : curves) {
                List<Double> copyCurveData = new ArrayList<>(curve.getData().size());
                for (Double d : curve.getData()) {
                    copyCurveData.add(d);
                }

                //modify curve to make it litle change
                /*
                Random rand = new Random();
                if (curve.getType() == Curve.Type.FID && curve.getKind() == Curve.Kind.ORIGINAL) {
                    for (int i = 0; i < copyCurveData.size(); ++i) {
                        copyCurveData.set(i, copyCurveData.get(i) * rand.nextDouble());
                    }
                }
                */

                Curve copyCurve = new Curve();
                copyCurve.setType(curve.getType());
                copyCurve.setKind(curve.getKind());
                copyCurve.setSampleId(newSample.getId());
                copyCurve.setData(copyCurveData);

                curveActiveRecord.wrap(copyCurve).save();
            }
        }
    }
}
