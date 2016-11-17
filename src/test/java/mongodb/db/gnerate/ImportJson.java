package mongodb.db.gnerate;

import com.wildcat.data.upload.ImporterDataFactory;
import com.wildcat.db.data.model.Curve;
import com.wildcat.db.data.model.Sample;
import com.wildcat.db.mongodb.DbClient;
import org.springframework.data.mongodb.core.MongoOperations;

import java.io.File;
import java.net.UnknownHostException;
import java.util.List;

public class ImportJson {
    public static void main(String[] args) throws UnknownHostException {
        MongoOperations operations = DbClient.getOperations();
        operations.dropCollection(Sample.class);
        operations.dropCollection(Curve.class);

        List<Sample> samples = operations.findAll(Sample.class);
        System.out.println(samples.size());

        boolean success = ImporterDataFactory.getInstance().importDataFile(new File("F:\\W\\hawk-eye\\src\\test\\test_160K_A_STANDARD.json"));

        samples = operations.findAll(Sample.class);
        System.out.println(samples.size());
    }
}
