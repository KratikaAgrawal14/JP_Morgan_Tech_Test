package com.jpmorgan.dao.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jpmorgan.consts.SettlementConstants;
import com.jpmorgan.dao.CSVFileReader;
import com.jpmorgan.dao.TradeSettlement;
import com.jpmorgan.model.Entity;

/**
 * @author kratika Agrawal
 * Class to process Incoming settlement per day and set entity rank
 */
public class IncomingSettlement implements TradeSettlement,SettlementConstants {

	private  Map<LocalDate, BigDecimal> tradeMap = new HashMap<>();
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
			if (entity.getTransactionType().equalsIgnoreCase(FILE_BUY_INDICATOR)) {
				if (!tradeMap.containsKey(entity.getSettlementDate())) {
					tradeMap.put(entity.getSettlementDate(), entity.getUSD());

				} else {

					BigDecimal USD = tradeMap.get(entity.getSettlementDate()).add(entity.getUSD());
					tradeMap.put(entity.getSettlementDate(), USD);

				}
				if (!rankMap.containsKey(entity.getName())) {
					rankMap.put(entity.getName(), entity.getUSD());

				} else {

					BigDecimal USD = rankMap.get(entity.getName()).add(entity.getUSD());
					rankMap.put(entity.getName(), USD);

				}

			}

		}

	}


	/**
	 * @return tradeMap
	 */
	public Map<LocalDate, BigDecimal> getTradeMap() {
		return tradeMap;
	}


	/**
	 * @return rankMap
	 */
	public static Map<String, BigDecimal> getRankMap() {
		
		Map<String, BigDecimal> result2 = new LinkedHashMap<>();
		rankMap.entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue())
                .forEachOrdered(x -> result2.put(x.getKey(), x.getValue()));
		
		return rankMap;
	}

	
	

}
