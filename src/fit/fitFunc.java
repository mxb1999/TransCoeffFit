package fit;
import java.util.concurrent.ConcurrentHashMap;
//interface to allow for runnable objects with arguments, used for passing data for fits
public interface fitFunc extends Runnable 
{
    public void run(ConcurrentHashMap<String, double[]> q);
}