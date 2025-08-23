/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.repository;

import ir.aria.pelaksefid.domain.entity.Account;
import ir.aria.pelaksefid.domain.entity.Agency;
import ir.aria.pelaksefid.domain.enumaration.AgencyStateEnm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mana2
 */
public interface AgencyRepository extends JpaRepository<Agency, Long> {

    public Agency findByAccountAndIsActiveAndIsAlive(Account account, Boolean TRUE, Boolean TRUE0);

    public Agency findByIsActiveAndIsAliveAndAccount(Boolean TRUE, Boolean TRUE0, Account account);

    public Long countByIsActiveAndIsAlive(Boolean TRUE, Boolean TRUE0);

    public Long countByIsActiveAndIsAliveAndAgencyState(Boolean TRUE, Boolean TRUE0, AgencyStateEnm agencyStateEnm);

}
