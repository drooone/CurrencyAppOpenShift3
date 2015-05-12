package apps.programing.eu.dao;

import java.util.List;

import apps.programing.eu.model.*; 
/**
 * 
 * @author Piotr
 * CurrencyDAO interface
 */
public interface CurrencyDAO {
	/**
	 * 
	 * @return list of currency with MAX/MIN/AVG values 
	 */
	 List<Object> listCurrency();
	 /**
	  * 
	  * @param code currency CODE
	  * @param dateFrom currency search range FROM
	  * @param dateTo currency search range TO
	  * @return
	  */
	 List<Currency> listCurrencyAverageValues(String code, String dateFrom, String dateTo);

}
