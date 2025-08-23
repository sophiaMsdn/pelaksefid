/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.entity;

import ir.aria.pelaksefid.domain.entity.base.Base;
import ir.aria.pelaksefid.domain.enumaration.AgencyStateEnm;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@SequenceGenerator(initialValue = 1, name = "idgenerator", sequenceName = "PS_AGENCIES_SEQ", allocationSize = 1)
@Table(name = "PS_AGENCIES",
        indexes = {
            @Index(name = "IDX_PS_AGENCIES_001", columnList = "PS_ACCOUNT_ID"),
            @Index(name = "IDX_PS_AGENCIES_002", columnList = "PS_AGENCY_TYPE_ID"),
            @Index(name = "IDX_PS_AGENCIES_003", columnList = "PS_REGION_ID")
        })
public class Agency extends Base {

    @Column(name = "NAME")
    private String name;
    @Column(name = "NATIONAL_ID")
    private String nationalId;
    @Column(name = "AGENCY_NAME")
    private String agencyName;
    @Column(name = "ADDRESS")
    private String address;
    @ManyToOne
    @JoinColumn(name = "PS_ACCOUNT_ID")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "PS_AGENCY_TYPE_ID")
    private AgencyType agencyType;

    @JoinColumn(name = "AGENCY_STATE")
    @Enumerated(EnumType.STRING)
    private AgencyStateEnm agencyState;

    @Column(name = "ADMIN_COMMENT")
    private String adminComment;

    @ManyToOne
    @JoinColumn(name = "PS_REGION_ID")
    private Region region;
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "agency", fetch = FetchType.LAZY)
    private Set<AgencyDocument> agencyDocument;
}
