package apps.programing.eu.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Currency")
public class Currency {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCurrency;
	@Temporal(value = TemporalType.DATE)
	private Date publicationDate;
	private String currencyCode;
	private Double averageExchangeRate;
	private String currencyFullName;

	public String getCurrencyFullName() {
		return currencyFullName;
	}

	public void setCurrencyFullName(final String currencyFullName) {
		this.currencyFullName = currencyFullName;
	}

	public Long getIdCurrency() {
		return idCurrency;
	}

	public void setIdCurrency(final Long idCurrency) {
		this.idCurrency = idCurrency;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(final Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(final String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Double getAverageExchangeRate() {
		return averageExchangeRate;
	}

	public void setAverageExchangeRate(final Double averageExchangeRate) {
		this.averageExchangeRate = averageExchangeRate;
	}

	@Override
	public String toString() {
		return "Currency [idCurrency=" + idCurrency + ", publicationDate="
				+ publicationDate + ", currencyCode=" + currencyCode
				+ ", averageExchangeRate=" + averageExchangeRate
				+ ", currencyFullName=" + currencyFullName + "]";
	}

}
