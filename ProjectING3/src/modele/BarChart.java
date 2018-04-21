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
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;



/**
 *
 * @author micha
 */
public class BarChart extends JPanel{
    public String title;
    public String datatype;
    
    public BarChart(String title, ArrayList<String> a, ArrayList<Float> b, String datatype) {
        
        this.title = title;
        this.datatype = datatype;
        JFreeChart barChart = ChartFactory.createBarChart(
                title, 
                "Salaire", 
                "€", 
                createDataset(a, b, datatype));
        
        ChartPanel chartPanel = new ChartPanel( barChart );
        this.add(chartPanel);
        this.setSize(500, 500);
        this.setVisible(true);
        
    }
    
     private static CategoryDataset  createDataset( ArrayList<String> a, ArrayList<Float> b, String g) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
         for (int i = 0; i < a.size(); i++) {
              dataset.addValue(b.get(i), a.get(i), g);
         }
        return dataset;        
    }
}
