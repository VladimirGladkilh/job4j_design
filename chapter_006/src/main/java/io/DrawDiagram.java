package io;

import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.util.Rotation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class DrawDiagram {
    public DrawDiagram(String chartTitle, PieDataset dataset) throws IOException {
        JFreeChart chart = createChart(dataset,chartTitle);

        int width=640;
        int height=480;
        BufferedImage bufferedImage = chart.createBufferedImage(width, height);
        String s = imgToBase64String(bufferedImage, "myFIle.png");
        System.out.println(s);
        File BarChart=new File("chart.png");
        try {
            ChartUtils.saveChartAsPNG(BarChart, chart, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String imgToBase64String(final RenderedImage img, final String formatName)
    {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();

        try
        {
            ImageIO.write(img, formatName, os);
            return Base64.getEncoder().encodeToString(os.toByteArray());
        }
        catch (final IOException ioe)
        {
            throw new UncheckedIOException(ioe);
        }
    }
    private PieDataset createDataset(HashMap<String, Double> va) {
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("First", 24.55d);
        result.setValue("Second", 75.45d);
        return result;
    }

    private static JFreeChart createChart(PieDataset dataset, String title)
    {
        JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, true, false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setStartAngle(90);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setBackgroundPaint(Color.WHITE);


        return chart;
    }

    public static void main(String[] args) throws IOException {
        double value = 0.777774;
        double scale = Math.pow(10, 4);
        double result = Math.ceil(value * scale) / 100;
        System.out.println((result)); //34.778
       /* DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("НДФЛ", 18d);
        dataset.setValue("ПФР", 35d);
        dataset.setValue("Прочее", 70.5d);
        //new DrawDiagram("Налоги", dataset);
        JFreeChart chart = createChart(dataset,"chartTitle");

        int width=640;
        int height=480;
        BufferedImage bufferedImage = chart.createBufferedImage(width, height);
        String s = imgToBase64String(bufferedImage, "png");
        System.out.println("s=" + s);
        File BarChart=new File("chart.png");
        try {
            ChartUtils.saveChartAsPNG(BarChart, chart, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
