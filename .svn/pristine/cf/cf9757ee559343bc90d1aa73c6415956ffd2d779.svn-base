/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.repository;

import ir.aria.pelaksefid.domain.entity.Region;
import ir.aria.pelaksefid.domain.enumaration.RegionTypeEnm;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mana2
 */
public interface RegionRepository extends JpaRepository<Region, Long> {

    public List<Region> findByTypeAndIsActiveAndIsAlive(RegionTypeEnm type, Boolean TRUE, Boolean TRUE0);

    public List<Region> findByTypeAndParentAndIsActiveAndIsAlive(RegionTypeEnm regionTypeEnm, Region region, Boolean TRUE, Boolean TRUE0);

    public List<Region> findByDescriptionAndType(String provinceDescription, RegionTypeEnm regionTypeEnm);

}
