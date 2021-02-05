package misc;

public class customMath {
    public static double[] spanNums(double start, double stop, int num)
    {
        double[] result = new double[num];
        double del = (stop-start)/(num-1);
        double acc = start;
        result[num-1] = stop;
        for(int i = 0; i < num-1; i++)
        {
            result[i] = acc;
            acc+=del;
        }
        return result;
    }
}
