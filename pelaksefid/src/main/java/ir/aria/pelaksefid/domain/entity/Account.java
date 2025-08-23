/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.entity;

import ir.aria.pelaksefid.domain.entity.base.Base;
import ir.aria.pelaksefid.domain.enumaration.AccountTypeEnm;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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
@SequenceGenerator(initialValue = 1, name = "idgenerator", sequenceName = "PS_ACCOUNTS_SEQ", allocationSize = 1)
@Table(name = "PS_ACCOUNTS")
public class Account extends Base {

    @Column(name = "CELL_NUMBER", unique = true)
    private String cellNumber;
    @Column(name = "OTP_PASSWORD")
    private String otpPassword;
    @Column(name = "LOGIN_COUNT")
    private Long loginCount;
    @Column(name = "IS_MANA")
    private Boolean isMana;
    @JoinColumn(name = "ACCOUNT_TYPE")
    @Enumerated(EnumType.STRING)
    private AccountTypeEnm accountType;
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "account", fetch = FetchType.LAZY)
    private Set<Advertise> advertises;
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "account", fetch = FetchType.LAZY)
    private Set<Agency> agencies;
}
