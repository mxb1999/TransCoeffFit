package plot;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.concurrent.*;
public class Plotter extends Application {
    private ConcurrentHashMap<String, double[]> map;
    private String dependent;
    private String independent;

    @Override public void start(Stage stage) {
        System.out.println("Check 2\n");
        System.out.flush();

        stage.setTitle("Line Chart Sample");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Test Plot");
        //defining a series
        XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
        series.setName("My portfolio");
        //populating the series with data
        double[] ind = map.get(independent);
        double[] dep = map.get(dependent);
        int len  = ind.length;
        for(int i = 0; i < len; i++)
        {
            series.getData().add(new XYChart.Data<Number, Number>(ind[i], dep[i]));
        }
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
        stage.setScene(scene);
        stage.show();

        System.out.println("Check 3\n");
        System.out.flush();

    }
    public Plotter(ConcurrentHashMap<String, double[]> newMap, String i, String d)
    {
        super();
        this.map = newMap;
        this.independent = i;
        this.dependent = d;
    }
    public void launchWrap(){
        System.out.println("Check 1\n");
        System.out.flush();
        launch();
        System.out.println("Check 4\n");
        System.out.flush();


    }

}