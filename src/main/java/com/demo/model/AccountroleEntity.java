package com.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "accountrole", schema = "app_web_musics_database", catalog = "")
public class AccountroleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "accountId")
    private int accountId;
    @Basic
    @Column(name = "roleId")
    private int roleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountroleEntity that = (AccountroleEntity) o;

        if (id != that.id) return false;
        if (accountId != that.accountId) return false;
        if (roleId != that.roleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + accountId;
        result = 31 * result + roleId;
        return result;
    }
}
