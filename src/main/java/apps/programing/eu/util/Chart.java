package apps.programing.eu.util;

import java.util.LinkedList;
import java.util.List;

import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.Line;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.LineStyle;
import com.googlecode.charts4j.Plots;

public class Chart {

	private List<Double> chartData;
	private String currencyCode;
	private String dateFrom;
	private String dateTo;

	public Chart(List<Double> chartData, String currencyCode, String dateFrom, String dateTo) {
		super();
		this.chartData = chartData;
		this.currencyCode = currencyCode;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;

	}

	public String createLineChart() {

		Line line = Plots.newLine(DataUtil.scaleWithinRange(0, 10, chartData), Color.newColor("CA3D05"), "Chart " + currencyCode);
		line.setLineStyle(LineStyle.MEDIUM_LINE);
		List<Integer> xAxisX = axisXelements();
		LineChart chart = GCharts.newLineChart(line);

		chart.setGrid(12, 10, 3, 2);
		chart.setSize(600, 500);
		chart.setTitle("Currency chart", Color.CORAL, 14);

		AxisLabels xAxis = AxisLabelsFactory.newNumericAxisLabels(xAxisX);
																		
		AxisLabels yAxis = AxisLabelsFactory.newAxisLabels("", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

		chart.addXAxisLabels(xAxis);
		chart.addYAxisLabels(yAxis);

		return chart.toURLString();
	}

	private List<Integer> axisXelements() {
		List<Integer> xAxisX = new LinkedList<Integer>();
		if (Integer.parseInt(subStringYear(dateTo)) > Integer.parseInt(subStringYear(dateFrom))) {
			for (int i = Integer.parseInt(subStringYear(dateFrom)); i <= Integer.parseInt(subStringYear(dateTo)); i++) {

				xAxisX.add(i);

			}
		}
		return xAxisX;
	}

	public String subStringYear(String date) {

		return date.substring(6);
	}

}
