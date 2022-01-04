package lu.pata.js;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Application {
    public static void main(String[] args) throws IOException {
        //https://github.com/mozilla/rhino/tree/master/examples

        Context cx = Context.enter();
        cx.setLanguageVersion(Context.VERSION_1_2);
        Scriptable scope = cx.initStandardObjects();

        String script= new String(Files.readAllBytes(Path.of("script.js")));
        ScriptableObject.putProperty(scope, "program", "SPA2");
        ScriptableObject.putProperty(scope, "role", "ADMIN");

        cx.evaluateString(scope, script, "MySource", 1, null);
        Boolean evalResult = (Boolean) scope.get("eval_result", scope);

        System.out.println(evalResult);
    }
}
