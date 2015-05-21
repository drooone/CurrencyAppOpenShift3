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
	private Long id;
	@Temporal(value = TemporalType.DATE)
	private Date publicationDate;
	private String code;
	private Double averageExchangeRate;
	private String fullName;

	public String getCurrencyFullName() {
		return fullName;
	}

	public void setCurrencyFullName(final String currencyFullName) {
		this.fullName = currencyFullName;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long idCurrency) {
		this.id = idCurrency;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(final Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getCurrencyCode() {
		return code;
	}

	public void setCurrencyCode(final String currencyCode) {
		this.code = currencyCode;
	}

	public Double getAverageExchangeRate() {
		return averageExchangeRate;
	}

	public void setAverageExchangeRate(final Double averageExchangeRate) {
		this.averageExchangeRate = averageExchangeRate;
	}

	@Override
	public String toString() {
		return "Currency [idCurrency=" + id + ", publicationDate="
				+ publicationDate + ", currencyCode=" + code
				+ ", averageExchangeRate=" + averageExchangeRate
				+ ", currencyFullName=" + fullName + "]";
	}

}
