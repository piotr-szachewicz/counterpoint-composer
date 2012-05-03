package pl.szachewicz.utils;

import java.text.DecimalFormat;

public class FormatUtils {

	private static DecimalFormat format = new DecimalFormat("#.##");

	public static String format(float number) {
		return format.format(number);
	}
}
