/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.client.consume.sigma;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mana2
 */
@Getter
@Setter
public class Advertise {

    private String id;
    private String advertiseAmount;
    private String mileage;
    private String comment;
    private String advertiseState;
    private String brandId;
    private String brandDescription;
    private String carTypeId;
    private String carTypeDescription;
    private String carModelId;
    private String carModelDescription;
    private String manufactureYearId;
    private String miladiYear;
    private String persianYear;
    private String colorId;
    private String colorCode;
    private String colorDescription;
    private String trimColorId;
    private String registerDate;
    private String accountName;
    private String accountLastName;
    private String accountCellNumber;
    private String accountOrgName;
    private String provinceDescription;
    private String cityDescription;
    private Document[] salesOrderDocuments;
}
