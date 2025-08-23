/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.entity;

import ir.aria.pelaksefid.domain.entity.base.Base;
import ir.aria.pelaksefid.domain.enumaration.NotificationEnm;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@SequenceGenerator(initialValue = 1, name = "idgenerator", sequenceName = "PS_SMSS_SEQ", allocationSize = 1)
@Table(name = "PS_SMSS")
public class Sms extends Base {

    @Column(name = "CELL_NUMBER")
    private String cellNumber;
    @Column(name = "PATTERN")
    private String pattern;
    @Column(name = "PATTERN_DATA", length = 4000)
    private String data;
    @Column(name = "NOTIFICATION_TYPE")
    @Enumerated(EnumType.STRING)
    private NotificationEnm type;
    @Column(name = "POSTAGE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date postageDate;
    @Column(name = "EXPIRE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expireDate;
    @Column(name = "TRY_COUNT")
    private Integer tryCount;
    @Column(name = "POSTED")
    private Boolean posted;
}
