import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class test
{
    public static void main(String[] args) throws Exception
    {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String foo = "2+3+3*4";
        System.out.println(engine.eval(foo));
    }
}