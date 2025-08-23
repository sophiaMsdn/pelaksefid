/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.repository;

import ir.aria.pelaksefid.domain.entity.AgencyOperation;
import ir.aria.pelaksefid.domain.enumaration.AgencyOperationEnm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mana2
 */
public interface AgencyOperationRepository extends JpaRepository<AgencyOperation, Long> {

    public AgencyOperation findByType(AgencyOperationEnm operationType);
}
