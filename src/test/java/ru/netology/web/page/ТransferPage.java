package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ТransferPage {
    private SelenideElement amount = $ ( "[data-test-id=amount]");
    private SelenideElement cardFrom = $ ( "[data-test-id=from]");
    private SelenideElement cardTo = $ ( "[data-test-id=to]");
    private SelenideElement refillButton = $ ( "[data-test-id=action-transfer]");
    private SelenideElement messageError = $ ( "[data-test-id=error-notification]");
    private SelenideElement cancelButton = $ ( "[data-test-id=action-cancel]");
}
