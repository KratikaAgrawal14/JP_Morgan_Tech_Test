# JP_Morgan_Tech_Test

# Problem statement
Create a report that shows
Amount in USD settled incoming everyday
Amount in USD settled outgoing everyday
Ranking of entities based on incoming and outgoing amount. Eg: If entity foo instructs the highest
amount for a buy instruction, then foo is rank 1 for outgoing

# Application implementation
The starting Point of the Aplication is Application.java
Interface TradeSettlement which defins settleTransaction() method and is implemented by below classes. 
The Two classes OutgoingSettlement and IncomingSettlement contains the code to for incoming and outgoing trade functionaities.
CSVReader reads the file and populate the entity object model
Entity object model contains the parameters as per the datafile.
TradeParams conatins the trade realated costrains and implementations eg Validating working day
SettlementConstants defines the constant configurable properties.

# To generate Amount in USD settled incoming everyday
Class IncomingSettlement overriden method settle Transaction to populate the tradeMap
Method settleTrasaction() calls CSVFileReader.readFile() method to read the file.
Method readFile() method reads the file stream and calls addEntity Method to generate and add the entity to the entityList.
Method addEntity() split the stream(EntityDetails) by the INSTRUCTION_FILE_SEPERATER and set the each params.
Param settlementDate is processed via TradeParams.getWorkingDay() method
    TradeParams defines the Map which assigns numerical values to the week days.
    getWorkingDay() check the workingday status as per currency and current day.
    getWorkingDay() returns the 0 if the Day of settlement date as working day or 1 or 2 (days to be added) to calculate next     working day.
Param USD is calculated as per requirment Price per unit * Units * AgreedFx

After tradeMap is populated get the map via getTradeMap() and print the result

# TO generate Amount in USD settled outgoing everyday
Above is implemented with class OutgoingSettlement

# Ranking of entities 
To generate the ranking for incoming nad outgoing call the respective class's getRankMap() method which sort the rankMap generated the settleTrasaction() method and print the rankings

# Error Handing 
If the data is null or empty in a particular line the code prints Data invaild or "Invaild Entity skipped :" in the output with entity details

If the data is invalid like invaild data type or wrong format the application will continue and print the invaild entity with exception and Error message.

# DataFlile
Data file is kept at the src/resource location and is considered as an CSV file with header.

# Assumption
The code currenlty cosider settlement date for both incoming and outgoing settlements




