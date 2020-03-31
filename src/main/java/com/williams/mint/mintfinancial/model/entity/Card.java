package com.williams.mint.mintfinancial.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "card")
public class Card {

    private Long d;
    private String scheme;
    private String type;
    private String bank;
    private String status;
    private String count;
    private String bin;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    public Long getD() { return d; }

    public void setD(Long d) { this.d = d; }

    @Basic
    @Column(name = "scheme", nullable = true)
    public String getScheme() { return scheme; }

    public void setScheme(String scheme) { this.scheme = scheme; }

    @Basic
    @Column(name = "type", nullable = true)
    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    @Basic
    @Column(name = "bank", nullable = true)
    public String getBank() { return bank; }

    public void setBank(String bank) { this.bank = bank; }

    @Basic
    @Column(name = "status", nullable = true)
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    @Basic
    @Column(name = "count", nullable = true)
    public String getCount() { return count; }

    public void setCount(String count) { this.count = count; }

    @Basic
    @Column(name = "bin", nullable = true)
    public String getBin() { return bin; }

    public void setBin(String bin) { this.bin = bin; }

    @Basic
    @Column(name = "created_at", nullable = true)
    public Timestamp getCreatedAt() { return createdAt; }

    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @Basic
    @Column(name = "updated_at", nullable = true)
    public Timestamp getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    @PrePersist
    public void beforeSave() {
        this.createdAt = new Timestamp(new Date().getTime());
    }

    @PreUpdate
    private void beforeUpdate() {
        this.updatedAt = new Timestamp(new Date().getTime());
    }
}
