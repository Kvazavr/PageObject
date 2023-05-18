package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
//    private SelenideElement refillFirstBalance = $("[data-test-id=action-deposit](0)");
//
//    private SelenideElement refillSecondBalance = $("[data-test-id=action-deposit](1)");
//    private SelenideElement messageError = $("[data-test-id=error-notification]");
//    private SelenideElement reloadButton = $("[data-test-id=action-reload]");

    public DashboardPage() {

        heading.shouldBe(visible);
    }

    public int getFirstCardBalance() {
        val text = cards.first().text();
        int balance = extractBalance(text);
        return balance;
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public int getSecondCardBalance() {
        val text = cards.get(1).text();
        int balance = extractBalance(text);
        return balance;
    }
    public TransferPage depositFirstCard() {
        $$("[data-test-id=action-deposit]").get(0).click();
        return new TransferPage();
    }
    public TransferPage depositSecondCard() {
        $$("[data-test-id=action-deposit]").get(1).click();
        return new TransferPage();
    }
    public void setInitialBalances(){

        int currentBalance = getFirstCardBalance();
        int depositBalance = 10000-currentBalance;
        if (depositBalance > 0){
            depositFirstCard().deposit(depositBalance, DataHelper.secondCardNumber());
        } else if (depositBalance < 0) {
            depositSecondCard().deposit(-depositBalance, DataHelper.firstCardNumber());
        }
    }

}
