package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.impl.Html.text;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement refillFirstBalance = $("[data-test-id=action-deposit](0)");

    private SelenideElement refillSecondBalance = $("[data-test-id=action-deposit](1)");
    private SelenideElement messageError = $("[data-test-id=error-notification]");
    private SelenideElement reloadButton = $("[data-test-id=action-reload]");

    public DashboardPage() {

        heading.shouldBe(visible);
    }
    public static DashboardPage getBalanceFirstCard(){
        private ElementsCollection cards = $$(".list__item div");
        private final String balanceStart = "баланс: ";
        private final String balanceFinish = " р.";


        public int getFirstCardBalance() {
            val text = cards.first().text();
            return extractBalance(text);
        }

        private int extractBalance(String text) {
            val start = text.indexOf(balanceStart);
            val finish = text.indexOf(balanceFinish);
            val value = text.substring(start + balanceStart.length(), finish);
            return Integer.parseInt(value);
        }
    }
}
