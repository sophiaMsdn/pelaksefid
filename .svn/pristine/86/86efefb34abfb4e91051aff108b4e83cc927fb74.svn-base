/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.repository;

import ir.aria.pelaksefid.domain.entity.Advertise;
import ir.aria.pelaksefid.domain.entity.AdvertiseHistory;
import ir.aria.pelaksefid.domain.entity.AdvertiseOperation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mana2
 */
public interface AdvertiseHistoryRepository extends JpaRepository<AdvertiseHistory, Long> {

    public List<AdvertiseHistory> findByAdvertiseAndOperation(Advertise advertise, AdvertiseOperation operation);

    public List<AdvertiseHistory> findByIsActiveAndIsAliveAndAdvertise(Boolean TRUE, Boolean TRUE0, Advertise advertise);

}
