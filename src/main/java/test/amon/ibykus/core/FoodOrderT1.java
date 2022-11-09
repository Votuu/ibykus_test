package test.amon.ibykus.core;

import com.codeborne.selenide.Condition;
import test.amon.ibykus.ibykus.IbykusCore;

import static com.codeborne.selenide.Selenide.$x;

public class FoodOrderT1 extends IbykusCore {

    public FoodOrderT1() {
        super("!removed");
    }

    @Override
    public void start() {
        fromJson();

        $x("/html/body/form/div[2]/div[7]/div[2]/div[3]/span/p[1]/span").shouldBe(Condition.visible).shouldNot(Condition.empty);
        $x("/html/body/form/div[2]/div[7]/div[2]/div[1]/div[1]/img").shouldBe(Condition.visible);
        $x("/html/body/form/div[2]/div[7]/div[2]/div[1]/div[2]/img").shouldBe(Condition.visible).shouldBe(Condition.image);
    }
}
