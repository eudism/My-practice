package bank.dao;

import bank.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;



public interface IAccountDAO extends JpaRepository<Account,Integer> {

}

