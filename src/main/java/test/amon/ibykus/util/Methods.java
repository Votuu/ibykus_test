package test.amon.ibykus.util;

import com.google.gson.JsonObject;
import org.openqa.selenium.support.Color;

import static com.codeborne.selenide.Selenide.$x;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Methods {

    public static String optionalGet(JsonObject object, String key) {
        if(object.keySet().contains(key)) {
            return object.get(key).getAsString();
        }
        throw new NullPointerException(key + " is not contain in color parser");
    }

    public static void color(JsonObject object) {
        assertThat(Color.fromString($x(optionalGet(object, "xpath")).getCssValue(optionalGet(object, "css-value"))).asHex(), equalTo(optionalGet(object, "value")));
    }
    public static void text(JsonObject object) {
        assertThat($x(optionalGet(object, "xpath")).getAttribute("innerHTML"), equalTo(optionalGet(object, "value")));
    }
    public static void click(String xpath) {
        $x(xpath).click();
    }
}
