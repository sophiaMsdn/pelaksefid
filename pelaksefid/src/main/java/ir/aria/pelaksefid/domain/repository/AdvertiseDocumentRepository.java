/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.repository;

import ir.aria.pelaksefid.domain.entity.Advertise;
import ir.aria.pelaksefid.domain.entity.AdvertiseDocument;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mana2
 */
public interface AdvertiseDocumentRepository extends JpaRepository<AdvertiseDocument, Long> {

    public List<AdvertiseDocument> findByIsActiveAndIsAliveAndAdvertise(Boolean isActive, Boolean isAlive, Advertise advertise);

}
