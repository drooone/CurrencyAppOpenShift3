package apps.programing.eu.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.Line;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.LineStyle;
import com.googlecode.charts4j.Plots;

import apps.programing.eu.service.CurrencyService;
import apps.programing.eu.util.Chart;
import apps.programing.eu.model.Currency;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private CurrencyService currencyService;

	@Autowired(required = true)
	@Qualifier(value = "currencyService")
	public void setCurrencyService(CurrencyService cs) {
		this.currencyService = cs;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		model.addAttribute("currency", new Currency());
		model.addAttribute("listCurrency", this.currencyService.listCurrency());

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	/**
	 * Return currency with chart page
	 * 
	 * @param currencyCode
	 *            - currency code
	 * @param dateFrom
	 *            - date from range
	 * @param dateTo
	 *            - date to range
	 * @param model
	 *            - model
	 * @return - currency.jsp page
	 */
	@RequestMapping(value = "/currency", method = RequestMethod.GET)
	public String drawPieChart(@RequestParam String currencyCode, @RequestParam String dateFrom, @RequestParam String dateTo, Model model) {

		System.out.println("DATe from:" + dateFrom + " dateTo:" + dateTo);
		List<Double> averageValuesList = new LinkedList<Double>();
		List<Currency> currencyExchangeRateList = this.currencyService.listCurrencyAverageValues(currencyCode, dateFrom, dateTo);
		for (Iterator<Currency> iterator = currencyExchangeRateList.iterator(); iterator.hasNext();) {
			Currency cur = (Currency) iterator.next();
			averageValuesList.add(cur.getAverageExchangeRate());

		}

		Chart chart = new Chart(averageValuesList, currencyCode, dateFrom, dateTo);
		model.addAttribute("currencyCode", currencyCode);
		model.addAttribute("chartUrl", chart.createLineChart());

		return "currency";
	}

	@RequestMapping(value = "/currency2", method = RequestMethod.POST)
	public String drawChartWithDates(Model model, @RequestParam String currencyCode, @RequestParam String dateFrom, @RequestParam String dateTo) {

		List<Double> averageValuesList = new LinkedList<Double>();
		List<Currency> currencyExchangeRateList = this.currencyService.listCurrencyAverageValues(currencyCode, dateFrom, dateTo);
		for (Iterator<Currency> iterator = currencyExchangeRateList.iterator(); iterator.hasNext();) {
			Currency cur = (Currency) iterator.next();
			averageValuesList.add(cur.getAverageExchangeRate());

		}

		Chart chart = new Chart(averageValuesList, currencyCode, dateFrom, dateTo);
		model.addAttribute("currencyCode", currencyCode);
		model.addAttribute("chartUrl", chart.createLineChart());

		return "currency";
	}

	/*
	 * @RequestMapping(value = "test") public String listCurrency(Locale locale,
	 * Model model, HttpServletRequest request) {
	 * 
	 * Date date = new Date(); DateFormat dateFormat =
	 * DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
	 * 
	 * String formattedDate = dateFormat.format(date);
	 * 
	 * model.addAttribute("serverTime", formattedDate); return "test"; }
	 */

}
