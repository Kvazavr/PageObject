package ru.netology.web.test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV1;
import ru.netology.web.page.LoginPageV2;
import ru.netology.web.page.LoginPageV3;

import static com.codeborne.selenide.Selenide.open;

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
    void shouldReturnBalanceFirstCard(){
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getValidVerificationCodeFor();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        int balance = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(10000, balance);

    }

//    @Test
//    void shouldTransferMoneyBetweenOwnCardsV2() {
//        open("http://localhost:9999");
//        var loginPage = new LoginPageV2();
////    var loginPage = open("http://localhost:9999", LoginPageV2.class);
//        var authInfo = DataHelper.getValidAuthInfo();
//        var verificationPage = loginPage.validLogin(authInfo);
//        var verificationCode = DataHelper.getValidVerificationCodeFor();
//        verificationPage.validVerify(verificationCode);
//    }
//
//    @Test
//    void shouldTransferMoneyBetweenOwnCardsV3() {
//        var loginPage = open("http://localhost:9999", LoginPageV3.class);
//        var authInfo = DataHelper.getValidAuthInfo();
//        var verificationPage = loginPage.validLogin(authInfo);
//        var verificationCode = DataHelper.getValidVerificationCodeFor();
//        verificationPage.validVerify(verificationCode);
//    }
}

