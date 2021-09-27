package entity;

import java.sql.Date;

public class Payments {

    private Integer paymentNumber;
    // userName(PAYMENT_AUTHOR) is in the UserInfo class
    private String cardNumber;
    private String paymentAuthor;
    private String paymentPurpose;
    private String paymentTelephone;
    private Double paymentAmount;
    private String paymentDateAndTime;
    private String paymentStatus;

    public Payments() {
    }

    public Integer getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(Integer paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPaymentAuthor() {
        return paymentAuthor;
    }

    public void setPaymentAuthor(String paymentAuthor) {
        this.paymentAuthor = paymentAuthor;
    }

    public String getPaymentPurpose() {
        return paymentPurpose;
    }

    public void setPaymentPurpose(String paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentDateAndTime() {
        return paymentDateAndTime;
    }

    public void setPaymentDateAndTime(String paymentDateAndTime) {
        this.paymentDateAndTime = paymentDateAndTime;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setPaymentStatusPrepared() {
        this.paymentStatus = "Prepared";
    }

    public void setPaymentStatusDispatched() {
        this.paymentStatus = "Dispatched";
    }

    public String getPaymentTelephone() {
        return paymentTelephone;
    }

    public void setPaymentTelephone(String paymentTelephone) {
        this.paymentTelephone = paymentTelephone;
    }
}
