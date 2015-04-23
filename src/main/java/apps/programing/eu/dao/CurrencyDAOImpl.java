package apps.programing.eu.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		List<Object> currencyList = session.createQuery(
				"SELECT idCurrency,publicationDate,currencyCode,currencyFullName, MAX(averageExchangeRate) AS maxValue,"
						+ " MIN(averageExchangeRate) AS minValue, AVG(averageExchangeRate) AS avgValue "
						+ "FROM Currency GROUP BY currencyCode ORDER BY currencyFullName ASC").list();
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
	public List<Currency> listCurrencyAverageValues(String code, String dateFrom, String dateTo) {
		Session session = this.sessionFactory.getCurrentSession();
		System.out.println("SIZE:");
		SimpleDateFormat myDate = new SimpleDateFormat("mm/dd/yyyy");
		Date dateF = null;
		try {
			dateF = myDate.parse(dateFrom);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		@SuppressWarnings("unchecked")
		List<Currency> currencyList = session.createQuery("FROM Currency WHERE (currencyCode=:code)AND(publicationDate>:dateFrom)")
				.setString("code", code).setDate("dateFrom", dateF).list();
		System.out.println("SIZE:" + currencyList.size());
		return currencyList;
	}

}
