package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static final Faker FAKER = new Faker(new Locale("en"));

    private DataHelper() {
    }


    @Value
    //@Data
    @RequiredArgsConstructor
    public static class CardInfo {
        String cvc;
        String holder;
        String month;
        String cardNumber;
        String year;
    }

    // Заполнение поля "Номер карты"
    public static String getApprovedCardInfo() {
        return ("1111 2222 3333 4444");
    }

    public static String getDeclinedCardInfo() {
        return "5555 6666 7777 8888";
    }


    public static String generateRandomCardInfo() {
        return FAKER.numerify("################");
    }

    public static String generateIncompleteApprovedCardInfo() {
        return "1111 2222 3333 444";
    }

    // заполнение поля "Месяц".
    public static String getValidMonth() {
        return LocalDate.now().plusMonths(FAKER.number().numberBetween(1, 12)).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getDoubleZeroMonth() {
        return "00";
    }

    public static String get13Month() {
        return "13";
    }

    public static String getSimvolMonth() {
        return "f/";
    }

    public static String getOneMonth() {
        return FAKER.numerify("#");
    }

    public static String getThreeHundredMonth() {
        return "300";
    }

    // Заполнение поля "Год".
    public static String getValidYear() {
        return LocalDate.now().plusYears(FAKER.number().numberBetween(1, 5)).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getFutureYear() {
        return LocalDate.now().plusYears(FAKER.number().numberBetween(6, 75)).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getPastYear() {
        return LocalDate.now().plusYears(FAKER.number().numberBetween(-1, -23)).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getThreeHundredYear() {
        return FAKER.numerify("###");
    }

    public static String getSimvolYear() {
        return "r@";
    }

    // Заполнение поля CVV
    public static String generateRandomCVV() {
        return FAKER.numerify("###");
    }

    public static String generateRandomTwoCVV() {
        return FAKER.numerify("##");
    }

    public static String generateRandomForCVV() {
        return FAKER.numerify("####");
    }

    public static String generateRandomSymbolCVV() {
        return "l-[";
    }

    // Заполнение поля "Владелец карты".
    public static String getRandomName() {
        return FAKER.name().fullName();
    }

    public static String getRandomRussianName() {
        Faker FAKER = new Faker(new Locale("ru"));
        return FAKER.name().fullName();
    }

    public static String getMaximumLimitName() {
        return "Pablo Diego José Francisco de Paula Juan Nepomuceno María de los ";
    }

    public static String getMinLimitName() {
        return FAKER.letterify("#");
    }

}
