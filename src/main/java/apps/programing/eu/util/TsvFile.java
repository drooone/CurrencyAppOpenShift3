package apps.programing.eu.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;

import apps.programing.eu.model.Currency;

/**
 * 
 * @author Piotr TSV file creator class
 */
public class TsvFile {
	private final List<Currency> listOfValues;
	private final Logger logger = Logger.getLogger(getClass());

	public TsvFile(final List<Currency> listOfValues) {
		super();
		this.listOfValues = listOfValues;
	}

	/**
	 * Method will create TSV data.tsv file source of data to D3 chart
	 */
	public void createTSVFile() {

		FileWriter fileWriter = null;

		System.out.println("");
		try {
			//fileWriter = new FileWriter("C:\\Users\\dombrowskip\\git\\currencyappopenshift3\\src\\main\\webapp\\resources\\data\\data.tsv");
			fileWriter = new FileWriter("CurrencyAppOpenShift3/src/main/webapp/resource/data/data.tsv");
										 
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("File not created");
		}
		final BufferedWriter bufferedWrite = new BufferedWriter(fileWriter);
		try {
		bufferedWrite.write("date");
		bufferedWrite.write("\t");
		bufferedWrite.write("close");
		bufferedWrite.write("\t");
		bufferedWrite.write("\n");
		} catch (IOException e) {
			logger.info("IOException problem when closing ");
		}
		
		for (final Iterator<Currency> iterator = listOfValues.iterator(); iterator.hasNext();) {
			final Currency currency = (Currency) iterator.next();
			final String publicationDate = dateConvertToString(currency.getPublicationDate());
			final String avgCurValue = String.valueOf(currency.getAverageExchangeRate());
			try {
				
				bufferedWrite.write(publicationDate);
				bufferedWrite.write("\t");
				bufferedWrite.write(avgCurValue);
				bufferedWrite.write("\t");
				bufferedWrite.write("\n");
				
			} catch (IOException e) {
				logger.info("IOException problem when closing ");
			}
		}
		try {
			bufferedWrite.close();
		} catch (IOException e) {
			logger.info("IOException problem when closing ");
		}
	}

	/**
	 * Convert date to String
	 * 
	 * @param date
	 *            Date Format
	 * @return date in String format
	 */
	public String dateConvertToString(final Date date) {

		return DateFormatUtils.format(date, "dd/MM/yyyy");
	}

}
