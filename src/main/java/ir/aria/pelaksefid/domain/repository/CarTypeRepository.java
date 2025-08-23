/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.repository;

import ir.aria.pelaksefid.domain.entity.CarModel;
import ir.aria.pelaksefid.domain.entity.CarType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mana2
 */
public interface CarTypeRepository extends JpaRepository<CarType, Long> {

    public List<CarType> findByIsAliveAndIsActiveAndCarModel(Boolean TRUE, Boolean TRUE0, CarModel cm);

    public List<CarType> findByDescription(String carTypeDescription);

}
