/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.repository;

import ir.aria.pelaksefid.domain.entity.Agency;
import ir.aria.pelaksefid.domain.entity.AgencyDocument;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mana2
 */
public interface AgencyDocumentRepository extends JpaRepository<AgencyDocument, Long> {

    public List<AgencyDocument> findByIsActiveAndIsAliveAndAgency(Boolean TRUE, Boolean TRUE0, Agency agency);

    public List<AgencyDocument> findByAgencyAndIsActiveAndIsAlive(Agency d, Boolean TRUE, Boolean TRUE0);

}
