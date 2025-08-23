/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.entity;

import ir.aria.pelaksefid.domain.entity.base.Base;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mana2
 */
@Getter
@Setter
@Entity
@SequenceGenerator(initialValue = 1, name = "idgenerator", sequenceName = "PS_MANUFACTURE_YEARS_SEQ", allocationSize = 1)
@Table(name = "PS_MANUFACTURE_YEARS")
public class ManufactureYear extends Base {

    @Column(name = "PERSIAN_YEAR")
    private Integer persianYear;
    @Column(name = "MILADI_YEAR")
    private Integer miladiYear;
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "manufactureYear", fetch = FetchType.LAZY)
    private Set<CarTypeManufactureYear> carTypeManufactureYears;
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "manufactureYear", fetch = FetchType.LAZY)
    private Set<Car> cars;
}
