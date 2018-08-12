/**
 * 
 */
package com.jpmorgan.dao;

/**
 * @author kratika Agrawal
 */
public interface TradeSettlement {
	
	/**
	 * 
	 * Calculates transaction amounts (in USD) per day
	 */
	public void settleTransaction();
	
}
