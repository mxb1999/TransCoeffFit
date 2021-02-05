package main;
import fit.*;
import misc.*;
import java.util.concurrent.ConcurrentHashMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.operator.Operator;
public class Main extends Application
{
    String independent = "Z";
    String dependent = "etaPerp";
    static CoefficientFit jiHeld;
    public static Runnable method2 = new Runnable() {

        @Override
        public void run() {
            System.out.println("2");
        }
    };
    public static Runnable method1 = new Runnable() {

        @Override
        public void run() {
            System.out.println("1");
        }
    };
    public static fitFunc jiHeldFit = new fitFunc() {

        @Override
        public void run(ConcurrentHashMap<String, double[]> q) {
            
            //set up required variables
            //scalar variables
            double[] Z = q.get("Z");
            //set of eta transport coeffs
            double[] etaPar = q.get("etaPar");
            double[] etaPerp = q.get("etaPerp");
            double[] etaNew = q.get("etaNew");
            double[] etaCross = q.get("etaCross");
            //set of beta transport coeffs
            double[] betaPar = q.get("betaPar");
            double[] betaPerp = q.get("betaPerp");
            double[] betaNew = q.get("betaNew");
            double[] betaCross = q.get("betaCross");
            double[] chiE = q.get("chiE");
            int len = Math.max(Z.length, chiE.length);

            double[] a = new double[6];//5 "a" variables for each fit, dependent upon Z
            int zLen = Z.length;
            int chiLen = chiE.length;
            double thisZ = Z[0];
            double thisChi = chiE[0];
            for(int i = 0; i < len; i++)
            {
                if(i < zLen)
                {
                    thisZ = Z[i];
                }
                if(i < chiLen)
                {
                    thisChi = chiE[i];
                }
                System.out.format("%f %f\n", thisZ, thisChi);
                a[0] = 0.331*Math.pow(thisZ, (double)5/3) - 1.24*Math.pow(thisZ, (double)4/3) + 2.54*thisZ + 0.4;
                a[1] = 1.46*Math.pow(thisZ, (double)5/3)/(1-etaPar[i]);
                a[2] = Math.pow(thisZ, (double)4/3)*(-0.114*Math.pow(thisZ, (double)1/3) + 0.013);

                etaPar[i] = 1-(thisZ/(1.42*thisZ - 0.065*Math.pow(thisZ, (double)2/3) + 0.352*Math.pow(thisZ, (double)1/3) +0.32));
                etaPerp[i] = 1-(1.46*Math.pow(thisZ, (double)5/3)*thisChi+a[0]*(1-etaPar[i]))/(Math.pow(thisZ, (double)5/3)*Math.pow(thisChi, (double)5/3) + a[2]*Math.pow(thisChi, (double)4/3)+a[1]*thisChi+a[0]);
                etaNew[i] = (1-etaPar[i])*Math.pow(thisChi, (double)1/3)*(Math.pow(thisZ, (double)5/3)*Math.pow(thisChi, (double)1/3)+a[2])/(Math.pow(thisZ*thisChi, (double)5/3)+a[2]*Math.pow(thisChi, (double)4/3) + a[1]*thisChi+a[0]);//(etaPerp[i]-etaPar[i])/thisChi;
                
                
                a[0] = 0.0759*Math.pow(thisZ, (double)8/3)+0.897*Math.pow(thisZ, 2)+2.06*thisZ+1.06;
                a[1] = thisZ*(2.18*Math.pow(thisZ, (double)5/3)+5.31*thisZ+3.73);
                a[2] = Math.pow(thisZ, (double)5/3)*(7.41*thisZ+1.11*Math.pow(thisZ, (double)2/3) - 1.17);
                a[3] = Math.pow(thisZ, 2)*(3.89*Math.pow(thisZ, (double)2/3) - 4.51*Math.pow(thisZ, (double)1/3)+6.76);
                a[4] = Math.pow(thisZ, (double)7/3)*(3.26*Math.pow(thisZ, (double)1/3) +2.81);
                a[5] =1.18*Math.pow(thisZ, (double)5/3)-1.03*Math.pow(thisZ, (double)(4/3))+3.6*thisZ+1.32;
                etaCross[i] = Math.pow(thisZ, (double)5/3)*(2.53*thisZ*thisChi+a[0]/a[5])/(Math.pow(thisZ, (double)8/3)*Math.pow(thisChi, (double)5/3) 
                    + a[4]*Math.pow(thisChi, (double)5/3) + a[3]*Math.pow(thisChi, 2) + a[2]*Math.pow(thisChi, (double)5/3) + a[1]*thisChi+a[0]);
                
                
                betaPar[i] = (1.5*thisZ/(thisZ - 0.115*Math.pow(thisZ, (double)2/3) + 0.858*Math.pow(thisZ, (double)1/3) +0.401));
                a[0] = 0.288*Math.pow(thisZ, (double)8/3)+1.75*Math.pow(thisZ, 2)+5.09*thisZ-0.322;
                a[1] = 6.33*Math.pow(thisZ, (double)8/3)/betaPar[i];
                a[2] = Math.pow(thisZ, (double)5/3)*(9.4*thisZ+5.43*Math.pow(thisZ, (double)2/3) - 9.67*Math.pow(thisZ, (double)1/3) + 3.06);
                a[3] = Math.pow(thisZ, 2)*(2.62*Math.pow(thisZ, (double)2/3) - 0.704*Math.pow(thisZ, (double)1/3)-0.264);;
                a[4] = Math.pow(thisZ, (double)7/3)*(2.58*Math.pow(thisZ, (double)1/3) +2.62);
                betaPerp[i] = (6.33*Math.pow(thisZ, (double)8/3)*thisChi+a[0]*betaPar[i])/
                    (Math.pow(thisZ, (double)8/3)*Math.pow(thisChi, (double)8/3)+a[4]*Math.pow(thisChi, (double)7/3) + a[3]*Math.pow(thisChi, 2) + a[2]*Math.pow(thisChi, (double)5/3)
                        + a[1]*thisChi + a[0]);
                a[0] = 0.00687*Math.pow(thisZ, 3)+0.0782*Math.pow(thisZ, 2)+0.623*thisZ+0.366;//
                a[1] = thisZ*(0.134*Math.pow(thisZ, 2)+0.977*thisZ+0.17);
                a[2] = Math.pow(thisZ, (double)5/3)*(0.689*Math.pow(thisZ, (double)4/3)+0.377*Math.pow(thisZ, (double)2/3)
                    +3.94*Math.pow(thisZ, (double)1/3) + 0.644);
                a[3] = Math.pow(thisZ, 2)*(-0.109*thisZ+1.33*Math.pow(thisZ, (double)2/3) - 3.80*Math.pow(thisZ, (double)1/3)+0.289);
                a[4] = Math.pow(thisZ, (double)7/3)*(2.46*Math.pow(thisZ, (double)2/3) +0.522);
                a[5] =0.102*Math.pow(thisZ, 2)+0.072*Math.pow(thisZ, (double)(1/3))+0.746*thisZ+0.211;
                betaCross[i] = (Math.pow(thisZ,2)*(1.5*thisZ*thisChi+a[0]/a[5]))/
                    (Math.pow(thisZ*thisChi, 3)+a[4]*Math.pow(thisChi, (double)7/3) + a[3]*Math.pow(thisChi, 2) + a[2]*Math.pow(thisChi, (double)5/3) + a[1]*thisChi + a[0]);
                betaNew[i] = (betaPar[i]-betaPerp[i])/thisChi;
            }

            
        }
        @Override
        public void run() {
        }
    };
    @Override public void start(Stage stage) {
        System.out.println("Check 2\n");
        System.out.flush();
        ConcurrentHashMap<String, double[]> map = jiHeld.getMap();
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
    public static void main(String[] args)
    {
        int N = 100;
        String[] varlist = {"Z", "etaPar", "etaPerp", "etaNew", "etaCross","betaPar", "betaPerp", "betaNew", "betaCross", "chiE"};
        double[] z = customMath.spanNums(1,100, N);//{1.0};
        double[] chi = {10.0};//customMath.spanNums(10.0/N,10.0, N);
        double[][] vars = {z, new double[N], new double[N],new double[N],new double[N],new double[N],new double[N],new double[N],new double[N],chi};
        jiHeld  = new CoefficientFit(jiHeldFit, varlist, vars);
        jiHeld.runFunc();
        int slen = varlist.length;
        for(int i = 0; i < slen; i++)
        {
            double[] arr = vars[i];
            System.out.format("%s:{%f", varlist[i], arr[0]);
            int innerlen = arr.length;
            for(int j = 1; j < innerlen; j++)
            {
                System.out.format(", %f", arr[j]);
            }
            System.out.format("}\n");

        }
        launch();
    }
}