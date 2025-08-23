/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.model;

import ir.aria.pelaksefid.utility.ValidationUtil;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mana2
 */
@Getter
@Setter
public class LogModelDto {

    private String userId;
    private String userIp;
    private String username;
    private String userDescription;
    private String userUnit;
    private Boolean isActive;
    private Boolean isAlive;
    private String channelId;
    private String ownerId;
    private Date registerDate;
    private Date updateDate;

    public LogModelDto(String username, String ipAddress, String browserName) {
        this.userIp = ipAddress;
        this.username = username;
        if (!ValidationUtil.isEmpty(browserName)) {
            if (browserName.length() > 18) {
                this.channelId = browserName.substring(0, 18);
            }
        }
        this.registerDate = new Date();
        this.updateDate = new Date();
    }
}
