/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.entity;

import ir.aria.pelaksefid.domain.entity.base.Base;
import ir.aria.pelaksefid.domain.enumaration.AdvertiseOperationEnm;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
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
@SequenceGenerator(initialValue = 1, name = "idgenerator", sequenceName = "PS_ADVERTISE_OPERATIONS_SEQ", allocationSize = 1)
@Table(name = "PS_ADVERTISE_OPERATIONS",
        indexes = {
            @Index(name = "IDX_PS_ADVERTISE_OPRTNS_001", columnList = "TYPE")
        })
public class AdvertiseOperation extends Base {

    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private AdvertiseOperationEnm type;
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "operation", fetch = FetchType.LAZY)
    private Set<AdvertiseHistory> advertiseHistorys;
}
