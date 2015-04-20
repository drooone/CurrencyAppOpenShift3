package apps.programing.eu.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/currency", method = RequestMethod.GET)
	public String drawPieChart(@RequestParam String code, Model model) {
		List currencyExchangeRateList = this.currencyService
				.listCurrencyAverageValues(code);

		Line line = Plots.newLine(
				DataUtil.scaleWithinRange(0, 10, currencyExchangeRateList),
				Color.newColor("CA3D05"), "Chart " + code);
		line.setLineStyle(LineStyle.MEDIUM_LINE);

		LineChart chart = GCharts.newLineChart(line);

		chart.setGrid(12, 10, 3, 2);
		chart.setSize(600, 500);
		chart.setTitle("Currency chart", Color.CORAL, 14);

		AxisLabels xAxis = AxisLabelsFactory.newAxisLabels("2007", "2008",
				"2009", "2010", "2011", "2012", "2013", "2014", "2015");
		AxisLabels yAxis = AxisLabelsFactory.newAxisLabels("", "1", "2", "3",
				"4", "5", "6", "7", "8", "9", "10");

		chart.addXAxisLabels(xAxis);
		chart.addYAxisLabels(yAxis);
		model.addAttribute("chartUrl", chart.toURLString());

		return "currency";
	}
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String listCurrency(Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		return "test";
	}
	
}
