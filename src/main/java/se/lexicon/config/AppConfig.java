package se.lexicon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import se.lexicon.dao.TransactionDao;
import se.lexicon.dao.WalletDao;
import se.lexicon.dao.impl.TransactionDaoImpl;
import se.lexicon.dao.impl.WalletDaoImpl;
import se.lexicon.service.WalletService;

@Configuration
@ComponentScan(basePackages = "se.lexicon")
public class AppConfig {

    @Bean
    public WalletDao walletDao(){
        return new WalletDaoImpl();
    }

    @Bean
    public TransactionDao transactionDao(){
        return new TransactionDaoImpl();
    }

}
