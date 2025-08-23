/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.repository;

import ir.aria.pelaksefid.domain.entity.Account;
import ir.aria.pelaksefid.domain.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mana2
 */
public interface SessionRepository extends JpaRepository<Session, Long> {

    public Session findByToken(String token);

    public Session findFirstByAccountOrderByIdDesc(Account account);

}
