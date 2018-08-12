package com.jpmorgan;

import java.util.concurrent.atomic.AtomicInteger;

import com.jpmorgan.dao.impl.IncomingSettlement;
import com.jpmorgan.dao.impl.OutgoingSettlement;

/**
 * @author kratika Agrawal This is the main class of the Application to generate
 *         report from a given file
 */
public class Application {

	public static void main(String[] args) {

		// Generating outgoing day-wise settlement report
		OutgoingSettlement o = new OutgoingSettlement();
		o.settleTransaction();
		System.out.println("OutGoing Settlement Report:");
		// Date-wise print trade amount settled per day
		System.out.println(o.getTradeMap());

		// Getting Rank report
		AtomicInteger atomicInteger = new AtomicInteger(0);
		OutgoingSettlement.getRankMap().forEach((k, v) -> {
			System.out.println("Entity : " + k + " Rank : " + atomicInteger.incrementAndGet());
		});

		// Generating incoming day-wise settlement report
		IncomingSettlement i = new IncomingSettlement();
		i.settleTransaction();
		AtomicInteger atomicInteger1 = new AtomicInteger(0);
		System.out.println("\nIncoming Settlement Report:");
		// Date-wise print trade amount settled per day
		System.out.println(i.getTradeMap());

		// Getting Rank report
		IncomingSettlement.getRankMap().forEach((k, v) -> {
			System.out.println("Entity : " + k + " Rank : " + atomicInteger1.incrementAndGet());
		});

	}

}
