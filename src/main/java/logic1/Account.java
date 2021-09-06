package logic1;

public class Account {

    // userName is in the UserInfo class
    private String cardName;
    private String cardNumber;
    private Double cardBalance;

    public Account() {
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
}
