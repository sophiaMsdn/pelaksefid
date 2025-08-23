/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.repository;

import ir.aria.pelaksefid.domain.entity.Brand;
import ir.aria.pelaksefid.domain.entity.CarModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mana2
 */
public interface CarModelRepository extends JpaRepository<CarModel, Long> {

    public List<CarModel> findByIsAliveAndIsActiveAndBrand(Boolean TRUE, Boolean TRUE0, Brand b);

    public List<CarModel> findByDescription(String carModdelDescription);

}
