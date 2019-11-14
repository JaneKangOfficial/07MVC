package com.model2.mvc.service.domain;

import java.sql.Date;


//==>»óÇ°Á¤º¸¸¦ ¸ðµ¨¸µ(Ãß»óÈ­/Ä¸½¶È­)ÇÑ Bean
public class Product {
	
	///Field
	private int prodNo;
	private String prodName;
	private String prodDetail;
	private String manuDate;
	private int price;
	private String fileName;
	private Date regDate;
	private String proTranCode;
	
	
	///Constructor
	public Product(){
	}

	///Method 
	/**
	 * @return the prodNo
	 */
	public int getProdNo() {
		return prodNo;
	}


	/**
	 * @param prodNo the prodNo to set
	 */
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}


	/**
	 * @return the prodName
	 */
	public String getProdName() {
		return prodName;
	}


	/**
	 * @param prodName the prodName to set
	 */
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}


	/**
	 * @return the prodDetail
	 */
	public String getProdDetail() {
		return prodDetail;
	}


	/**
	 * @param prodDetail the prodDetail to set
	 */
	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}


	/**
	 * @return the manuDate
	 */
	public String getManuDate() {
		return manuDate;
	}


	/**
	 * @param manuDate the manuDate to set
	 */
	public void setManuDate(String manuDate) {
		this.manuDate = manuDate;
	}


	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}


	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}


	/**
	 * @return the imageFile
	 */
	public String getFileName() {
		return fileName;
	}


	/**
	 * @param imageFile the imageFile to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	/**
	 * @return the regDate
	 */
	public Date getRegDate() {
		return regDate;
	}


	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	/**
	 * @return the proTranCode
	 */
	public String getProTranCode() {
		return proTranCode;
	}


	/**
	 * @param proTranCode the proTranCode to set
	 */
	public void setProTranCode(String proTranCode) {
		this.proTranCode = proTranCode;
	}

	@Override
	public String toString() {
		return "Product [prodNo=" + prodNo + ", prodName=" + prodName + ", prodDetail=" + prodDetail + ", manuDate="
				+ manuDate + ", price=" + price + ", fileName=" + fileName + ", regDate=" + regDate + ", proTranCode="
				+ proTranCode + "]";
	}



	
	

}
	