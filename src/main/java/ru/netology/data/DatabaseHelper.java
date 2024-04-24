package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHelper {

    private static QueryRunner runner = new QueryRunner();
    private DatabaseHelper() {
    }

    @SneakyThrows
    private static Connection getConn() {
        return DriverManager.getConnection(System.getProperty("db.url"),  "app", "qwer123");
    }

    @SneakyThrows
    public static String getStatusSQL() {
        var payStatus = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        var result = runner.query(conn, payStatus, new ScalarHandler<String>());
        return result;
    }

    @SneakyThrows
    public static int getAmountSQL() {
        var amount = "SELECT amount FROM payment_entity order by created desc LIMIT 1";
        var conn = getConn();
        var result = runner.query(conn, amount, new ScalarHandler<Integer>());
        return result;

    }

}
