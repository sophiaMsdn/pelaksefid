/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.repository;

import ir.aria.pelaksefid.domain.entity.Agency;
import ir.aria.pelaksefid.domain.entity.AgencyHistory;
import ir.aria.pelaksefid.domain.entity.AgencyOperation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mana2
 */
public interface AgencyHistoryRepository extends JpaRepository<AgencyHistory, Long> {

    public List<AgencyHistory> findByAgencyAndOperation(Agency agency, AgencyOperation operation);

    public List<AgencyHistory> findByIsActiveAndIsAliveAndAgency(Boolean TRUE, Boolean TRUE0, Agency agency);

}
