package main;
import fit.*;
import java.util.concurrent.ConcurrentHashMap;
class Main
{
    CoefficientFit jiHeld;
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
    ConcurrentHashMap<String, double[]> q = new ConcurrentHashMap<String, double[]>();

    public static fitFunc jiHeldFit = new fitFunc() {

        @Override
        public void run(ConcurrentHashMap<String, double[]> q, String independent, String dependent) {
            double[] dep;
            double[] ind;
            if((dep = q.get(dependent)) == null || (ind = q.get(independent) ) == null)//if either requested variable is not found, return
            {
                System.out.format("Variable(s) not found: %s = %d, %s = %d", independent, ind == null, dependent, dep == null);
                return;
            }
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
            int len = Z.length;

            double[] a = new double[6];//5 "a" variables for each fit, dependent upon Z
            
            for(int i = 0; i < len; i++)
            {
                double thisZ = Z[i];
                double thisChi = chiE[i];
                a[0] = 0.331*Math.pow(thisZ, (double)5/3) - 1.24*Math.pow(thisZ, (double)4/3) + 2.54*thisZ + 0.4;
                a[1] = 1.46*Math.pow(thisZ, (double)5/3)/(1-etaPar[i]);
                a[2] = Math.pow(thisZ, (double)4/3)*(-0.114*Math.pow(thisZ, (double)1/3) + 0.013);

                etaPar[i] = 1-(thisZ/(1.42*thisZ - 0.065*Math.pow(thisZ, (double)2/3) + 0.35*Math.pow(thisZ, (double)1/3) +0.32));
                etaPerp[i] = 1-(1.46*Math.pow(thisChi, (double)5/3)+a[0]*(1-etaPar[i]))/(Math.pow(thisZ, (double)5/3)*Math.pow(thisChi, (double)5/3) + a[2]*Math.pow(thisChi, (double)4/3)+a[1]*thisChi+a[0]);
                etaNew[i] = (etaPerp[i]-etaPar[i])/thisChi;
                
                
                a[0] = 0.0759*Math.pow(thisZ, (double)8/3)+0.897*Math.pow(thisZ, 2)+2.06*thisZ+1.06;
                a[1] = thisZ*(2.18*Math.pow(thisZ, (double)5/3)+5.31*thisZ+3.73);
                a[2] = Math.pow(thisZ, (double)5/3)*(7.41*thisZ+1.11*Math.pow(thisZ, (double)2/3) - 1.17);
                a[3] = Math.pow(thisZ, 2)*(3.89*Math.pow(thisZ, (double)2/3) - 4.51*Math.pow(thisZ, (double)1/3)+6.76);
                a[4] = Math.pow(thisZ, (double)7/3)*(3.26*Math.pow(thisZ, (double)1/3) +2.81);
                a[5] =1.18*Math.pow(thisZ, (double)5/3)-1.03*Math.pow(thisZ, (double)(4/3))+3.6*thisZ+1.32;
                etaCross[i] = thisChi*Math.pow(thisZ, (double)5/3)*(2.53*thisZ*thisChi+a[0]/a[5])/(Math.pow(thisZ, (double)8/3)*Math.pow(thisChi, (double)5/3) 
                    + a[4]*Math.pow(thisChi, (double)5/3) + a[3]*Math.pow(thisChi, 2) + a[2]*Math.pow(thisChi, (double)5/3) + a[1]*thisChi+a[0]);
                
                
                betaPar[i] = (1.5*thisZ/(thisZ - 0.115*Math.pow(thisZ, (double)2/3) + 0.858*Math.pow(thisZ, (double)1/3) +0.401));
                a[0]
                a[1]
                a[2]
                a[3];
                a[4]
    
                a[0]
                a[1]
                a[2]
                a[3];
                a[4]
            }

            
        }
        @Override
        public void run() {
        }
    };
    public static void main(String[] args)
    {

        CoefficientFit q  = new CoefficientFit(method2);
        q.runFunc();
        r.runFunc();
    }
}