package logic1;

import java.util.Date;

public class Payments {

    private String paymentNumber;
    // userName(PAYMENT_AUTHOR) is in the UserInfo class
    private String paymentAuthor;
    private String paymentPurpose;
    private String paymentAmount;
    private Date paymentDateAndTime;
    private String paymentStatus;

    public Payments() {
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
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

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Date getPaymentDateAndTime() {
        return paymentDateAndTime;
    }

    public void setPaymentDateAndTime(Date paymentDateAndTime) {
        this.paymentDateAndTime = paymentDateAndTime;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
