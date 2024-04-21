import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.ApiHelper;
import ru.netology.data.DataHelper;
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
    void keysTest1() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), getRandomName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.successfullPayment();
    }

    // Сценарий №2
    @Test
    void keysTest2() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), getRandomName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val creditPage  = dashboardPage.payByCredit();
        creditPage.fillForm1(authInfo);
        creditPage.successfullPayment1();
    }

    // Сценарий №3 (Bag №1)
    @Test
    void keysTest3() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), getRandomName(), getValidMonth(), getDeclinedCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.successErrorPayment();
    }

    // Сценарий №4 (Bag №2)
    @Test
    void keysTest4() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), getRandomName(), getValidMonth(), getDeclinedCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val creditPage  = dashboardPage.payByCredit();
        creditPage.fillForm1(authInfo);
        creditPage.successErrorPayment1();
    }

    // Сценарий №5
    @Test
    void keysTest5() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), getRandomName(), getValidMonth(), generateRandomCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.successErrorPayment();
    }

    // Сценарий №6
    @Test
    void keysTest6() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), getRandomName(), getValidMonth(), generateIncompleteApprovedCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.cardNumberErrorVisible();
    }

    // Сценарий №7
    @Test
    void keysTest7() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), getRandomName(), getValidMonth(), null, getValidYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.cardNumberErrorVisible();
    }

    // Сценарий №8
    @Test
    void keysTest8() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), getRandomName(), null, getApprovedCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.monthErrorVisible();
    }

    // Сценарий №9 (Bag №3)
    @Test
    void keysTest9() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), getRandomName(), getDoubleZeroMonth(), getApprovedCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.monthErrorVisible();
    }

    // Сценарий №10
    @Test
    void keysTest10() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), getRandomName(), get13Month(), getApprovedCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.monthErrorVisible();
    }

    // Сценарий №11
    @Test
    void keysTest11() {
        val authInfo = new DataHelper.AuthInfo(null, null, getSimvolMonth(), null,  null);
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.monthErrorVisible();
    }

    // Сценарий №12
    @Test
    void keysTest12() {
        val authInfo = new DataHelper.AuthInfo(null, null, getOneMonth(), null,  null);
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.monthErrorVisible();
    }

    // Сценарий №13
    @Test
    void keysTest13() {
        val authInfo = new DataHelper.AuthInfo(null, null, getThreeHundredMonth(), null,  null);
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.yearErrorVisible();
    }

    // Сценарий №14
    @Test
    void keysTest14() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), getRandomName(), getValidMonth(), getApprovedCardInfo(),  null);
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.yearErrorVisible();
    }

    // Сценарий №15
    @Test
    void keysTest15() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), getRandomName(), getValidMonth(), getApprovedCardInfo(),  getFutureYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.yearErrorVisible();
    }

    // Сценарий №16
    @Test
    void keysTest16() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), getRandomName(), getValidMonth(), getApprovedCardInfo(),  getPastYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.yearErrorVisible();
    }

    // Сценарий №17
    @Test
    void keysTest17() {
        val authInfo = new DataHelper.AuthInfo(null, null, null, null,  getThreeHundredYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.monthErrorVisible();
    }

    // Сценарий №18
    @Test
    void keysTest18() {
        val authInfo = new DataHelper.AuthInfo(null, null, null, null,  getSimvolYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.yearErrorVisible();
    }

    // Сценарий №19 (Bag №4)
    @Test
    void keysTest19() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), getRandomRussianName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.holderErrorVisible();
    }

    // Сценарий №20
    @Test
    void keysTest20() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), null, getValidMonth(), getApprovedCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.holderErrorVisible();
    }

    // Сценарий №21 (Bag №5)
    @Test
    void keysTest21() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), getMaximumLimitName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.holderErrorVisible();
    }

    // Сценарий №22 (Bag №6)
    @Test
    void keysTest22() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), getMinLimitName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.holderErrorVisible();
    }

    // Сценарий №23
    @Test
    void keysTest23() {
        val authInfo = new DataHelper.AuthInfo(null, getRandomName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.cvvErrorVisible();
    }

    // Сценарий №24
    @Test
    void keysTest24() {
        val authInfo = new DataHelper.AuthInfo(generateRandomTwoCVV(), getRandomName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.cvvErrorVisible();
    }

    // Сценарий №25
    @Test
    void keysTest25() {
        val authInfo = new DataHelper.AuthInfo(generateRandomForCVV(), getRandomName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.successfullPayment();
    }

    // Сценарий №26
    @Test
    void keysTest26() {
        val authInfo = new DataHelper.AuthInfo(generateRandomSymbolCVV(), getRandomName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.cvvErrorVisible();
    }


    // Сценарий №27
    @Test
    @DisplayName("Статус зарегистрированного пользователя")
    void databaseQueryApprovedStatusTest() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), getRandomName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.successfullPayment();
        var PaymentStatus = ApiHelper.getStatusSQL();
        Assertions.assertEquals("APPROVED", PaymentStatus);
    }

    // Сценарий №28(Bag №7)
    @Test
    @DisplayName("Сумма при оплате зарегистрированной карте")
    void databaseQueryApprovedAmountTest() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), getRandomName(), getValidMonth(), getApprovedCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.successfullPayment();
        var PaymentAmount = ApiHelper.getAmountSQL();
        Assertions.assertEquals(450000, PaymentAmount);
    }

    // Сценарий №29
    @Test
    @DisplayName("Статус удаленного пользователя")
    void databaseQueryDeclinedStatusTest() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), getRandomName(), getValidMonth(), getDeclinedCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.successfullPayment();
        var PaymentStatus = ApiHelper.getStatusSQL();
        Assertions.assertEquals("DECLINED", PaymentStatus);
    }

    // Сценарий №30(Bag №8)
    @Test
    @DisplayName("Сумма при оплате удаленной карте")
    void databaseQueryDeclinededAmountTest() {
        val authInfo = new DataHelper.AuthInfo(generateRandomCVV(), getRandomName(), getValidMonth(), getDeclinedCardInfo(),  getValidYear());
        val dashboardPage = new DashboardPage();
        val paymentPage = dashboardPage.payByCard();
        paymentPage.fillForm(authInfo);
        paymentPage.successfullPayment();
        var PaymentAmount = ApiHelper.getAmountSQL();
        Assertions.assertEquals(0, PaymentAmount);
    }

}
