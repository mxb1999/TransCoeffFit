package fit;
import java.util.concurrent.ConcurrentHashMap;

public class CoefficientFit
{
    private fitFunc func;
    private ConcurrentHashMap<String, double[]> map;//dereference variable values based upon name, essential for custom functionality. Assuming (relatively) low numbers of variables
    private String ind;//store the name of the independent variable(s) in fit
    private String dep;//store the name of the dependent variable(s) in fit
    public void runFunc()//run the fitFunc function
    {
        func.run(map, ind, dep);
    }
    public CoefficientFit(fitFunc f, ConcurrentHashMap<String, double[]> m, String i, String d)//Constructor
    {
        this.func = f;
        this.map = m;
        this.ind = i;
        this.dep = d;
        
    }
}