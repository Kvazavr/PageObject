package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ТransferPage {
    private SelenideElement amount = $ ( "[data-test-id=amount]");
    private SelenideElement cardFrom = $ ( "[data-test-id=from]");
    private SelenideElement cardTo = $ ( "[data-test-id=to]");
    private SelenideElement refillButton = $ ( "[data-test-id=action-transfer]");
    private SelenideElement messageError = $ ( "[data-test-id=error-notification]");
    private SelenideElement cancelButton = $ ( "[data-test-id=action-cancel]");

    public ТransferPage(){
       $x("//*[contains(text(), 'Пополнение карты')]").shouldBe(Condition.visible);
    }

}
