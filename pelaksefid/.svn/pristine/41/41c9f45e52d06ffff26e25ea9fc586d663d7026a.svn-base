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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@SequenceGenerator(initialValue = 1, name = "idgenerator", sequenceName = "PS_CAR_TYPES_SEQ", allocationSize = 1)
@Table(name = "PS_CAR_TYPES",
        indexes = {
            @Index(name = "IDX_PS_CAR_TYPE_001", columnList = "PS_CAR_MODEL_ID"),
            @Index(name = "IDX_PS_CAR_TYPE_002", columnList = "PS_CAR_DIFFERENTIAL_ID"),
            @Index(name = "IDX_PS_CAR_TYPE_003", columnList = "PS_CAR_CYLINDER_ID"),
            @Index(name = "IDX_PS_CAR_TYPE_004", columnList = "PS_CAR_GEAR_ID"),
            @Index(name = "IDX_PS_CAR_TYPE_005", columnList = "PS_CAR_BODY_ID"),
            @Index(name = "IDX_PS_CAR_TYPE_006", columnList = "PS_CAR_ENGINE_ID"),
            @Index(name = "IDX_PS_CAR_TYPE_007", columnList = "PS_CAR_ORIGIN_ID"),})
public class CarType extends Base {

    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PERSIAN_NAME")
    private String persianName;

    @ManyToOne
    @JoinColumn(name = "PS_CAR_MODEL_ID")
    private CarModel carModel;
    @ManyToOne
    @JoinColumn(name = "PS_CAR_DIFFERENTIAL_ID")
    private CarDifferential carDifferential;
    @ManyToOne
    @JoinColumn(name = "PS_CAR_CYLINDER_ID")
    private CarCylinder carCylinder;
    @ManyToOne
    @JoinColumn(name = "PS_CAR_GEAR_ID")
    private CarGear carGear;
    @ManyToOne
    @JoinColumn(name = "PS_CAR_BODY_ID")
    private CarBody carBody;
    @ManyToOne
    @JoinColumn(name = "PS_CAR_ENGINE_ID")
    private CarEngine carEngine;
    @ManyToOne
    @JoinColumn(name = "PS_CAR_ORIGIN_ID")
    private CarOrigin carOrigin;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "carType", fetch = FetchType.LAZY)
    private Set<CarTypeFuel> carTypeFuels;
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "carType", fetch = FetchType.LAZY)
    private Set<CarTypeColor> carTypeColors;
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "carType", fetch = FetchType.LAZY)
    private Set<CarTypeTrimColor> carTypeTrimColors;
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "carType", fetch = FetchType.LAZY)
    private Set<CarTypeManufactureYear> carTypeManufactureYears;
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "carType", fetch = FetchType.LAZY)
    private Set<Car> cars;
}
