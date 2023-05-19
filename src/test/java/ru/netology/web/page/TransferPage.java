package ru.netology.web.page;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TransferPage {
    private SelenideElement amount = $ ( "[data-test-id=amount] input");
    private SelenideElement cardFrom = $ ( "[data-test-id=from] input");
    private SelenideElement cardTo = $ ( "[data-test-id=to] input");
    private SelenideElement depositButton = $ ( "[data-test-id=action-transfer]");
    private SelenideElement messageError = $ ( "[data-test-id=error-notification]");
    private SelenideElement cancelButton = $ ( "[data-test-id=action-cancel]");

    public TransferPage(){
       $x("//*[contains(text(), 'Пополнение карты')]").shouldBe(Condition.visible);
    }
    public DashboardPage deposit(int depositAmount, String sourceCard){
        amount.sendKeys(Keys.CONTROL+"A");
        amount.sendKeys(Keys.DELETE);
        amount.setValue(Integer.toString(depositAmount));
        cardFrom.sendKeys(Keys.CONTROL+"A");
        cardFrom.sendKeys(Keys.DELETE);
        cardFrom.setValue(sourceCard);
        depositButton.click();
        return new DashboardPage();
    }

}
