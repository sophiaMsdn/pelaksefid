/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.aria.pelaksefid.domain.model;

import ir.aria.pelaksefid.domain.entity.Account;
import ir.aria.pelaksefid.domain.entity.base.Base;
import ir.aria.pelaksefid.domain.enumaration.AccountTypeEnm;
import ir.aria.pelaksefid.domain.model.base.BaseDto;
import ir.aria.pelaksefid.utility.ValidationUtil;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author user
 */
@Getter
@Setter
public class AccountDto extends BaseDto {

    private String id;
    private String cellNumber;
    private String otpPassword;
    private String accountType;
    private String accountTypeText;

    public String getAccountTypeText(String type) {
        if (type.equals(AccountTypeEnm.AGNC.name())) {
            return "نمايندگي";
        } else if (type.equals(AccountTypeEnm.ORD.name())) {
            return "اصلي";
        }
        return "";
    }

    @Override
    public Boolean isValid() {
        if (ValidationUtil.isEmpty(cellNumber) || !cellNumber.matches("09([0-9]{9})")) {
            return false;
        }
        return true;
    }

    @Override
    public Base toEntity() {
        Account ent = new Account();
        return toEntity(ent);
    }

    @Override
    public Base toEntity(Base e) {
        Account ent = (Account) e;
        ent.setCellNumber(cellNumber);
        ent.setIsAlive(Boolean.TRUE);
        return ent;
    }

    @Override
    public void fromEntity(Base e) {
        Account ent = (Account) e;
        id = String.valueOf(ent.getId());
        cellNumber = ent.getCellNumber();
        accountType = ent.getAccountType().name();
        accountTypeText = getAccountTypeText(ent.getAccountType().name());
    }
}
