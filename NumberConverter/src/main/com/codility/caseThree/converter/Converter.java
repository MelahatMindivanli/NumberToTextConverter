package main.com.codility.caseThree.converter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.com.codility.caseThree.constants.*;

public class Converter {

	public Converter() {
	}

	public static List<String> splitUnit(String enteredValue) {
		List<String> list = new ArrayList<String>();

		if (isContainDot(enteredValue)) {
			for (String value : enteredValue.split("\\.")) {
				list.add(value);// 0 -> dollar 1-> cent
			}
		} else {
			list.add(enteredValue);// 0 -> dollar
		}
		return list; /* returns currency list */
	}

	public static boolean isContainDot(String enteredValue) {
		if (enteredValue.contains(".")) {return true;}
		return false;
	}

	public static String replaceComma(String enteredValue) {
		if (enteredValue.contains(",")) {
			enteredValue = enteredValue.replaceFirst(",", "");
		}
		return enteredValue;
	}

	public static String convertSmallerThanThousand(int number) {
		String remainder;
		if (number % 100 < 20) {
			remainder = Constants.doubles[number % 100];
			number /= 100;

		} else {
			remainder = Constants.doubles[number % 10];
			number /= 10;

			remainder = Constants.tens[number % 10] + remainder;
			number /= 10;
		}
		if (number == 0)
			return remainder;
		return Constants.doubles[number] + " hundred" + remainder;
	}

	public static String convert(long number) {

		if (number == 0) {
			return "zero";
		}

		String strNumber = Long.toString(number);
		String mask = "000000000000";
		DecimalFormat decialFormat = new DecimalFormat(mask);
		strNumber = decialFormat.format(number);

		int billions = Integer.parseInt(strNumber.substring(0, 3));
		int millions = Integer.parseInt(strNumber.substring(3, 6));
		int hundredThousands = Integer.parseInt(strNumber.substring(6, 9));
		int thousands = Integer.parseInt(strNumber.substring(9, 12));

		String resBillions;
		switch (billions) {
		case 0:
			resBillions = "";
			break;
		case 1:
			resBillions = convertSmallerThanThousand(billions) + " billion ";
			break;
		default:
			resBillions = convertSmallerThanThousand(billions) + " billion ";

		}
		String textValue = resBillions;

		String resMillions;
		switch (millions) {
		case 0:
			resMillions = "";
			break;
		case 1:
			resMillions = convertSmallerThanThousand(millions) + " million ";
			break;
		default:
			resMillions = convertSmallerThanThousand(millions) + " million ";
		}
		textValue = textValue + resMillions;

		String resHundredThousands;

		switch (hundredThousands) {
		case 0:
			resHundredThousands = "";
			break;
		case 1:
			resHundredThousands = "one thousand ";
			break;
		default:
			resHundredThousands = convertSmallerThanThousand(hundredThousands) + " thousand ";
		}
		textValue = textValue + resHundredThousands;

		String resThousand;
		resThousand = convertSmallerThanThousand(thousands);
		textValue = textValue + resThousand;

		return textValue;
	}

	public static void main(String[] args) {
		Scanner scanner = null;
		Converter converter = null;
		String enteredValue;
		List<String> list = new ArrayList<String>();

		try {
			converter = new Converter();
			scanner = new Scanner(System.in);
			System.out.print("Enter the amount : ");
			enteredValue = scanner.next();

			enteredValue = replaceComma(enteredValue);// if enteredValue contains comma, replace it to ""

			list = splitUnit(enteredValue);// dollar - cent

			// only dollar
			if (list.size() == 1) {
				System.out.println("Amount in words --> " + converter.convert(Long.valueOf(list.get(0))) + " dollars");
			}

			// dollar and cent
			if (list.size() == 2) {
				System.out.print("Amount in words --> " + converter.convert(Long.valueOf(list.get(0))) + " dollars");
				System.out.print(converter.convert(Long.valueOf(list.get(1))) + " cent");
			}
		} catch (NumberFormatException e) {
			System.out.println("Please enter number value!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

	}
}
