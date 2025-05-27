package se.lexicon.model;

import java.math.BigDecimal;

public class Wallet {
    private String walletId;
    private BigDecimal balance;

    public Wallet(BigDecimal initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }

    public String getWalletId() {
        return walletId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    @Override
    public String toString() {
        return "WalletId=" + walletId + ", Balance=" + balance;
    }
}
