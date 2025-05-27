package se.lexicon.dao.impl;

import org.springframework.stereotype.Component;
import se.lexicon.dao.TransactionDao;
import se.lexicon.model.Transaction;

import java.util.*;
import java.util.stream.Collectors;

//@Component
public class TransactionDaoImpl implements TransactionDao {
    public Map<String, Transaction> storage = new HashMap<>();

    @Override
    public Transaction save(Transaction transaction) {
        transaction.setTransactionId(UUID.randomUUID().toString());
        storage.put(transaction.getTransactionId(), transaction);
        return transaction;
    }

    @Override
    public Optional<Transaction> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Transaction> findByWalletId(String walletId) {
        return storage.values().stream()
                .filter(tx -> tx.getWalletId().equals(walletId)) // Requires walletId in Transaction class
                .collect(Collectors.toList());
    }

    public List<Transaction> findAll() {
        return new ArrayList<>(storage.values());
    }
}
