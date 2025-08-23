/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.entity;

import ir.aria.pelaksefid.domain.entity.base.Base;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@SequenceGenerator(initialValue = 1, name = "idgenerator", sequenceName = "PS_CAR_TYPE_TRIM_COLORS_SEQ", allocationSize = 1)
@Table(name = "PS_CAR_TYPE_TRIM_COLORS",
        indexes = {
            @Index(name = "IDX_PS_CAR_TYPE_TRM_CLR_001", columnList = "PS_CAR_TYPE_ID"),
            @Index(name = "IDX_PS_CAR_TYPE_TRM_CLR_002", columnList = "PS_CAR_TRIM_COLOR_ID"),})
public class CarTypeTrimColor extends Base {

    @ManyToOne
    @JoinColumn(name = "PS_CAR_TYPE_ID")
    private CarType carType;
    @ManyToOne
    @JoinColumn(name = "PS_CAR_TRIM_COLOR_ID")
    private CarTrimColor carTrimColor;
}
