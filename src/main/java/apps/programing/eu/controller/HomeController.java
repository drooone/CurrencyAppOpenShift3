package apps.programing.eu.controller;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import apps.programing.eu.model.Currency;
import apps.programing.eu.service.CurrencyService;
import apps.programing.eu.util.Chart;
import apps.programing.eu.util.TsvFile;

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
		logger.info("Welcome home! The client locale is {}.");
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

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String listCurrency(final Locale locale, final Model model,HttpServletRequest request) {
		
		//final StringBuilder listOfAvgValues = new StringBuilder("");
		final List<Currency> curExchRateList = this.currencyService.listCurrencyAverageValues("AUD", "01/01/2007", "01/04/2015");
		TsvFile tsv = new TsvFile(curExchRateList);
		//tsv.createTSVFile();
		String path = request.getSession().getServletContext().getRealPath("/");
		System.out.println("DDD:" + path);
		ServletContext servletContext = request.getSession().getServletContext();
		String relativeWebPath = "resource/data/data.tsv";
		String absoluteDiskPath = servletContext.getRealPath("/");
	 	

		String envVar = System.getenv("OPENSHIFT_ENV_VAR");
		System.out.println("Sciecha:" + envVar);
		/*for (final Iterator<Currency> iterator = curExchRateList.iterator(); iterator.hasNext();) {
			final Currency cur = (Currency) iterator.next();
			listOfAvgValues.append(cur.getAverageExchangeRate());
			listOfAvgValues.append(',');
		}
		final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		final String formattedDate = dateFormat.format(new Date());
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("averageValuesList", listOfAvgValues);*/
		model.addAttribute("path", absoluteDiskPath);
		return "test";
	}

}
