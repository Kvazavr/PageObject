package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CardsPage {
    private SelenideElement refillFirstBalance = $("[data-test-id=action-deposit]");

    private SelenideElement refillSecondBalance = $("[data-test-id=action-deposit]");
    private SelenideElement messageError = $("[data-test-id=error-notification]");
    private SelenideElement reloadButton = $("[data-test-id=action-reload]");


//    h2.data-test-id="dashboard"."Личный кабинет".shouldBe(visible);
//    h1(Ваши карты);

//    public CardsPage() {
//        codeField.shouldBe(visible);
//    }
//
//    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
//        codeField.setValue(verificationCode.getCode());
//        verifyButton.click();
//        return new DashboardPage();
//    }
}
