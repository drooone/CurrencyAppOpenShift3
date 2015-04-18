package apps.programing.eu.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import apps.programing.eu.model.Currency;

public class CurrencyDAOImpl implements CurrencyDAO {


	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Object> listCurrency() {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Object> currencyList = session
				.createQuery(
						"SELECT idCurrency,publicationDate,currencyCode,currencyFullName, MAX(averageExchangeRate) AS maxValue,"
								+ " MIN(averageExchangeRate) AS minValue, AVG(averageExchangeRate) AS avgValue "
								+ "FROM Currency GROUP BY currencyCode ORDER BY currencyFullName ASC")
				.list();
		/*
		 * Iterator itr = currencyList.iterator(); while (itr.hasNext()) {
		 * 
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
	public List<Object> listCurrencyAverageValues(String code) {
		Session session = this.sessionFactory.getCurrentSession();
		System.out.println("SIZE:");
		@SuppressWarnings("unchecked")
		List<Object> currencyList = session.createQuery("Select averageExchangeRate FROM Currency WHERE currencyCode=:code").setString("code", code)
				.list();
		System.out.println("SIZE:" + currencyList.size());
		return currencyList;
	}

}
