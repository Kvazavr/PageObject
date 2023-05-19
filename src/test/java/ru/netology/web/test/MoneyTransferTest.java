package ru.netology.web.test;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV1;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

class MoneyTransferTest {

    @Test
    void shouldReturnBalanceFirstCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.setInitialBalances();
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
        dashboardPage.setInitialBalances();
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
        dashboardPage.setInitialBalances();
        int actual1 = dashboardPage.depositFirstCard().deposit(500, "5559 0000 0000 0002")
                .getFirstCardBalance();
        Assertions.assertEquals(10500, actual1);
        int actual2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(9500, actual2);
    }

    @Test
    void refillSecondCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.setInitialBalances();
        int actual = dashboardPage.depositSecondCard().deposit(3200, "5559 0000 0000 0001")
                .getSecondCardBalance();
        Assertions.assertEquals(13200, actual);
        int actual2 = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(6800, actual2);
    }

    @Test
    void transferOfAllAmountFromSecondCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.setInitialBalances();
        int actual1 = dashboardPage.depositFirstCard().deposit(10000, "5559 0000 0000 0002")
                .getFirstCardBalance();
        Assertions.assertEquals(20000, actual1);
        int actual2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(0, actual2);
    }

    @Test
    void transferOfAllAmountFromFirstCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.setInitialBalances();
        int actual1 = dashboardPage.depositSecondCard().deposit(10000, "5559 0000 0000 0001")
                .getSecondCardBalance();
        Assertions.assertEquals(20000, actual1);
        int actual2 = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(0, actual2);
    }

    @Test
    void boundaryValueInTheRestByFirstCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.setInitialBalances();
        int actual1 = dashboardPage.depositSecondCard().deposit(9999, "5559 0000 0000 0001")
                .getSecondCardBalance();
        Assertions.assertEquals(19999, actual1);
        int actual2 = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(1, actual2);
    }

    @Test
    void boundaryValueInTheRestBySecondCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.setInitialBalances();
        int actual1 = dashboardPage.depositFirstCard().deposit(9999, "5559 0000 0000 0002")
                .getFirstCardBalance();
        Assertions.assertEquals(19999, actual1);
        int actual2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(1, actual2);
    }

    @Test
    void transfer1RubFromFirstCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.setInitialBalances();
        int actual1 = dashboardPage.depositSecondCard().deposit(1, "5559 0000 0000 0001")
                .getSecondCardBalance();
        Assertions.assertEquals(10001, actual1);
        int actual2 = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(9999, actual2);
    }

    @Test
    void transfer1RubFromSecondCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.setInitialBalances();
        int actual1 = dashboardPage.depositFirstCard().deposit(1, "5559 0000 0000 0002")
                .getFirstCardBalance();
        Assertions.assertEquals(10001, actual1);
        int actual2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(9999, actual2);
    }

    @Test
    void emptyFieldFromForFirstCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.setInitialBalances();
        dashboardPage.depositFirstCard().deposit(500, "");
        $("[data-test-id=error-notification]").shouldBe(Condition.visible);
    }

    @Test
    void emptyFieldFromForSecondCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.setInitialBalances();
        dashboardPage.depositSecondCard().deposit(500, "");
        $("[data-test-id=error-notification]").shouldBe(Condition.visible);
    }

    @Test
    void emptyFieldAmount() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.setInitialBalances();
        int actual1 = dashboardPage.depositFirstCard().deposit(0, "5559 0000 0000 0002")
                .getFirstCardBalance();
        Assertions.assertEquals(10000, actual1);
        int actual2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(10000, actual2);
    }

    @Test
    void clickCancel() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.setInitialBalances();
        $("[data-test-id=action-deposit]").click();
        $("[data-test-id=amount] input").setValue("300");
        $("[data-test-id=from] input").setValue("5559 0000 0000 0002");
        $("[data-test-id=action-cancel]").click();
        int actual1 = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(10000, actual1);
        int actual2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(10000, actual2);
    }

    @Test
    void invalidCardNumber() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.setInitialBalances();
        dashboardPage.depositSecondCard().deposit(500, "1111 2222 3333 1234");
        $("[data-test-id=error-notification]").shouldBe(Condition.visible);
    }

    @Test
    void specialSymbolAndLettersInAmountField() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.setInitialBalances();
        $("[data-test-id=action-deposit]").click();
        $("[data-test-id=amount] input").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=amount] input").sendKeys(Keys.DELETE);
        $("[data-test-id=amount] input").setValue("-+/").shouldBe(Condition.empty);
        $("[data-test-id=amount] input").setValue("asdf").shouldBe(Condition.empty);
    }
    @Test
    void transferFromFirstCardToTheSame() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.setInitialBalances();
        int actual1 = dashboardPage.depositFirstCard().deposit(2000, "5559 0000 0000 0001")
                .getFirstCardBalance();
        Assertions.assertEquals(10000, actual1);
        int actual2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(10000, actual2);
    }
    @Test
    void transferFromSecondCardToTheSame() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.setInitialBalances();
        int actual1 = dashboardPage.depositSecondCard().deposit(2000, "5559 0000 0000 0002")
                .getSecondCardBalance();
        Assertions.assertEquals(10000, actual1);
        int actual2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(10000, actual2);
    }

    @Test
    void transferAmountMoreThanRestOnTheSecondCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.setInitialBalances();
        int actual1 = dashboardPage.depositFirstCard().deposit(12000, "5559 0000 0000 0002")
                .getFirstCardBalance();
        int actual2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(10000, actual2);
        Assertions.assertEquals(10000, actual1);

    }
    @Test
    void transferAmountMoreThanRestOnTheFirstCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.setInitialBalances();
        int actual1 = dashboardPage.depositSecondCard().deposit(12000, "5559 0000 0000 0001")
                .getSecondCardBalance();
        int actual2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(10000, actual2);
        Assertions.assertEquals(10000, actual1);

    }



}
