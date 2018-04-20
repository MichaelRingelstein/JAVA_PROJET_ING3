/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFrame;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;



/**
 *
 * @author micha
 */
public class PieChart extends JPanel{
    public String title;
    public PieChart(String title, ArrayList<String> a, ArrayList<Integer> b) {
        
        this.title = title;
        JPanel panel = createDemoPanel(a, b, title);
        this.add(panel);
        this.setSize(500, 500);
        this.setVisible(true);
        
    }
    
     private static PieDataset createDataset( ArrayList<String> a, ArrayList<Integer> b) {
        DefaultPieDataset dataset = new DefaultPieDataset();
         for (int i = 0; i < a.size(); i++) {
              dataset.setValue(a.get(i), b.get(i));
         }
        return dataset;        
    }
     
      private static JFreeChart createChart(PieDataset dataset, String title) {
        
        JFreeChart chart = ChartFactory.createPieChart(
            title,  // chart title
            dataset,             // data
            true,               // include legend
            true,
            false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setNoDataMessage("No data available");
        plot.setCircular(true);
        plot.setLabelGap(0.02);
        
        plot.setNoDataMessage("No data available");
        plot.setExplodePercent("One", 0.30);
        
        plot.setSectionPaint("One", Color.BLACK);
        plot.setSectionPaint("Two", Color.WHITE);
        
        plot.setBackgroundPaint(Color.WHITE);
        
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}--{1}--{2}"));
        
        plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
        
        chart.setBorderVisible(false);
        
        chart.setBorderPaint(Color.RED);
        
        chart.setBackgroundPaint(Color.WHITE);
        
        return chart;
        
    }
      
       public static JPanel createDemoPanel(ArrayList<String> a, ArrayList<Integer> b, String title) {
        JFreeChart chart = createChart(createDataset(a, b), title);
        return new ChartPanel(chart);
        
    }
}
