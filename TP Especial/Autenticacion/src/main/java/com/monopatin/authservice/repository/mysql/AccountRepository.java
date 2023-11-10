package com.monopatin.authservice.repository.mysql;




import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.monopatin.authservice.entity.mysql.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
