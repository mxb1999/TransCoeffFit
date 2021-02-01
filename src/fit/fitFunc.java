package fit;
import java.util.concurrent.ConcurrentHashMap;
public interface fitFunc extends Runnable 
{
    public void run(ConcurrentHashMap<String, double[]> q, String independent, String dependent);
}