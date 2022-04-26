package com.higradius;

import java.sql.Date;

public class Response {

	private String custName;
	private String custId;
	private Long invoiceId;
	private float invoiceAmt;
	private Date dueIn;
	private Date prdDate;
	private String notes;
	
	
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public Long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
	public float getInvoiceAmt() {
		return invoiceAmt;
	}
	public void setInvoiceAmt(float invoiceAmt) {
		this.invoiceAmt = invoiceAmt;
	}
	public Date getDueIn() {
		return dueIn;
	}
	public void setDueIn(Date dueIn) {
		this.dueIn = dueIn;
	}
	public Date getPrdDate() {
		return prdDate;
	}
	public void setPrdDate(Date prdDate) {
		this.prdDate = prdDate;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	
	
}
