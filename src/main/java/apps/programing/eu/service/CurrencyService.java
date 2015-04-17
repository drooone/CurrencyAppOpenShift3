package apps.programing.eu.service;

import java.util.List;

public interface CurrencyService {
	
	 public List<Object> listCurrency();
	 public List<Object> listCurrencyAverageValues(String code);

}
