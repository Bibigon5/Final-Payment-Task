package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.Account;
import entity.Payments;
import entity.UserInfo;

public class DBUtils {


    public static UserInfo findUser(Connection conn, //
                                    String userName, String password) throws SQLException {


        String sql = "Select a.user_name, a.Password, a.Gender from user_info a " //
                + " where a.user_name = ? and a.password= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String gender = rs.getString("Gender");
            UserInfo user = new UserInfo();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);

            return user;
        }
        return null;
    }

    public static UserInfo findUser(Connection conn, String userName) throws SQLException {

        String sql = "Select a.User_Name, a.Password, a.Gender from user_info a "//
                + " where a.User_Name = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String password = rs.getString("Password");
            String gender = rs.getString("Gender");
            UserInfo user = new UserInfo();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            return user;
        }
        return null;
    }

    public static Account findCard(Connection conn, String cardNumber) throws SQLException {
        String sql = "Select a.CARD_NAME, a.CARD_NUMBER from account a "//
                + " where a.CARD_NUMBER = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, cardNumber);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String cardName = rs.getString("CARD_NAME");
            Account card = new Account();
            card.setCardName(cardName);
            card.setCardNumber(cardNumber);
            return card;
        }
        return null;
    }

    public static List<UserInfo> queryUser(Connection conn) throws SQLException {
        String sql = "Select a.USER_NAME, a.GENDER, a.USER_STATUS from user_info a ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<UserInfo> list = new ArrayList<>();
        while (rs.next()) {
            String userName = rs.getString("USER_NAME");
            String gender = rs.getString("GENDER");
            String userStatus = rs.getString("USER_STATUS");
            UserInfo user = new UserInfo();
            user.setUserName(userName);
            user.setGender(gender);
            user.setUserStatus(userStatus);
            list.add(user);
        }
        return list;
    }

    public static List<Account> queryCard(Connection conn, String userName) throws SQLException {
        String sql = "Select a.CARD_NAME, a.CARD_NUMBER, a.CARD_BALANCE, a.CARD_STATUS from account a "//
                + " where a.User_Name = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);

        ResultSet rs = pstm.executeQuery();
        List<Account> list = new ArrayList<>();
        while (rs.next()) {
            String cardName = rs.getString("CARD_NAME");
            String cardNumber = rs.getString("CARD_NUMBER");
            Double cardBalance = rs.getDouble("CARD_BALANCE");
            String cardStatus = rs.getString("CARD_STATUS");
            Account card = new Account();
            card.setCardName(cardName);
            card.setCardNumber(cardNumber);
            card.setCardBalance(cardBalance);
            card.setCardStatus(cardStatus);
            list.add(card);
        }
        return list;
    }

    public static List<Account> queryCardUnblocked(Connection conn, String userName) throws SQLException {
        String sql = "Select a.CARD_NAME, a.CARD_NUMBER, a.CARD_BALANCE, a.CARD_STATUS from account a "//
                + " where a.User_Name = ? and CARD_STATUS = 'UNBLOCKED'";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);

        ResultSet rs = pstm.executeQuery();
        List<Account> list = new ArrayList<>();
        while (rs.next()) {
            String cardName = rs.getString("CARD_NAME");
            String cardNumber = rs.getString("CARD_NUMBER");
            Double cardBalance = rs.getDouble("CARD_BALANCE");
            String cardStatus = rs.getString("CARD_STATUS");
            Account card = new Account();
            card.setCardName(cardName);
            card.setCardNumber(cardNumber);
            card.setCardBalance(cardBalance);
            card.setCardStatus(cardStatus);
            list.add(card);
        }
        return list;
    }

    public static boolean findUserByNickname(Connection conn, String userName) throws SQLException {

        String sql = "Select a.User_Name, a.Password, a.Gender from user_info a "//
                + " where a.User_Name = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);

        ResultSet rs = pstm.executeQuery();

        return rs.next();
    }

    public static String getUserStatus (Connection conn, String card) throws SQLException {
        String sql = "SELECT USER_STATUS FROM user_info " +
                "WHERE USER_NAME = ?;";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, card);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            return rs.getString("USER_STATUS");
        }
        return null;
    }

    public static void userBlock (Connection conn, UserInfo user) throws SQLException {
        String sql = "UPDATE user_info " + "SET USER_STATUS =" + " ? " +
                "WHERE USER_NAME = ?;";

        PreparedStatement pstm = conn.prepareStatement(sql);
        user.setUserStatusBlocked();
        pstm.setString(1, user.getUserStatus());
        pstm.setString(2, user.getUserName());

        System.out.println(pstm);
        pstm.executeUpdate();
    }

    public static void userUnblock (Connection conn, UserInfo user) throws SQLException {
        String sql = "UPDATE user_info " + "SET USER_STATUS =" + " ? " +
                "WHERE USER_NAME = ?;";

        PreparedStatement pstm = conn.prepareStatement(sql);
        user.setUserStatusUnblocked();
        pstm.setString(1, user.getUserStatus());
        pstm.setString(2, user.getUserName());

        System.out.println(pstm);
        pstm.executeUpdate();
    }

    public static boolean findCardByNumber(Connection conn, String cardNumber) throws SQLException {
        String sql = "Select CARD_NUMBER from account "//
                + " where CARD_NUMBER = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, cardNumber);

        ResultSet rs = pstm.executeQuery();
        return rs.next();

    }

    public static void registerUser (Connection conn, UserInfo user) throws SQLException {
        String sql = "INSERT INTO user_info" + "(User_Name, Gender, Password) VALUES" +
                "(?, ?, ?);";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, user.getUserName());
        pstm.setString(2, user.getGender());
        pstm.setString(3, user.getPassword());

        System.out.println(pstm);
        pstm.executeUpdate();
    }

    public static void addCard (Connection conn, Account account, UserInfo user) throws SQLException {
        String sql = "INSERT INTO account" + "(USER_NAME, CARD_NAME, CARD_NUMBER) " +
                "VALUES" + "(?, ?, ?);";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, user.getUserName());//скорее всего тут логинед юзер
        pstm.setString(2, account.getCardName());
        pstm.setString(3, account.getCardNumber());

        System.out.println(pstm);
        pstm.executeUpdate();
    }

    public static void cardTopUp (Connection conn, Account account) throws SQLException {
        String sql = "UPDATE account " + "SET CARD_BALANCE =" + "CARD_BALANCE + ?" +
                "WHERE CARD_NUMBER = ?;";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setDouble(1, account.getCardBalanceTopUp());
        pstm.setString(2, account.getCardNumber());

        System.out.println(pstm);
        pstm.executeUpdate();
    }

    public static String getCardStatus (Connection conn, String card) throws SQLException {
        String sql = "SELECT CARD_STATUS FROM account " +
                "WHERE CARD_NUMBER = ?;";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, card);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            return rs.getString("CARD_STATUS");
        }
        return null;
    }

    public static Double getCardBalance (Connection conn, String card) throws SQLException {
        String sql = "SELECT CARD_BALANCE FROM account " +
                "WHERE CARD_NUMBER = ?;";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, card);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            return rs.getDouble("CARD_BALANCE");
        }
        return null;
    }

    public static void cardBlock (Connection conn, Account account) throws SQLException {
        String sql = "UPDATE account " + "SET CARD_STATUS =" + " ? " +
                "WHERE CARD_NUMBER = ?;";

        PreparedStatement pstm = conn.prepareStatement(sql);
        account.setCardStatusBlocked();
        pstm.setString(1, account.getCardStatus());
        pstm.setString(2, account.getCardNumber());

        System.out.println(pstm);
        pstm.executeUpdate();
    }

    public static void cardUnblock (Connection conn, Account account) throws SQLException {
        String sql = "UPDATE account " + "SET CARD_STATUS =" + " ? " +
                "WHERE CARD_NUMBER = ?;";

        PreparedStatement pstm = conn.prepareStatement(sql);
        account.setCardStatusUnblocked();
        pstm.setString(1, account.getCardStatus());
        pstm.setString(2, account.getCardNumber());

        System.out.println(pstm);
        pstm.executeUpdate();
    }

    public static String getUnblockRequest (Connection conn, String card) throws SQLException {
        String sql = "SELECT UNBLOCK_REQUEST FROM account " +
                "WHERE CARD_NUMBER = ?;";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, card);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            return rs.getString("UNBLOCK_REQUEST");
        }
        return null;
    }

    public static List<Account> queryRequest(Connection conn) throws SQLException {
        String sql = "Select a.CARD_NAME, a.CARD_NUMBER, a.CARD_BALANCE from account a "//
                + " where a.UNBLOCK_REQUEST = 'YES' ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Account> list = new ArrayList<>();
        while (rs.next()) {
            String cardName = rs.getString("CARD_NAME");
            String cardNumber = rs.getString("CARD_NUMBER");
            Double cardBalance = rs.getDouble("CARD_BALANCE");
            Account card = new Account();
            card.setCardName(cardName);
            card.setCardNumber(cardNumber);
            card.setCardBalance(cardBalance);
            list.add(card);
        }
        return list;
    }

    public static void setRequestYes (Connection conn, Account account) throws SQLException {
        String sql = "UPDATE account " + "SET UNBLOCK_REQUEST =" + " ? " +
                "WHERE CARD_NUMBER = ?;";

        PreparedStatement pstm = conn.prepareStatement(sql);
        account.setUnblockRequestYes();
        pstm.setString(1, account.getUnblockRequest());
        pstm.setString(2, account.getCardNumber());

        System.out.println(pstm);
        pstm.executeUpdate();
    }

    public static void setRequestNo (Connection conn, Account account) throws SQLException {
        String sql = "UPDATE account " + "SET UNBLOCK_REQUEST =" + " ? " +
                "WHERE CARD_NUMBER = ?;";

        PreparedStatement pstm = conn.prepareStatement(sql);
        account.setUnblockRequestNo();
        pstm.setString(1, account.getUnblockRequest());
        pstm.setString(2, account.getCardNumber());

        System.out.println(pstm);
        pstm.executeUpdate();
    }

    public static void addPayment (Connection conn, Payments pay, UserInfo user) throws SQLException {
        String sql = "INSERT INTO payments" + "(PAYMENT_AUTHOR, FROM_CARD_NUMBER, PAYMENT_PURPOSE, " +
                "PAYMENT_TELEPHONE, PAYMENT_AMOUNT, PAYMENT_DATE_AND_TIME, PAYMENT_STATUS) " +
                "VALUES" + "(?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pay.setPaymentStatusPrepared();
        pstm.setString(1, user.getUserName());//скорее всего тут логинед юзер
        pstm.setString(2, pay.getCardNumber());
        pstm.setString(3, pay.getPaymentPurpose());
        pstm.setString(4, pay.getPaymentTelephone());
        pstm.setDouble(5, pay.getPaymentAmount());
        pstm.setDate(6, pay.getPaymentDateAndTime());
        pstm.setString(7, pay.getPaymentStatus());

        System.out.println(pstm);
        pstm.executeUpdate();
    }

    public static Payments getPayment(Connection conn, Integer number) throws SQLException {

        String sql = "Select FROM_CARD_NUMBER, PAYMENT_AMOUNT, PAYMENT_NUMBER  from payments a "//
                + " where PAYMENT_NUMBER = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, number);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String cardNumber = rs.getString("FROM_CARD_NUMBER");
            Double amount = rs.getDouble("PAYMENT_AMOUNT");

            Payments payment = new Payments();
            payment.setCardNumber(cardNumber);
            payment.setPaymentAmount(amount);
            payment.setPaymentNumber(number);
            return payment;
        }
        return null;
    }

    public static String getPaymentStatus (Connection conn, Integer number) throws SQLException {
        String sql = "SELECT PAYMENT_STATUS FROM payments " +
                "WHERE PAYMENT_NUMBER = ?;";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, number);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            return rs.getString("PAYMENT_STATUS");
        }
        return null;
    }

    public static void commitPayment (Connection conn, Payments payment) throws SQLException {
        String sql = "UPDATE payments " + "SET PAYMENT_STATUS =" + " ? " +
                "WHERE PAYMENT_NUMBER = ?;";

        PreparedStatement pstm = conn.prepareStatement(sql);
        payment.setPaymentStatusDispatched();
        pstm.setString(1, payment.getPaymentStatus());
        pstm.setInt(2, payment.getPaymentNumber());

        System.out.println(pstm);
        pstm.executeUpdate();
    }

    public static void paymentBalanceDecrease (Connection conn, Payments payment) throws SQLException {
        String sql = "UPDATE account " + "SET CARD_BALANCE =" + "CARD_BALANCE - ?" +
                "WHERE CARD_NUMBER = ?;";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setDouble(1, payment.getPaymentAmount());
        pstm.setString(2, payment.getCardNumber());

        System.out.println(pstm);
        pstm.executeUpdate();
    }

    public static List<Payments> queryPayments(Connection conn, String card) throws SQLException {
        String sql = "Select PAYMENT_NUMBER, FROM_CARD_NUMBER, PAYMENT_PURPOSE, PAYMENT_TELEPHONE, PAYMENT_AMOUNT, " +
                "PAYMENT_DATE_AND_TIME, PAYMENT_STATUS from payments  "//
                + " where FROM_CARD_NUMBER = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, card);

        ResultSet rs = pstm.executeQuery();
        List<Payments> list = new ArrayList<>();
        while (rs.next()) {
            Integer paymentNumber = rs.getInt("PAYMENT_NUMBER");
            String cardNumber = rs.getString("FROM_CARD_NUMBER");
            String paymentPurpose = rs.getString("PAYMENT_PURPOSE");
            String paymentTelephone = rs.getString("PAYMENT_TELEPHONE");
            Double paymentAmount = rs.getDouble("PAYMENT_AMOUNT");
            Date paymentDateAndTime = rs.getDate("PAYMENT_DATE_AND_TIME");
            String paymentStatus = rs.getString("PAYMENT_STATUS");

            Payments payment = new Payments();
            payment.setPaymentNumber(paymentNumber);
            payment.setCardNumber(cardNumber);
            payment.setPaymentPurpose(paymentPurpose);
            payment.setPaymentTelephone(paymentTelephone);
            payment.setPaymentAmount(paymentAmount);
            payment.setPaymentDateAndTime(paymentDateAndTime);
            payment.setPaymentStatus(paymentStatus);

            list.add(payment);
        }
        return list;
    }

}
