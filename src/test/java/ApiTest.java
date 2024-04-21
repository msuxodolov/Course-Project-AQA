import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.ApiHelper;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;

public class ApiTest {

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
        Assertions.assertEquals(4500000, PaymentAmount);
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
    @DisplayName("Сумма при оплате зарегистрированной карте")
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
