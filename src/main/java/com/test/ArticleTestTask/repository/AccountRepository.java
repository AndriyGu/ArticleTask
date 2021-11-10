package com.test.ArticleTestTask.repository;


import com.test.ArticleTestTask.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findAccountByEmail(String email);

    @Query("SELECT a FROM Account a WHERE a.email=?1")
    Optional<Account> findByEmailOptional(String email);
}
