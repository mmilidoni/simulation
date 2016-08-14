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
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class FramePlot extends JPanel {

    private final XYSeries seriesMedia;
    private final XYSeries seriesVarianza;
    private XYPlot plotMedia;

    public FramePlot() {

        seriesMedia = new XYSeries("Media campionaria (Gordon)");
        seriesVarianza = new XYSeries("Varianza campionaria (Gordon)");

        final XYSeriesCollection datasetMedia = new XYSeriesCollection(this.seriesMedia);
        final XYSeriesCollection datasetVarianza = new XYSeriesCollection(this.seriesVarianza);
        final JFreeChart chartMedia = createChartMedia(datasetMedia);
        final JFreeChart chartVarianza = createChartVarianza(datasetVarianza);

        ChartPanel chartPanelMedia = new ChartPanel(chartMedia);
        final ChartPanel chartPanelVarianza = new ChartPanel(chartVarianza);

        final JPanel content = new JPanel(new BorderLayout());
        content.add(chartPanelMedia);
        content.add(chartPanelVarianza, BorderLayout.EAST);
        chartPanelMedia.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanelVarianza.setPreferredSize(new java.awt.Dimension(500, 270));
        add(content);
        //setContentPane(content);
    }

    private JFreeChart createChartMedia(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createXYLineChart(
                "Media campionaria",
                "n",
                "en",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );
        plotMedia = result.getXYPlot();
        ValueAxis axis = plotMedia.getDomainAxis();
        axis.setAutoRange(true);
        axis.setFixedAutoRange(1500);

        axis = plotMedia.getRangeAxis();
        axis.setAutoRange(true);
        axis.setRange(50, 160);
        plotMedia.getRenderer().setSeriesPaint(0, Color.BLUE);
        return result;
    }

    private JFreeChart createChartVarianza(final XYDataset datasetVarianza) {
        final JFreeChart result = ChartFactory.createXYLineChart(
                "Varianza campionaria",
                "n",
                "vc",
                datasetVarianza,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );
        final XYPlot plot = result.getXYPlot();
        ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        axis.setFixedAutoRange(1500);

        axis = plot.getRangeAxis();
        axis.setAutoRange(true);
        axis.setRange(10, 100);
        plot.getRenderer().setSeriesPaint(0, Color.MAGENTA);
        return result;
    }

    public void addSerieMedia(double x, double y) {
        this.seriesMedia.add(x, y);
    }

    public void addSerieVarianza(double x, double y) {
        this.seriesVarianza.add(x, y);
    }

    public void addMarker(double val, Color colore) {
        ValueMarker mark = new ValueMarker(val, colore, new BasicStroke(1));
        plotMedia.addRangeMarker(mark);
    }

    public void resetSerieMedia() {
        seriesMedia.clear();
        plotMedia.clearRangeMarkers();
    }

    public void resetSerieVarianza() {
        seriesVarianza.clear();
    }

}
