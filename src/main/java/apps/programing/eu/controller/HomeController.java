package apps.programing.eu.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import apps.programing.eu.model.Currency;
import apps.programing.eu.service.CurrencyService;
import apps.programing.eu.util.Chart;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private final Logger			logger			= Logger.getLogger(getClass());
	private CurrencyService currencyService;
	
	/**
	 * Returns
	 * @param currencyService
	 */
	@Autowired(required = true)
	@Qualifier(value = "currencyService")
	public void setCurrencyService(final CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(final Locale locale, final Model model) {
		
		model.addAttribute("currency", new Currency());
		model.addAttribute("listCurrency", currencyService.listCurrency());

		final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		final String formattedDate = dateFormat.format(new Date());

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	/**
	 * Return currency with chart page
	 * @param currencyCode - currency code
	 * @param dateFrom - date from range
	 * @param dateTo - date to range
	 * @param model - model
	 * @return - currency.jsp page
	 */
	@RequestMapping(value = "/currency", method = RequestMethod.GET)
	public String drawPieChart(@RequestParam final String currencyCode, final Model model) {

		final List<Double> avgValuesList = new LinkedList<Double>();
		final List<Currency> curExchRateList = this.currencyService.listCurrencyAverageValues(currencyCode, "01/01/2007", "01/04/2015");
		for (Iterator<Currency> iterator = curExchRateList.iterator(); iterator.hasNext();) {
			final Currency cur = (Currency) iterator.next();
			avgValuesList.add(cur.getAverageExchangeRate());

		}

		final Chart chart = new Chart(avgValuesList, currencyCode, "01/01/2007", "01/04/2015");
		model.addAttribute("currencyCode", currencyCode);
		model.addAttribute("chartUrl", chart.createLineChart());

		return "currency";
	}
	/**
	 * modifies chart to specific date range
	 * 
	 * @param model
	 * @param currencyCode - currency code
	 * @param dateFrom - date from range
	 * @param dateTo - date to range
	 * @return currency.jsp page
	 */
	@RequestMapping(value = "/currency2", method = RequestMethod.POST)
	public String drawChartWithDates(final Model model, @RequestParam final String currencyCode,
									 @RequestParam(required=false) String dateFrom, @RequestParam(required=false) String dateTo
									) {
		
		if("".equals(dateFrom)){
		
			dateFrom = "01/01/2007";
		}
		if("".equals(dateTo)){
			
			dateTo = "01/04/2015";
		}

		final List<Double> averageValuesList = new LinkedList<Double>();
		final List<Currency> curExchRateList = this.currencyService.listCurrencyAverageValues(currencyCode, dateFrom, dateTo);
		for (final Iterator<Currency> iterator = curExchRateList.iterator(); iterator.hasNext();) {
			final Currency cur = (Currency) iterator.next();
			averageValuesList.add(cur.getAverageExchangeRate());

		}

		final Chart chart = new Chart(averageValuesList, currencyCode, dateFrom, dateTo);
		model.addAttribute("currencyCode", currencyCode);
		model.addAttribute("chartUrl", chart.createLineChart());
		model.addAttribute("dateFrom", dateFrom);
		model.addAttribute("dateTo", dateTo);

		return "currency";
	}
	
	/**
	 * Returns alternative chart made with D#.js library
	 * 
	 * @param locale
	 * @param model
	 * @param currencyCode - currency Code
	 * @param dateFrom - date From range
	 * @param dateTo - date to range
	 * @return currencyD3.jsp
	 */
	@RequestMapping(value = "currencyD3", method = RequestMethod.GET)
	public String listCurrency(final Locale locale, final Model model, @RequestParam final String currencyCode,
			@RequestParam(required=false) String dateFrom, @RequestParam(required=false) String dateTo) {
		
		final StringBuilder listOfDataValues = new StringBuilder("");
		listOfDataValues.append('[');
		
		if("".equals(dateFrom)){
			
			dateFrom = "01/01/2007";
		}
		if("".equals(dateTo)){
			
			dateTo = "01/04/2015";
		}
		final List<Currency> curExchRateList = this.currencyService.listCurrencyAverageValues(currencyCode, dateFrom, dateTo);
		

		for (final Iterator<Currency> iterator = curExchRateList.iterator(); iterator.hasNext();) {
			final Currency cur = (Currency) iterator.next();
			
			final String dateStringFormat = DateFormatUtils.format(cur.getPublicationDate(), "dd/MM/yyyy");
			listOfDataValues.append('[');
			listOfDataValues.append('"');
			listOfDataValues.append(dateStringFormat);
			listOfDataValues.append('"');
			listOfDataValues.append(',');
			listOfDataValues.append(cur.getAverageExchangeRate());
			listOfDataValues.append(']');
			listOfDataValues.append(',');
			
		}
			listOfDataValues.append(']');

		model.addAttribute("listOfDataValues", listOfDataValues);
		return "currencyD3";
	}

}
