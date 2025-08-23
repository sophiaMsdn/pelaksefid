/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.entity;

import ir.aria.pelaksefid.domain.entity.base.Base;
import ir.aria.pelaksefid.domain.enumaration.AgencyTypeEnm;
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
@SequenceGenerator(initialValue = 1, name = "idgenerator", sequenceName = "PS_AGENCY_TYPES_SEQ", allocationSize = 1)
@Table(name = "PS_AGENCY_TYPES",
        indexes = {
            @Index(name = "IDX_PS_AGENCY_TYPE_001", columnList = "TYPE")
        })
public class AgencyType extends Base {

    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private AgencyTypeEnm type;
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "agencyType", fetch = FetchType.LAZY)
    private Set<Agency> agencies;
}
