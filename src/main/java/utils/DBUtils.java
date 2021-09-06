package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import logic1.Account;
import logic1.Payments;
import logic1.Product;
import logic1.UserInfo;

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

    public static List<Account> queryCard(Connection conn, String userName) throws SQLException {
        String sql = "Select a.CARD_NAME, a.CARD_NUMBER, a.CARD_BALANCE from account a "//
                + " where a.User_Name = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);

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

    public static boolean findUserByNickname(Connection conn, String userName) throws SQLException {

        String sql = "Select a.User_Name, a.Password, a.Gender from user_info a "//
                + " where a.User_Name = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);

        ResultSet rs = pstm.executeQuery();

        return rs.next();
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

    public static void addPayment (Connection conn, Payments pay, UserInfo user) throws SQLException {
        String sql = "INSERT INTO payments" + "(PAYMENT_AUTHOR, PAYMENT_PURPOSE, " +
                "PAYMENT_AMOUNT, PAYMENT_DATE_AND_TIME, PAYMENT_STATUS) " +
                "VALUES" + "(?, ?, ?, ?, ?);";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, user.getUserName());//скорее всего тут логинед юзер
        pstm.setString(2, pay.getPaymentPurpose());
        pstm.setString(3, pay.getPaymentAmount());
        pstm.setString(4, pay.getPaymentDateAndTime().toString());
        pstm.setString(5, pay.getPaymentStatus());

        System.out.println(pstm);
        pstm.executeUpdate();
    }

    public static List<Product> queryProduct(Connection conn) throws SQLException {
        String sql = "Select a.Code, a.Name, a.Price from Product a ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Product> list = new ArrayList<>();
        while (rs.next()) {
            String code = rs.getString("Code");
            String name = rs.getString("Name");
            float price = rs.getFloat("Price");
            Product product = new Product();
            product.setCode(code);
            product.setName(name);
            product.setPrice(price);
            list.add(product);
        }
        return list;
    }

    public static Product findProduct(Connection conn, String code) throws SQLException {
        String sql = "Select a.Code, a.Name, a.Price from Product a where a.Code=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, code);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            String name = rs.getString("Name");
            float price = rs.getFloat("Price");
            Product product = new Product(code, name, price);
            return product;
        }
        return null;
    }

    public static void updateProduct(Connection conn, Product product) throws SQLException {
        String sql = "Update Product set Name =?, Price=? where Code=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, product.getName());
        pstm.setFloat(2, product.getPrice());
        pstm.setString(3, product.getCode());
        pstm.executeUpdate();
    }

    public static void insertProduct(Connection conn, Product product) throws SQLException {
        String sql = "Insert into Product(Code, Name,Price) values (?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, product.getCode());
        pstm.setString(2, product.getName());
        pstm.setFloat(3, product.getPrice());

        pstm.executeUpdate();
    }

    public static void deleteProduct(Connection conn, String code) throws SQLException {
        String sql = "Delete From Product where Code= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, code);

        pstm.executeUpdate();
    }

}
