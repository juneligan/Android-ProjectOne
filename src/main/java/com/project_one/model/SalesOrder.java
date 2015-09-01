package com.project_one.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by JenuNagil on 7/30/2015.
 */
public class SalesOrder {
    private Long id;
    private User user;
    private BigDecimal amount;
    private Date dateCreated;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
}
