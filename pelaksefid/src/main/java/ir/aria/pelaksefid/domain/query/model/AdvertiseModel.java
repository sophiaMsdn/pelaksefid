/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.query.model;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mana2
 */
@Getter
@Setter
public class AdvertiseModel {

    private String id;
    private String accountCellNumber;
    private String sigma;
    private String agencyId;
    private String agencyName;
    private String carType;
    private String carModel;
    private String brand;
    private String persianYear;
    private String miladiYear;
    private String mileage;
    private String gear;
    private String price;
    private String region;
    private String city;
    private String province;
    private String adComment;
    private String advertiseState;
    private String agencyDocuments;
    private String documents;
    private String sigmaDocuments;
    private String bodyStatus;
    private String registerDate;
    private String updateDate;
    private String color;
    private String trimColor;
    private String cylinder;
    private String differential;

    public AdvertiseModel(Object[] columns) {

        int index = 0;
        this.id = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.accountCellNumber = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.sigma = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.agencyId = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.agencyName = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.carType = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.carModel = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.brand = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.persianYear = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.miladiYear = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.mileage = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.gear = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.price = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.region = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.city = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.province = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.adComment = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.advertiseState = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        if (columns[index] != null) {
            Clob clob = (Clob) columns[index];
            try {
                this.documents = clob.getSubString(1, (int) clob.length());
            } catch (SQLException ex) {
                Logger.getLogger(AdvertiseModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (++index >= columns.length) {
            return;
        }
        if (columns[index] != null) {
            Clob clob = (Clob) columns[index];
            try {
                this.agencyDocuments = clob.getSubString(1, (int) clob.length());
            } catch (SQLException ex) {
                Logger.getLogger(AdvertiseModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (++index >= columns.length) {
            return;
        }
        if (columns[index] != null) {
            Clob clob = (Clob) columns[index];
            try {
                this.sigmaDocuments = clob.getSubString(1, (int) clob.length());
            } catch (SQLException ex) {
                Logger.getLogger(AdvertiseModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (++index >= columns.length) {
            return;
        }
        this.bodyStatus = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.registerDate = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.updateDate = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.color = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.trimColor = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.cylinder = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
        this.differential = (columns[index] != null) ? (columns[index]).toString() : null;
        if (++index >= columns.length) {
            return;
        }
    }
}
