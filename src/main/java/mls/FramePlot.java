/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 */
import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class FramePlot extends ApplicationFrame {

    private final XYSeries series;

    public FramePlot(final String title, int maxRangeY) {

        super(title);
        series = new XYSeries("Random Data");

        final XYSeriesCollection dataset = new XYSeriesCollection(this.series);
        final JFreeChart chart = createChart(dataset, maxRangeY);

        final ChartPanel chartPanel = new ChartPanel(chart);

        final JPanel content = new JPanel(new BorderLayout());
        content.add(chartPanel);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(content);
    }

    private JFreeChart createChart(final XYDataset dataset, int maxRangeY) {
        final JFreeChart result = ChartFactory.createXYLineChart(
                "Media campionaria",
                "n",
                "en",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        final XYPlot plot = result.getXYPlot();
        ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
       // axis.setFixedAutoRange(60000.0);  // 60 seconds
        axis = plot.getRangeAxis();
        axis.setRange(0.0, maxRangeY);
        return result;
    }

    public void addSerie(double x, double y) {
        this.series.add(x, y);
    }

}
