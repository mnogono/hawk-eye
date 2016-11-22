package com.wildcat.ui.image.source;

import ChartDirector.XYChart;
import com.vaadin.server.StreamResource;
import com.wildcat.db.active.record.ActiveRecordFactory;
import com.wildcat.db.data.model.Curve;
import com.wildcat.db.data.model.Sample;
import com.wildcat.db.mongodb.active.record.SampleActiveRecord;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SampleImageSource implements StreamResource.StreamSource {
    private Sample sample;

    public SampleImageSource(Sample sample) {
        this.sample = sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }

    @Override
    public InputStream getStream() {
        if (sample == null) {
            return null;
        }

        SampleActiveRecord sampleActiveRecord = (SampleActiveRecord) ActiveRecordFactory.getInstance().getActiveRecord(Sample.class).wrap(sample);
        Curve time = sampleActiveRecord.getCurve(Curve.Type.TIME, Curve.Kind.ORIGINAL);
        Curve fid = sampleActiveRecord.getCurve(Curve.Type.FID, Curve.Kind.ORIGINAL);

        List<Double> timeData = time.getData();
        String []labels = new String[timeData.size()];
        for (int i = 0; i < timeData.size(); ++i) {
            labels[i] = String.valueOf(timeData.get(i));
        }

        List<Double> fidData = fid.getData();
        double []data = new double[fidData.size()];
        for (int i = 0; i < fidData.size(); ++i) {
            data[i] = fidData.get(i);
        }

        // Create a XYChart object of size 250 x 250 pixels
        XYChart c = new XYChart(650, 450);

        // Set the plotarea at (30, 20) and of size 200 x 200 pixels
        c.setPlotArea(50, 50, 600, 400);

        // Add a line chart layer using the given data
        c.addLineLayer(data);

        // Set the labels on the x axis.
        c.xAxis().setLabels(labels);

        // Display 1 out of 3 labels on the x-axis.
        c.xAxis().setLabelStep(3);

        Image image = c.makeImage();

        BufferedImage bufferedImage = toBufferedImage(image);
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Converts a given Image into a BufferedImage
     *
     * @param image The Image to be converted
     * @return The converted BufferedImage
     */
    public static java.awt.image.BufferedImage toBufferedImage(Image image)
    {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        java.awt.Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(image, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}
