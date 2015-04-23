package apps.programing.eu.dao;

import java.util.List;

import apps.programing.eu.model.*; 

public interface CurrencyDAO {
	public List<Object> listCurrency();
	public List<Currency> listCurrencyAverageValues(String code, String dateFrom, String dateTo);

}
