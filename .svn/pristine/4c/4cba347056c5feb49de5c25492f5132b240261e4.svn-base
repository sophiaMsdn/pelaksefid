/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.model;

import ir.aria.pelaksefid.domain.entity.Agency;
import ir.aria.pelaksefid.domain.entity.base.Base;
import ir.aria.pelaksefid.domain.enumaration.AgencyStateEnm;
import ir.aria.pelaksefid.domain.model.base.BaseDto;
import ir.aria.pelaksefid.utility.ValidationUtil;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mana2
 */
@Getter
@Setter
public class AgencyDto extends BaseDto {

    private String id;
    private String name;
    private String nationalId;
    private String agencyName;
    private String address;
    private String accountId;
    private String accountType;
    private String agencyTypeId;
    private String agencyTypeText;
    private String regionId;
    private String regionDescription;
    private String agencyState;
    private String agencyStateText;
    private String adminComment;
    private AgencyDocumentDto[] documents;

    public String getAgencyStateText(String state) {
        if (state.equals(AgencyStateEnm.REGISTERED.name())) {
            return "در انتظار بررسي";
        }
        if (state.equals(AgencyStateEnm.CONFIRMED.name())) {
            return "تاييد شده";
        }
        if (state.equals(AgencyStateEnm.REJECTED.name())) {
            return "رد شده";
        }
        return "";
    }

    @Override
    public Boolean isValid() {
        if (ValidationUtil.isEmpty(name)) {
            return false;
        }
        if (ValidationUtil.isEmpty(nationalId)) {
            return false;
        }
        if (ValidationUtil.isEmpty(agencyName)) {
            return false;
        }
        if (ValidationUtil.isEmpty(address)) {
            return false;
        }
        if (ValidationUtil.isEmpty(agencyTypeId)) {
            return false;
        }
        if (ValidationUtil.isEmpty(regionId)) {
            return false;
        }
        return true;
    }

    @Override
    public Base toEntity() {
        Agency ent = new Agency();
        return toEntity(ent);
    }

    @Override
    public Base toEntity(Base e) {
        Agency ent = (Agency) e;
        ent.setName(name);
        ent.setNationalId(nationalId);
        ent.setAgencyName(agencyName);
        ent.setAddress(address);
        ent.setIsActive(Boolean.TRUE);
        ent.setIsAlive(Boolean.TRUE);
        return ent;
    }

    @Override
    public void fromEntity(Base e) {
        Agency ent = (Agency) e;
        id = String.valueOf(ent.getId());
        name = ent.getName();
        nationalId = ent.getNationalId();
        agencyName = ent.getAgencyName();
        address = ent.getAddress();
        adminComment = ent.getAdminComment();
        agencyTypeId = ent.getAgencyType() != null ? ent.getAgencyType().getId().toString() : "";
        regionId = ent.getRegion() != null ? ent.getRegion().getId().toString() : "";
        agencyTypeText = ent.getAgencyType() != null ? ent.getAgencyType().getDescription() : "";
        AccountDto accountDto = new AccountDto();
        accountType = ent.getAccount() != null ? accountDto.getAccountTypeText(ent.getAccount().getAccountType().name()) : "";
        agencyState = ent.getAgencyState() != null ? ent.getAgencyState().name() : "";
        agencyStateText = ent.getAgencyState() != null ? getAgencyStateText(ent.getAgencyState().name()) : "";
    }
}
