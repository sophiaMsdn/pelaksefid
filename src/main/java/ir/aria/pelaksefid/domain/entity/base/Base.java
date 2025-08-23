/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.aria.pelaksefid.domain.entity.base;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author r-taherkhani
 */
@MappedSuperclass
public class Base<T extends Number> implements Serializable {
// <editor-fold defaultstate="collapsed" desc="Entity Fields"> 

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgenerator")
    private Long id;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "IS_ALIVE")
    private Boolean isAlive;
    @Column(name = "CHANNEL_ID", length = 255)
    private String channelId;
    @Column(name = "USER_IP", length = 20)
    private String userIp;
    @Column(name = "OWNER_ID", length = 19)
    private String ownerId;
    @Column(name = "USER_ID", length = 19)
    private String userId;
    @Column(name = "REG_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date regDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(Boolean isAlive) {
        this.isAlive = isAlive;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

}
