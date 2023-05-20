package ru.netology.web.test;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV1;
import ru.netology.web.page.TransferPage;

import static com.codeborne.selenide.Selenide.*;

class MoneyTransferTest {
    DashboardPage dashboardPage;

    @BeforeEach
    private void successAuth() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        dashboardPage = verificationPage.validVerify(verificationCode);
        setInitialBalances(dashboardPage);
    }

    private void setInitialBalances(DashboardPage dashboardPage) {

        int currentBalance = dashboardPage.getFirstCardBalance();
        int depositBalance = 10000 - currentBalance;
        if (depositBalance > 0) {
            dashboardPage.depositFirstCard().deposit(depositBalance, DataHelper.secondCardNumber());
        } else if (depositBalance < 0) {
            dashboardPage.depositSecondCard().deposit(-depositBalance, DataHelper.firstCardNumber());
        }
    }

    @Test
    void refillFirstCard() {
        int actual1 = dashboardPage.depositFirstCard().deposit(500, DataHelper.secondCardNumber())
                .getFirstCardBalance();
        Assertions.assertEquals(10500, actual1);
        int actual2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(9500, actual2);
    }

    @Test
    void refillSecondCard() {
        int actual = dashboardPage.depositSecondCard().deposit(3200, DataHelper.firstCardNumber())
                .getSecondCardBalance();
        Assertions.assertEquals(13200, actual);
        int actual2 = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(6800, actual2);

    }

    @Test
    void transferOfAllAmountFromSecondCard() {
        int actual1 = dashboardPage.depositFirstCard().deposit(10000, DataHelper.secondCardNumber())
                .getFirstCardBalance();
        Assertions.assertEquals(20000, actual1);
        int actual2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(0, actual2);
    }

    @Test
    void transferOfAllAmountFromFirstCard() {
        int actual1 = dashboardPage.depositSecondCard().deposit(10000, DataHelper.firstCardNumber())
                .getSecondCardBalance();
        Assertions.assertEquals(20000, actual1);
        int actual2 = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(0, actual2);
    }

    @Test
    void boundaryValueInTheRestByFirstCard() {
        int actual1 = dashboardPage.depositSecondCard().deposit(9999, DataHelper.firstCardNumber())
                .getSecondCardBalance();
        Assertions.assertEquals(19999, actual1);
        int actual2 = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(1, actual2);
    }

    @Test
    void boundaryValueInTheRestBySecondCard() {
        int actual1 = dashboardPage.depositFirstCard().deposit(9999, DataHelper.secondCardNumber())
                .getFirstCardBalance();
        Assertions.assertEquals(19999, actual1);
        int actual2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(1, actual2);
    }

    @Test
    void transfer1RubFromFirstCard() {
        int actual1 = dashboardPage.depositSecondCard().deposit(1, DataHelper.firstCardNumber())
                .getSecondCardBalance();
        Assertions.assertEquals(10001, actual1);
        int actual2 = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(9999, actual2);
    }

    @Test
    void transfer1RubFromSecondCard() {
        int actual1 = dashboardPage.depositFirstCard().deposit(1, DataHelper.secondCardNumber())
                .getFirstCardBalance();
        Assertions.assertEquals(10001, actual1);
        int actual2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(9999, actual2);
    }

    @Test
    void emptyFieldFromForFirstCard() {
        dashboardPage.depositFirstCard().deposit(500, "");
        new TransferPage().checkErrorVisible();
    }

    @Test
    void emptyFieldFromForSecondCard() {
        dashboardPage.depositSecondCard().deposit(500, "");
        new TransferPage().checkErrorVisible();
    }

    @Test
    void emptyFieldAmount() {
        int actual1 = dashboardPage.depositFirstCard().deposit(0, DataHelper.secondCardNumber())
                .getFirstCardBalance();
        Assertions.assertEquals(10000, actual1);
        int actual2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(10000, actual2);
    }

    @Test
    void clickCancel() {
        TransferPage transferPage = dashboardPage.depositFirstCard();
        transferPage.setAmount(300);
        transferPage.setSourceCard(DataHelper.secondCardNumber());
        new TransferPage().clickCancel();
        int actual1 = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(10000, actual1);
        int actual2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(10000, actual2);
    }

    @Test
    void invalidCardNumber() {
        dashboardPage.depositSecondCard().deposit(500, DataHelper.invalidCardNumber());
        new TransferPage().checkErrorVisible();
    }

    @Test
    void specialSymbolAndLettersInAmountField() {
        TransferPage transferPage = dashboardPage.depositFirstCard();
        transferPage.amount.sendKeys(Keys.CONTROL + "A");
        transferPage.amount.sendKeys(Keys.DELETE);
        transferPage.amount.setValue("-+/").shouldBe(Condition.empty);
        transferPage.amount.setValue("asdf").shouldBe(Condition.empty);
    }

    @Test
    void transferFromFirstCardToTheSame() {
        int actual1 = dashboardPage.depositFirstCard().deposit(2000, DataHelper.firstCardNumber())
                .getFirstCardBalance();
        Assertions.assertEquals(10000, actual1);
        int actual2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(10000, actual2);
    }

    @Test
    void transferFromSecondCardToTheSame() {
        int actual1 = dashboardPage.depositSecondCard().deposit(2000, DataHelper.secondCardNumber())
                .getSecondCardBalance();
        Assertions.assertEquals(10000, actual1);
        int actual2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(10000, actual2);
    }

    @Test
    void transferAmountMoreThanRestOnTheSecondCard() {
        int actual1 = dashboardPage.depositFirstCard().deposit(12000, DataHelper.secondCardNumber())
                .getFirstCardBalance();
        int actual2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(10000, actual2);
        Assertions.assertEquals(10000, actual1);

    }

    @Test
    void transferAmountMoreThanRestOnTheFirstCard() {
        int actual1 = dashboardPage.depositSecondCard().deposit(12000, DataHelper.firstCardNumber())
                .getSecondCardBalance();
        int actual2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(10000, actual2);
        Assertions.assertEquals(10000, actual1);

    }


}
