package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement refillFirstBalance = $("[data-test-id=action-deposit](0)");

    private SelenideElement refillSecondBalance = $("[data-test-id=action-deposit](1)");
    private SelenideElement messageError = $("[data-test-id=error-notification]");
    private SelenideElement reloadButton = $("[data-test-id=action-reload]");

    public DashboardPage() {

        heading.shouldBe(visible);
    }
}
