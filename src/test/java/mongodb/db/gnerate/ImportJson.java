package mongodb.db.gnerate;

import com.wildcat.data.upload.ImporterDataFactory;
import com.wildcat.db.active.record.ActiveRecordFactory;
import com.wildcat.db.data.model.Curve;
import com.wildcat.db.data.model.Sample;
import com.wildcat.db.mongodb.DbClient;
import com.wildcat.db.mongodb.active.record.CurveActiveRecord;
import com.wildcat.db.mongodb.active.record.SampleActiveRecord;
import org.springframework.data.mongodb.core.MongoOperations;

import java.io.File;
import java.net.UnknownHostException;
import java.util.List;

public class ImportJson {
    public static void main(String[] args) throws UnknownHostException {
        ActiveRecordFactory activeRecordFactory = ActiveRecordFactory.getInstance();
        activeRecordFactory.registerActiveRecord(Sample.class, new SampleActiveRecord());
        activeRecordFactory.registerActiveRecord(Curve.class, new CurveActiveRecord());

        MongoOperations operations = DbClient.getOperations();
        operations.dropCollection(Sample.class);
        operations.dropCollection(Curve.class);

        List<Sample> samples = operations.findAll(Sample.class);
        System.out.println(samples.size());

        ImporterDataFactory.getInstance().importDataFile(new File("F:\\W\\hawk-eye\\src\\test\\test_160K_A_BACKGROUND.json"));
        ImporterDataFactory.getInstance().importDataFile(new File("F:\\W\\hawk-eye\\src\\test\\test_160K_A_STANDARD.json"));
        ImporterDataFactory.getInstance().importDataFile(new File("F:\\W\\hawk-eye\\src\\test\\test_160K_B_QUALITY.json"));
        ImporterDataFactory.getInstance().importDataFile(new File("F:\\W\\hawk-eye\\src\\test\\test_WT_A_STANDARD.json"));

        samples = operations.findAll(Sample.class);
        System.out.println(samples.size());

        SampleActiveRecord sampleActiveRecord = (SampleActiveRecord) ActiveRecordFactory.getInstance().getActiveRecord(Sample.class);
        List<Curve> curves = sampleActiveRecord.getCurves();

        System.out.println(curves.size());
    }
}
