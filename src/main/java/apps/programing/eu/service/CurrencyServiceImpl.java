package apps.programing.eu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apps.programing.eu.dao.CurrencyDAO;
import apps.programing.eu.model.Currency;

/**
 * @author Piotr Dombrowski 
 * CurrencyServiceImplementation
 */
@Service
public class CurrencyServiceImpl implements CurrencyService {

	private CurrencyDAO currencyDAO;

	/**
	 * currencyDAO setter
	 * @param currencyDAO
	 */
	public void setCurrencyDAO(final CurrencyDAO currencyDAO) {
		this.currencyDAO = currencyDAO;
	}

	@Override
	@Transactional
	public List<Object> listCurrency() {

		return this.currencyDAO.listCurrency();
	}

	@Override
	@Transactional
	public List<Currency> listCurrencyAverageValues(String code, String dateFrom, String dateTo) {

		return this.currencyDAO.listCurrencyAverageValues(code, dateFrom, dateTo);
	}

}
