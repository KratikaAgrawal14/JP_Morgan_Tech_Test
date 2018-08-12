package com.jpmorgan.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.jpmorgan.consts.SettlementConstants;
import com.jpmorgan.model.Entity;

/**
 * @author kratika Agrawal This class read the CSV file data from the give file
 *         path
 */

public class CSVFileReader implements SettlementConstants {

	static List<Entity> entityList = new ArrayList<>();

	/**
	 * method to read a CSV file and populate entity list
	 */
	public static List<Entity> readFile() {

		boolean skipHeader = IS_SKIP_HEADER;
		// read file into stream
		try (Stream<String> stream = Files.lines(Paths.get(INSTRUCTION_FILE_PATH))) {

			if (skipHeader) {

				stream.skip(1).forEach(entity -> addEntity(entity));

			} else {
				stream.forEach(entityDetails -> addEntity(entityDetails));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return entityList;
	}

	/**
	 * generate entity from entity details
	 * and populate the entity list.
	 * @param entityDetails
	 */
	private static void addEntity(String entityDetails) {

		Entity entity = new Entity();

		String[] entityElements = entityDetails.split(INSTRUCTION_FILE_SEPERATER);
		try {
			entity.setName(entityElements[0]);
			entity.setCurrency(entityElements[3]);
			entity.setTransactionType(entityElements[1]);
			entity.setAgreedFx(new BigDecimal(entityElements[2]));

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INSTRUCTION_FILE_DATE_PATTERN);
			LocalDate localDate = LocalDate.parse(entityElements[4], formatter);
			entity.setInstructionDate(localDate);

			localDate = LocalDate.parse(entityElements[5], formatter);
			entity.setSettlementDate(localDate);

			entity.setUnits(Integer.parseInt(entityElements[6]));
			entity.setPricePerUnit(new BigDecimal(entityElements[7]));

			// adding entity to list
			entityList.add(entity);
		} catch (Exception e) {
			System.out.println("Error in File entry " + entityDetails);
			e.printStackTrace();
		}

	}

}
