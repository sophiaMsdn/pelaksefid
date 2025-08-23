/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ir.aria.pelaksefid.domain.repository;

import ir.aria.pelaksefid.domain.entity.CarBodyStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mana2
 */
public interface CarBodyStatusRepository extends JpaRepository<CarBodyStatus, Long> {

    public List<CarBodyStatus> findByIsActiveAndIsAlive(Boolean TRUE, Boolean TRUE0);

    public List<CarBodyStatus> findByIsAliveAndIsActive(Boolean TRUE, Boolean TRUE0);

}
