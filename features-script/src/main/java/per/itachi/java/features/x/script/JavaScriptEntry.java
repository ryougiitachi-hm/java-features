package per.itachi.java.features.x.script;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import lombok.extern.slf4j.Slf4j;

/**
 * https://www.graalvm.org/latest/reference-manual/js/JavaInteroperability/
 * */
@Slf4j
public class JavaScriptEntry {

    public static void main(String[] args) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine scriptEngine = manager.getEngineByName("JavaScript");
//        Bindings bindings = scriptEngine.createBindings();
//        executeNative(scriptEngine);
        executeDomWindow(scriptEngine);
    }

    private static void executeNative(ScriptEngine scriptEngine) {
        try {
            Object result = scriptEngine.eval("[]");
            log.info("result={}", result);
        }
        catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    private static void executeDomWindow(ScriptEngine scriptEngine) {
        Bindings bindings = scriptEngine.getBindings(ScriptContext.ENGINE_SCOPE);
        bindings.put("window", new HashMap<String, Object>());
        try(InputStream is = JavaScriptEntry.class.getClassLoader().getResourceAsStream("window__INITIAL_STATE__.js");
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8) ) {
            Object result = scriptEngine.eval(isr);
            log.info("bindings={}", bindings);
        }
        catch (IOException|ScriptException e) {
            log.error("Error occurred. ", e);
        }
    }
}
