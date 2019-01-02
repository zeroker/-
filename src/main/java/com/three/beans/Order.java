package com.three.beans;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
@Entity
@Table(name = "t_orders", schema = "ssh")
public class Order implements Serializable {
    private  int id;
    private String oId;
    private Timestamp ordertime;
    private Double total;
    private String address;                 //
    private String telephone;               //
    private String name;

    private Integer status;
    private User user;
    private int flag;

    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "o_id",referencedColumnName = "u_id",insertable = false,updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Column(name = "o_id", nullable = false, length = 32)
    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }


    @Basic
    @Column(name = "flag")
    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Basic
    @Column(name = "ordertime", nullable = true)
    public Timestamp getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Timestamp ordertime) {
        this.ordertime = ordertime;
    }

    @Basic
    @Column(name = "total", nullable = true, precision = 0)
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 30)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "telephone", nullable = true, length = 20)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (oId != null ? !oId.equals(order.oId) : order.oId != null) return false;
        if (ordertime != null ? !ordertime.equals(order.ordertime) : order.ordertime != null) return false;
        if (total != null ? !total.equals(order.total) : order.total != null) return false;
        if (address != null ? !address.equals(order.address) : order.address != null) return false;
        if (name != null ? !name.equals(order.name) : order.name != null) return false;
        if (telephone != null ? !telephone.equals(order.telephone) : order.telephone != null) return false;
        if (status != null ? !status.equals(order.status) : order.status != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = oId != null ? oId.hashCode() : 0;
        result = 31 * result + (ordertime != null ? ordertime.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
