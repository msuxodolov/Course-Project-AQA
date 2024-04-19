package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;
import ru.netology.data.DataHelper.AuthInfo;


import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class PaymentPage {

    private final SelenideElement cardNumber = $(byText("Номер карты")).parent().$(".input__control");
    private final SelenideElement month = $(byText("Месяц")).parent().$(".input__control");
    private final SelenideElement year = $(byText("Год")).parent().$(".input__control");
    private final SelenideElement holder = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement cvc = $(byText("CVC/CVV")).parent().$(".input__control");
    private final SelenideElement conditionButton = $(byText("Продолжить"));

    private final SelenideElement cardNumberError = $(byText("Номер карты")).parent().$(".input__sub");
    private final SelenideElement monthError = $(byText("Месяц")).parent().$(".input__sub");
    private final SelenideElement yearError = $(byText("Год")).parent().$(".input__sub");
    private final SelenideElement holderError = $(byText("Владелец")).parent().$(".input__sub");
    private final SelenideElement cvcError = $(byText("CVC/CVV")).parent().$(".input__sub");

    public void fillForm(AuthInfo authInfo) {
        cardNumber.setValue(authInfo.getCardNumber());
        month.setValue(authInfo.getMonth());
        year.setValue(authInfo.getYear());
        holder.setValue(authInfo.getHolder());
        cvc.setValue(authInfo.getCvc());
        conditionButton.click();
    }

    public void successfullPayment() {
        $(".notification_status_ok").shouldBe(visible, Duration.ofSeconds(30));
    }

    public void successErrorPayment() {
        $(".notification_status_error").shouldBe(visible, Duration.ofSeconds(30));
    }

//    public void fillErrorForm() {
//        cardNumberError.shouldBe(visible);
//    }

    public void cardNumberErrorVisible(){
        cardNumberError.shouldBe(visible);
    }

    public void monthErrorVisible(){
        monthError.shouldBe(visible);
    }

    public void yearErrorVisible(){
        yearError.shouldBe(visible);
    }

    public void cvvErrorVisible(){
        cvcError.shouldBe(visible);
    }

    public void holderErrorVisible(){
        holderError.shouldBe(visible);
    }




}