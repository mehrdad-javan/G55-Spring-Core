package se.lexicon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.config.WalletConfig;
import se.lexicon.dao.TransactionDao;
import se.lexicon.dao.WalletDao;
import se.lexicon.model.Transaction;
import se.lexicon.model.TransactionType;
import se.lexicon.model.Wallet;
import se.lexicon.service.WalletService;

import java.math.BigDecimal;

@Component
public class WalletServiceImpl implements WalletService {

    private WalletDao walletDao;
    private TransactionDao transactionDao;

    private WalletConfig walletConfig;


    @Autowired
    public WalletServiceImpl(WalletDao walletDao, TransactionDao transactionDao, WalletConfig walletConfig) {
        this.walletDao = walletDao;
        this.transactionDao = transactionDao;
        this.walletConfig = walletConfig;
    }

    @Override
    public Wallet create(Wallet wallet) {
        if (wallet == null) throw new IllegalArgumentException("Wallet should not be null.");
        return walletDao.save(wallet);
    }

    @Override
    public Wallet depositTransaction(String walletId, BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be grater than zero.");
        }
        if (amount.compareTo(walletConfig.getMaxDeposit()) > 0) {
            throw new IllegalArgumentException("Deposit number exceeds maximum limit of [ " + walletConfig.getMaxDeposit() + " ]");
        }
        Wallet wallet = walletDao.findById(walletId)
                .orElseThrow(() -> new IllegalArgumentException("wallet id [" + walletId + "] not found!"));

        wallet.deposit(amount);

        walletDao.update(wallet);

        Transaction transaction = new Transaction(wallet.getWalletId(), TransactionType.DEPOSIT, amount);
        transactionDao.save(transaction);

        return wallet;
    }

    @Override
    public Wallet withdrawTransaction(String walletId, BigDecimal amount) {
        return null;
    }

    @Override
    public Wallet findWalletBydId(String walletId) {
        return null;
    }
}
