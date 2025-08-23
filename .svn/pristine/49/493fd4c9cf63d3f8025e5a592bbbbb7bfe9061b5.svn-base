/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.entity;

import ir.aria.pelaksefid.domain.entity.base.Base;
import java.math.BigDecimal;
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
@SequenceGenerator(initialValue = 1, name = "idgenerator", sequenceName = "PS_CARS_SEQ", allocationSize = 1)
@Table(name = "PS_CARS",
        indexes = {
            @Index(name = "IDX_PS_CARS_001", columnList = "PS_CAR_TYPE_ID"),
            @Index(name = "IDX_PS_CARS_002", columnList = "PS_MANUFACTURE_YEAR_ID"),
            @Index(name = "IDX_PS_CARS_003", columnList = "PS_CAR_BODY_STATUS_ID"),
            @Index(name = "IDX_PS_CARS_004", columnList = "PS_CAR_COLOR_ID"),
            @Index(name = "IDX_PS_CARS_005", columnList = "PS_CAR_TRIM_COLOR_ID")
        })
public class Car extends Base {

    @Column(name = "MILEAGE")
    private BigDecimal mileage;
    @ManyToOne
    @JoinColumn(name = "PS_CAR_TYPE_ID")
    private CarType carType;
    @ManyToOne
    @JoinColumn(name = "PS_MANUFACTURE_YEAR_ID")
    private ManufactureYear manufactureYear;

    @ManyToOne
    @JoinColumn(name = "PS_CAR_BODY_STATUS_ID")
    private CarBodyStatus carBodyStatus;

    @ManyToOne
    @JoinColumn(name = "PS_CAR_COLOR_ID")
    private CarColor carColor;

    @ManyToOne
    @JoinColumn(name = "PS_CAR_TRIM_COLOR_ID")
    private CarTrimColor carTrimColor;
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "car", fetch = FetchType.LAZY)
    private Set<Advertise> advertises;

}
