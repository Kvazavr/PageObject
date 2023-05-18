package ru.netology.web.test;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV1;
import ru.netology.web.page.ТransferPage;
import ru.netology.web.page.LoginPageV2;
import ru.netology.web.page.LoginPageV3;

import static com.codeborne.selenide.Selenide.*;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldReturnBalanceFirstCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        int balance = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(10000, balance);

    }

    @Test
    void shouldReturnBalanceSecondCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        int balance = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(10000, balance);

    }

    @Test
    void refillFirstCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        $$("[data-test-id=action-deposit]").get(0).click();
        $x("//*[contains(text(), 'Пополнение карты')]").shouldBe(Condition.visible);
        $("[data-test-id=to] .input__control").getAttribute("**** **** **** 0001");
        $("[data-test-id=amount] input").setValue("2000");
        $("[data-test-id=from] input").setValue("5559 0000 0000 0002");
        $("[data-test-id=action-transfer]").click();
        $("h1").shouldHave(Condition.text("Ваши карты"));



    }
    @Test
    void emptyFromField() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        $$("[data-test-id=action-deposit]").get(0).click();
        $x("//*[contains(text(), 'Пополнение карты')]").shouldBe(Condition.visible);
        $("[data-test-id=to] .input__control").getAttribute("**** **** **** 0001");
        $("[data-test-id=amount] input").setValue("2000");
        $("[data-test-id=from] input").setValue("");
        $("[data-test-id=action-transfer]").click();
        $("[data-test-id=error-notification]").shouldHave(Condition.text("Ошибка!")).shouldBe(Condition.visible);
    }
       @Test
    void transferAllAmount() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        $$("[data-test-id=action-deposit]").get(0).click();
        $x("//*[contains(text(), 'Пополнение карты')]").shouldBe(Condition.visible);
        $("[data-test-id=to] .input__control").getAttribute("**** **** **** 0001");
        int balance = dashboardPage.getSecondCardBalance();

        $("[data-test-id=amount] input").setValue();
        $("[data-test-id=from] input").setValue("5559 0000 0000 0002");
        $("[data-test-id=action-transfer]").click();
        $("h1").shouldHave(Condition.text("Ваши карты"));
    }

}
