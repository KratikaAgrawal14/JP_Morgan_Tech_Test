/**
 * 
 */
package com.jpmorgan.dao.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jpmorgan.consts.SettlementConstants;
import com.jpmorgan.dao.CSVFileReader;
import com.jpmorgan.dao.TradeSettlement;
import com.jpmorgan.model.Entity;

/**
 * @author kmac
 *
 */
public class OutgoingSettlement implements TradeSettlement, SettlementConstants {

	//
	private Map<LocalDate, BigDecimal> tradeMap = new HashMap<>();
	private static Map<String, BigDecimal> rankMap = new HashMap<>();

	/*
	 * (non-Javadoc)
	 * @see com.jpmorgan.service.TradeSettlement#settleTransaction() 
	 * settle the INCOMING transactions per day and 
	 * prepare entity ranks
	 */
	@Override
	public void settleTransaction() {
		for (Entity entity : CSVFileReader.readFile()) {
			if (isDataValid(entity)) {
				continue;
			}
			if (entity.getTransactionType().equalsIgnoreCase(FILE_SELL_INDICATOR)) {
				if (!tradeMap.containsKey(entity.getSettlementDate())) {
					tradeMap.put(entity.getSettlementDate(), entity.getUSD());

				} else {

					BigDecimal USD = tradeMap.get(entity.getSettlementDate()).add(entity.getUSD());
					tradeMap.put(entity.getSettlementDate(), USD);

				}
				// Generating Rank map
				if (!rankMap.containsKey(entity.getName())) {
					rankMap.put(entity.getName(), entity.getUSD());

				} else {

					BigDecimal USD = rankMap.get(entity.getName()).add(entity.getUSD());
					rankMap.put(entity.getName(), USD);

				}

			}

		}

	}

	private boolean isDataValid(Entity entity) {

		if (entity.getName().equals("")||entity.getTransactionType().equals("")||entity.getName().isEmpty()||entity.getAgreedFx().equals(null)||entity.getCurrency().equals("")||entity.getCurrency().isEmpty()) {
			System.out.println("Invaild Entity skipped :"+ entity);
			return true;
		} else {

		}
		return false;
	}

	/**
	 * @return
	 */
	public Map<LocalDate, BigDecimal> getTradeMap() {
		return tradeMap;
	}

	/**
	 * @return
	 */
	public static Map<String, BigDecimal> getRankMap() {

		Map<String, BigDecimal> result2 = new LinkedHashMap<>();
		//reverse sorting map enteries
		rankMap.entrySet().stream().sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
				.forEachOrdered(x -> result2.put(x.getKey(), x.getValue()));

		return result2;
	}

}
