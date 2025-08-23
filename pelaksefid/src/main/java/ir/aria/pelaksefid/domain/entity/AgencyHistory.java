/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.entity;

import ir.aria.pelaksefid.domain.entity.base.Base;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mana2
 */
@Getter
@Setter
@Entity
@SequenceGenerator(initialValue = 1, name = "idgenerator", sequenceName = "PS_AGENCY_HISTORIES_SEQ", allocationSize = 1)
@Table(name = "PS_AGENCY_HISTORIES",
        indexes = {
            @Index(name = "IDX_PS_AGENCY_HSTRS_001", columnList = "PS_AGENCY_ID"),
            @Index(name = "IDX_PS_AGENCY_HSTRS_002", columnList = "PS_OPERATION_ID")
        })
public class AgencyHistory extends Base {

    @Column(name = "REGISTER_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date registerDate;
    @Column(name = "REGISTER_TIME")
    private String registerTime;
    @Column(name = "OPERATION_DATE")
    private String operationDate;
    @Column(name = "OPERATION_TIME")
    private String operationTime;
    @Column(name = "USER_NAME")
    private String username;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "MISC_ID")
    private Long miscId;

    @ManyToOne
    @JoinColumn(name = "PS_AGENCY_ID", nullable = true)
    private Agency agency;
    @ManyToOne
    @JoinColumn(name = "PS_OPERATION_ID", nullable = true)
    private AgencyOperation operation;

}
