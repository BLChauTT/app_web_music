package com.demo.entitiesjpa;
// Generated Aug 18, 2023, 2:33:02 PM by Hibernate Tools 4.3.6.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

/**
 * Invoice generated by hbm2java
 */
@Entity
@Table(name = "invoice")
public class Invoice implements java.io.Serializable {

	private Integer id;
	private Account account;
	private String name;
	private Date created;
	private String payment;
	private String status;
	private Set<InvoiceDetails> invoiceDetailses = new HashSet<InvoiceDetails>(0);

	public Invoice() {
	} 

	public Invoice(Account account, String name, Date created, String payment, String status) {
		this.account = account;
		this.name = name;
		this.created = created;
		this.payment = payment;
		this.status = status;
	}

	public Invoice(Account account, String name, Date created, String payment, String status,
			Set<InvoiceDetails> invoiceDetailses) {
		this.account = account;
		this.name = name;
		this.created = created;
		this.payment = payment;
		this.status = status;
		this.invoiceDetailses = invoiceDetailses;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Column(name = "name", nullable = false, length = 250)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "created", nullable = false, length = 10)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "payment", nullable = false, length = 250)
	public String getPayment() {
		return this.payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	@Column(name = "status", nullable = false, length = 250)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "invoice")
	public Set<InvoiceDetails> getInvoiceDetailses() {
		return this.invoiceDetailses;
	}

	public void setInvoiceDetailses(Set<InvoiceDetails> invoiceDetailses) {
		this.invoiceDetailses = invoiceDetailses;
	}

}
