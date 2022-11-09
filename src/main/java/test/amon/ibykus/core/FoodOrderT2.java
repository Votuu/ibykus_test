package test.amon.ibykus.core;

import com.codeborne.selenide.Condition;
import test.amon.ibykus.ibykus.IbykusCore;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static test.amon.ibykus.util.Methods.click;

public class FoodOrderT2 extends IbykusCore {

    public FoodOrderT2() {
        super("!removed");
    }

    @Override
    public void start() {
        fromJson();

        login();

        click("//*[@id=\"xi-btn-1_btn0\"]");

        login();

        click("//*[@id=\"xi-btn-1_btn1\"]");

        assertThat($x("//*[@id=\"xi-txt-9\"]").getAttribute("innerHTML"), equalTo("Summe:&nbsp;0.00 €"));

        click("//*[@id=\"xi-btn-4_btn1\"]");
    }

    private void login() {
        $x("//*[@id=\"xi-tf-2\"]").val("!removed");
        $x("//*[@id=\"xi-btn-2_btn0\"]").click();

        $x("//*[@id=\"xi-txt-16\"]").shouldBe(Condition.text("!remove"));
    }
}
