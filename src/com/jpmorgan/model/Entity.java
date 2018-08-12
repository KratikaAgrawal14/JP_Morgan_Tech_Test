/**
 * @author kratika Agrawal
 *
 */
package com.jpmorgan.model;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * Model class entity
 *
 */
public class Entity {

	private String name;
	private String transactionType;
	private String currency;
	private LocalDate instructionDate;
	private LocalDate settlementDate;
	private int units;
	private BigDecimal pricePerUnit;
	private BigDecimal agreedFx;
	private BigDecimal USD;

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name of the entity
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * @param Type of transaction Buy/sell
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * @return currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param Currency value
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return instructionDate
	 */
	public LocalDate getInstructionDate() {
		return instructionDate;
	}

	/**
	 * @param Date on which the instruction was sent 
	 */
	public void setInstructionDate(LocalDate instructionDate) {
		this.instructionDate = instructionDate;
	}

	/**
	 * @return acceptable settlementDate 
	 */
	public LocalDate getSettlementDate() {

		return settlementDate
				.plusDays(TradeParams.getWorkingDay(this.currency, this.settlementDate.getDayOfWeek().toString()));
	}

	/**
	 * @param The date on which the client wished for the instruction to be settled with respect
	 * to Instruction Date
	 */
	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}

	/**
	 * @return agreedFx
	 */
	public BigDecimal getAgreedFx() {
		return agreedFx;
	}

	/**
	 * @param agreedFx is the foreign exchange rate with respect to USD that was agreed
	 */
	public void setAgreedFx(BigDecimal agreedFx) {
		this.agreedFx = agreedFx;
	}

	/**
	 * @return units
	 */
	public int getUnits() {
		return units;
	}

	/**
	 * @param units: Number of shares to be bought or sold
	 */
	public void setUnits(int units) {
		this.units = units;
	}

	/**
	 * @return pricePerUnit
	 */
	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	/**
	 * @param pricePerUnit
	 */
	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	/**
	 * @return USD amount of a trade = Price per unit * Units * AgreedFx
	 */
	public BigDecimal getUSD() {

		return this.pricePerUnit.multiply(agreedFx).multiply(new BigDecimal(units));
	}

	@Override
	public String toString() {
		return "Entity [name=" + name + ", transactionType=" + transactionType + ", currency=" + currency
				+ ", instructionDate=" + instructionDate + ", settlementDate=" + settlementDate + ", units=" + units
				+ ", pricePerUnit=" + pricePerUnit + ", agreedFx=" + agreedFx + ", USD=" + USD + "]";
	}

}
