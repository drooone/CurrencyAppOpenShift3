package apps.programing.eu.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import apps.programing.eu.model.Currency;
/**
 * 
 * @author Piotr
 * CurrencyDAOImplementation
 */
public class CurrencyDAOImpl implements CurrencyDAO {
	/**
	 * session Factory object
	 */
	private SessionFactory sessionFactory;
	/**
	 * sessionFactory object
	 * @param sessionFactory
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyDAOImpl.class);
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Object> listCurrency() {
		final Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		final List<Object> currencyList = session.createQuery(
				"SELECT idCurrency,publicationDate,currencyCode,currencyFullName, MAX(averageExchangeRate) AS maxValue,"
						+ " MIN(averageExchangeRate) AS minValue, AVG(averageExchangeRate) AS avgValue "
						+ "FROM Currency GROUP BY currencyCode ORDER BY currencyFullName ASC")
						.list();
		
		/*
		 * Iterator itr = currencyList.iterator(); while (itr.hasNext()) {
		 * Object[] obj = (Object[]) itr.next(); Long idCurrency = (Long)
		 * obj[0];
		 * 
		 * Date publicationDate = (Date) obj[1]; String currencyCode = (String)
		 * obj[2]; Double max = (Double) obj[3]; Double min = (Double) obj[4];
		 * Double avg = (Double) obj[5]; System.out.println("ID:" + idCurrency +
		 * " publicationDate:" + publicationDate + " currencyCode:" +
		 * currencyCode + " max" + max + " min:" + min + " avg:" + avg); }
		 */
		return currencyList;
	}

	@Override
	public List<Currency> listCurrencyAverageValues(final String code, final String dateFrom,final String dateTo) {
		final Session session = this.sessionFactory.getCurrentSession();
		final SimpleDateFormat myDate = new SimpleDateFormat("dd/mm/yyyy",Locale.ENGLISH);
		Date dateF = null;
		Date dateT = null;
		try {
			dateF = myDate.parse(dateFrom);
			dateT = myDate.parse(dateTo);
		} catch (ParseException e) {
			LOGGER.error("ParseException wrong date format");
		}

		@SuppressWarnings("unchecked")
		final List<Currency> currencyList = session.createQuery("FROM Currency WHERE (currencyCode=:code)AND(publicationDate>:dateFrom)AND(publicationDate<:dateTo)")
				.setString("code", code)
				.setDate("dateFrom", dateF)
				.setDate("dateTo", dateT)
				.list();
		return currencyList;
	}

}
