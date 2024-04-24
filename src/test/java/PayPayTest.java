import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.DatabaseHelper;
import ru.netology.page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;

public class PayPayTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
    }

    // Сценарий №1
    @Test
    void paymentWithRegisteredCardUsingValidData() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), getRandomName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.successfullPayment();
    }

    // Сценарий №2
    @Test
    void paymentInCreditWithRegisteredCardUsingValidData() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), getRandomName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var creditPage  = dashboardPage.payByCredit();
        creditPage.fillForm1(cardInfo);
        creditPage.successfullPayment1();
    }

    // Сценарий №3 (Bag №1)
    @Test
    void paymentWithDeclinedCardUsingValidData() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), getRandomName(), getValidMonth(), getDeclinedCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.successErrorPayment();
    }

    // Сценарий №4 (Bag №2)
    @Test
    void paymentInCreditWithDeclinedCardUsingValidData() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), getRandomName(), getValidMonth(), getDeclinedCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var creditPage  = dashboardPage.payByCredit();
        creditPage.fillForm1(cardInfo);
        creditPage.successErrorPayment1();
    }

    // Сценарий №5
    @Test
    void paymentUsingANonExistentCard() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), getRandomName(), getValidMonth(), generateRandomCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.successErrorPayment();
    }

    // Сценарий №6
    @Test
    void paymentByPartialCardNumber() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), getRandomName(), getValidMonth(), generateIncompleteApprovedCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.cardNumberErrorVisible();
    }

    // Сценарий №7
    @Test
    void sendingAnEmptyFieldCardNumberWhenPayingForAService() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), getRandomName(), getValidMonth(), null, getValidYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.cardNumberErrorVisible();
    }

    // Сценарий №8
    @Test
    void sendingAnEmptyFieldMonthWhenPayingForAService() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), getRandomName(), null, getApprovedCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.monthErrorVisible();
    }

    // Сценарий №9 (Bag №3)
    @Test
    void sendingAnonExistentZeroMonthWhenPayingForAService() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), getRandomName(), getDoubleZeroMonth(), getApprovedCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.monthErrorVisible();
    }

    // Сценарий №10
    @Test
    void sendingAnonExistentThirteenthMonthWhenPayingForAService() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), getRandomName(), get13Month(), getApprovedCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.monthErrorVisible();
    }

    // Сценарий №11
    @Test
    void enteringCharactersInTheMonthField() {
        var cardInfo = new DataHelper.CardInfo(null, null, getSimvolMonth(), null,  null);
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.monthErrorVisible();
    }

    // Сценарий №12
    @Test
    void enteringOneDigitInTheMonthField() {
        var cardInfo = new DataHelper.CardInfo(null, null, getOneMonth(), null,  null);
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.monthErrorVisible();
    }

    // Сценарий №13
    @Test
    void enteringThreeDigitsInTheMonthField() {
        var cardInfo = new DataHelper.CardInfo(null, null, getThreeHundredMonth(), null,  null);
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.yearErrorVisible();
    }

    // Сценарий №14
    @Test
    void submittingAnEmptyYearFieldWhenPayingForAService() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), getRandomName(), getValidMonth(), getApprovedCardInfo(),  null);
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.yearErrorVisible();
    }

    // Сценарий №15
    @Test
    void enteringThePastYearWhenPayingForAService() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), getRandomName(), getValidMonth(), getApprovedCardInfo(),  getFutureYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.yearErrorVisible();
    }

    // Сценарий №16
    @Test
    void enteringAFutureDateBeyondTheNormalExpirationDateOfCards() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), getRandomName(), getValidMonth(), getApprovedCardInfo(),  getPastYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.yearErrorVisible();
    }

    // Сценарий №17
    @Test
    void enteringThreeDigitsInTheYearField() {
        var cardInfo = new DataHelper.CardInfo(null, null, null, null,  getThreeHundredYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.monthErrorVisible();
    }

    // Сценарий №18
    @Test
    void enteringCharactersIntoTheYearField() {
        var cardInfo = new DataHelper.CardInfo(null, null, null, null,  getSimvolYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.yearErrorVisible();
    }

    // Сценарий №19 (Bag №4)
    @Test
    void enteringCharactersInTheOwnerFieldInCyrillic() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), getRandomRussianName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.holderErrorVisible();
    }

    // Сценарий №20
    @Test
    void submittingBlankDataInTheOwnerField() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), null, getValidMonth(), getApprovedCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.holderErrorVisible();
    }

    // Сценарий №21 (Bag №5)
    @Test
    void enteringMaximumValueInTheOwnerFieldBeyondTheBoundaryValue() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), getMaximumLimitName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.holderErrorVisible();
    }

    // Сценарий №22 (Bag №6)
    @Test
    void enteringOneValidCharacterInTheOwnerField() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), getMinLimitName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.holderErrorVisible();
    }

    // Сценарий №23
    @Test
    void submittingBlankDataInTheCVCVVCField() {
        var cardInfo = new DataHelper.CardInfo(null, getRandomName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.cvvErrorVisible();
    }

    // Сценарий №24
    @Test
    void enteringIncompleteDataInTheCVCVVCField() {
        var cardInfo = new DataHelper.CardInfo(generateRandomTwoCVV(), getRandomName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.cvvErrorVisible();
    }

    // Сценарий №25
    @Test
    void enteringDataBeyondTheBoundaryValuesInTheCVCVVCField() {
        var cardInfo = new DataHelper.CardInfo(generateRandomForCVV(), getRandomName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.successfullPayment();
    }

    // Сценарий №26
    @Test
    void enteringExtraneousCharactersInTheCVCVVCField() {
        var cardInfo = new DataHelper.CardInfo(generateRandomSymbolCVV(), getRandomName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.cvvErrorVisible();
    }


    // Сценарий №27
    @Test
    @DisplayName("Просмотр статуса в СУБД MySQL зарегистрированного пользователя")
    void databaseQueryApprovedStatusTest() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), getRandomName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.successfullPayment();
        var PaymentStatus = DatabaseHelper.getStatusSQL();
        Assertions.assertEquals("APPROVED", PaymentStatus);
    }

    // Сценарий №28(Bag №7)
    @Test
    @DisplayName("Просмотр оплаты тура в СУБД MySQL зарегистрированного пользователя")
    void databaseQueryApprovedAmountTest() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), getRandomName(),
                getValidMonth(), getApprovedCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.successfullPayment();
        var PaymentAmount = DatabaseHelper.getAmountSQL();
        Assertions.assertEquals(450000, PaymentAmount);
    }

    // Сценарий №29
    @Test
    @DisplayName("Просмотр статуса в СУБД MySQL отклоненного пользователя")
    void databaseQueryDeclinedStatusTest() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), getRandomName(), getValidMonth(), getDeclinedCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.successfullPayment();
        var PaymentStatus = DatabaseHelper.getStatusSQL();
        Assertions.assertEquals("DECLINED", PaymentStatus);
    }

    // Сценарий №30(Bag №8)
    @Test
    @DisplayName("Просмотр оплаты тура в СУБД MySQL отклоненного пользователя")
    void databaseQueryDeclinededAmountTest() {
        var cardInfo = new DataHelper.CardInfo(generateRandomCVV(), getRandomName(), getValidMonth(),
                getDeclinedCardInfo(),  getValidYear());
        var dashboardPage = new DashboardPage();
        var paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(cardInfo);
        paymentPage.successfullPayment();
        var PaymentAmount = DatabaseHelper.getAmountSQL();
        Assertions.assertEquals(0, PaymentAmount);
    }

}
