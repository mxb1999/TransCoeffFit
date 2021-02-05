package fit;
import java.util.concurrent.ConcurrentHashMap;

public class CoefficientFit
{
    private fitFunc func;
    private ConcurrentHashMap<String, double[]> map;//dereference variable values based upon name, essential for custom functionality. Assuming (relatively) low numbers of variables
    //private String ind;//store the name of the independent variable(s) in fit
    //private String dep;//store the name of the dependent variable(s) in fit
    public void runFunc()//run the fitFunc function
    {
        func.run(map);
    }


    public CoefficientFit(fitFunc f, String[] names, double[][] values)//constructor w/o hashmap
    {
        if(names == null || values == null)
        {
            System.out.println("Invalid length provided");
            return;
        }
        int len = names.length;
        if(len != values.length)
        {
            System.out.println("Number of variables and number of value fields do not match");
            return;
        }
        ConcurrentHashMap<String, double[]> newMap = new ConcurrentHashMap<String, double[]>();
        for(int i = 0; i < len; i++)
        {
            newMap.put(names[i], values[i]);
        }
        this.map = newMap;
        this.func = f;
    }
    public ConcurrentHashMap<String, double[]> getMap(){
        return this.map;
    }
    public CoefficientFit(fitFunc f, ConcurrentHashMap<String, double[]> m, String i, String d)//Constructor with preexisting hashmap
    {
        this.func = f;
        this.map = m;
        //this.ind = i;
        //this.dep = d;
        
    }
}