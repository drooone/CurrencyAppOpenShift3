package apps.programing.eu.service;

import java.util.List;

import apps.programing.eu.model.Currency;

public interface CurrencyService {
	
	 public List<Object> listCurrency();
	 public List<Currency> listCurrencyAverageValues(String code, String dateFrom, String dateTo);

}
