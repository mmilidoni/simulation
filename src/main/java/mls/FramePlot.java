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

public class FramePlot extends ApplicationFrame {

    private final XYSeries seriesMedia;
    private final XYSeries seriesVarianza;

    public FramePlot(final String title, int maxRangeY) {

        super(title);
        seriesMedia = new XYSeries(title);
        seriesVarianza = new XYSeries(title);

        final XYSeriesCollection datasetMedia = new XYSeriesCollection(this.seriesMedia);
        final XYSeriesCollection datasetVarianza = new XYSeriesCollection(this.seriesVarianza);
        final JFreeChart chartMedia = createChartMedia(datasetMedia, maxRangeY);
        final JFreeChart chartVarianza = createChartVarianza(datasetVarianza, maxRangeY);

        final ChartPanel chartPanelMedia = new ChartPanel(chartMedia);
        final ChartPanel chartPanelVarianza = new ChartPanel(chartVarianza);

        final JPanel content = new JPanel(new BorderLayout());
        content.add(chartPanelMedia);
        content.add(chartPanelVarianza, BorderLayout.SOUTH);
        chartPanelMedia.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanelVarianza.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(content);
    }

    private JFreeChart createChartMedia(final XYDataset dataset, int maxRangeY) {
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

    private JFreeChart createChartVarianza(final XYDataset datasetVarianza, int maxRangeY) {
        final JFreeChart result = ChartFactory.createXYLineChart(
                "Varianza campionaria",
                "n",
                "vc",
                datasetVarianza,
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

    public void addSerieMedia(double x, double y) {
        this.seriesMedia.add(x, y);
    }

    public void addSerieVarianza(double x, double y) {
        this.seriesVarianza.add(x, y);
    }

}
