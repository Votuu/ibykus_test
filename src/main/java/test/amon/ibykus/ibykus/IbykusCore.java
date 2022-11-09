package test.amon.ibykus.ibykus;

import com.google.gson.*;
import org.openqa.selenium.json.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.codeborne.selenide.Selenide.open;
import static test.amon.ibykus.util.Math.msToSeconds;
import static test.amon.ibykus.util.Methods.*;

public abstract class IbykusCore {

    private static Map<String, IbykusCore> coreMap = new HashMap<>();

    public static IbykusCore createCore(String coreId, IbykusCore core) {
        if(!coreMap.containsKey(coreId.toLowerCase())) {
            return coreMap.put(coreId.toLowerCase(), core);
        }
        throw new RuntimeException("Core " + coreId + " is already registered");
    }
    public static IbykusCore core(String coreId) {
        if(coreMap.containsKey(coreId.toLowerCase())) {
            return coreMap.get(coreId.toLowerCase());
        }
        throw new NullPointerException("Core " + coreId + " was not found");
    }
    public static String core(IbykusCore core) {
        for(String k : coreMap.keySet()) {
            if(coreMap.get(k) == core) {
                return k;
            }
        }
        throw new NullPointerException("cant find core by " + core.getClass().getName());
    }

    public static Collection<IbykusCore> coreList() {
        return coreMap.values();
    }
    public static Set<String> coreName() {
        return coreMap.keySet();
    }

    private final String connectionString;

    public IbykusCore(String connectionString) {
        this.connectionString = connectionString;
    }

    public void preBuild() {
        long l1 = System.currentTimeMillis();
        open(connectionString);
        System.out.println("Pre built in " + msToSeconds((System.currentTimeMillis() - l1)) + "s");
    }

    public abstract void start();

    public void fromJson() {
        try {
            JsonObject object = JsonParser.parseReader(new FileReader(getClass().getResource("/ibykus.json").getFile(), StandardCharsets.UTF_8)).getAsJsonObject();

            JsonArray array = object.getAsJsonArray(core(this));
            Type listType = new TypeToken<List<JsonObject>>() {}.getType();
            List<JsonObject> list = new Gson().fromJson(array, listType);

            for(JsonObject o : list) {
                function(o.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void function(String json) {
        JsonObject object = JsonParser.parseString(json).getAsJsonObject();

        String type = object.get("type").getAsString();

        if(type.equalsIgnoreCase("color")) {
            color(object);
        } else if(type.equalsIgnoreCase("text")) {
            text(object);
        }
    }
}
