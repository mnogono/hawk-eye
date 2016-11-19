package com.wildcat.data.upload;

import com.wildcat.db.data.model.Curve;
import com.wildcat.db.data.model.Sample;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

public class ImporterSample extends AbstractImporterData {
    public ImporterSample(ImporterPersistentLayer persistentLayer) {
        super(persistentLayer);
    }

    @Override
    public boolean importDataFile(File dataFile) {
        boolean success = false;
        try {
            try (JsonReader jsonReader = Json.createReader(new FileInputStream(dataFile))) {
                JsonObject json = jsonReader.readObject();
                String recordName = json.getString("record_name");
                do {
                    success = recordName.equals("#sample");
                    if (!success) {
                        break;
                    }

                    Sample sample = new Sample();

                    //read some base sample data
                    sample.setName(json.getString("sample_id"));
                    sample.setDepth(json.getInt("sample_depth", 0));
                    //sample.setDepth(json.getJsonNumber("sample_depth").doubleValue());

                    persistentLayer.save(sample);

                    //List<Curve> curves = new LinkedList<>();

                    //read curves
                    JsonArray jsonCurves = json.getJsonArray("curves");
                    for (int itCurve = 0; itCurve < jsonCurves.size(); ++itCurve) {
                        JsonObject jsonCurve = jsonCurves.getJsonObject(itCurve);

                        String dataBase64 = jsonCurve.getString("curve_data");
                        byte []doubleBytes = Base64.getDecoder().decode(dataBase64);
                        ByteBuffer bb = ByteBuffer.wrap(doubleBytes).order(ByteOrder.LITTLE_ENDIAN);
                        List<Double> data = new ArrayList<>(doubleBytes.length / 8);
                        while (bb.position() != doubleBytes.length) {
                            Double d = bb.getDouble();
                            data.add(d);
                        }

                        Curve curve = new Curve();
                        curve.setKind(Curve.Kind.fromValue(jsonCurve.getJsonNumber("curve_mode").intValue()));
                        curve.setType(Curve.Type.fromValue(jsonCurve.getJsonNumber("curve_type").intValue()));
                        curve.setData(data);
                        curve.setSampleId(sample.getId());

                        //curves.add(curve);

                        persistentLayer.save(curve);
                    }
                    break;
                } while (true);
            }
        } catch (Exception e) {
            success = false;
        }

        return success;
    }
}
