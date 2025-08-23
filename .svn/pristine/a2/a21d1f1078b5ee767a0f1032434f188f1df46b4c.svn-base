/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.repository;

import ir.aria.pelaksefid.domain.entity.CarType;
import ir.aria.pelaksefid.domain.entity.CarTypeColor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mana2
 */
public interface CarTypeColorRepository extends JpaRepository<CarTypeColor, Long> {

    public List<CarTypeColor> findByIsAliveAndIsActiveAndCarType(Boolean TRUE, Boolean TRUE0, CarType ct);

}
