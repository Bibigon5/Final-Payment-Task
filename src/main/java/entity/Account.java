package entity;

public class Account {

    // userName is in the UserInfo class
    private String cardName;
    private String cardNumber;
    private Double cardBalance;
    private Double cardBalanceTopUp;
    private String cardStatus;
    private String unblockRequest;

    public Account() {
    }

    public Account(String cardNumber, Double cardBalanceTopUp) {
        this.cardNumber = cardNumber;
        this.cardBalanceTopUp = cardBalanceTopUp;

    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(Double cardBalance) {
        this.cardBalance = cardBalance;
    }

    public Double getCardBalanceTopUp() {
        return cardBalanceTopUp;
    }

    public void setCardBalanceTopUp(Double cardBalanceTopUp) {
        this.cardBalanceTopUp = cardBalanceTopUp;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public void setCardStatusBlocked() {
        this.cardStatus = "BLOCKED";
    }

    public void setCardStatusUnblocked() {
        this.cardStatus = "UNBLOCKED";
    }

    public String getUnblockRequest() {
        return unblockRequest;
    }

    public void setUnblockRequest(String unblockRequest) {
        this.unblockRequest = unblockRequest;
    }

    public void setUnblockRequestYes() {
        this.unblockRequest = "YES";
    }

    public void setUnblockRequestNo() {
        this.unblockRequest = "NO";
    }
}
