package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CreditPage {
    private final SelenideElement cardNumber = $(byText("Номер карты")).parent().$(".input__control");
    private final SelenideElement month = $(byText("Месяц")).parent().$(".input__control");
    private final SelenideElement year = $(byText("Год")).parent().$(".input__control");
    private final SelenideElement holder = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement cvc = $(byText("CVC/CVV")).parent().$(".input__control");
    private final SelenideElement conditionButton = $(byText("Продолжить"));

    public void fillForm1(DataHelper.AuthInfo authInfo) {
        cardNumber.setValue(authInfo.getCardNumber());
        month.setValue(authInfo.getMonth());
        year.setValue(authInfo.getYear());
        holder.setValue(authInfo.getHolder());
        cvc.setValue(authInfo.getCvc());
        conditionButton.click();
    }

    public void successfullPayment1() {
        $(".notification_status_ok").shouldBe(Condition.visible, Duration.ofSeconds(30));
    }

    public void successErrorPayment1() {
        $(".notification_status_error").shouldBe(Condition.visible, Duration.ofSeconds(30));
    }
}
