package test.com.codility.caseThree.test.converter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import main.com.codility.caseThree.converter.Converter;
import test.com.codility.caseThree.test.constants.ConstantsTest;

class ConverterTest {
	
	Converter converter = new Converter();

	@Before
	public void setUp() {
		converter = new Converter();
	}
		
	@Test
	public void testIsContainDot() {
		
		boolean realresult = true;
		boolean result = converter.isContainDot(ConstantsTest.testDatas[0]);
		Assert.assertTrue("FALSE RESULT", result == realresult);
	}

	@Test
	public void testReplaceComma() {
		String realresult = "174512";
		String result = converter.replaceComma(ConstantsTest.testDatas[19]); // 1745,12
		Assert.assertTrue("FALSE RESULT", result.equals(realresult)); // String .equals for comparing value not memory
	}

	@Test
	public void testSplitUnit() {
		List<String> realresult = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		boolean isEqual = false;
		realresult.add("1745");
		realresult.add("12");
		result = converter.splitUnit(ConstantsTest.testDatas[18]); // 1745.12
		isEqual = result.get(0).equals(realresult.get(0)) && result.get(1).equals(realresult.get(1));
		Assert.assertTrue("FALSE RESULT", isEqual);
	}

	@Test
	public void testConvertLessThousand() {
		String realresult = "sixteen";
		String strnumber = ConstantsTest.testDatas[2]; // 16
		String mask = "000000000000";
		DecimalFormat decialFormat = new DecimalFormat(mask);
		strnumber = decialFormat.format(Long.valueOf(ConstantsTest.testDatas[2]));
		int thousands = Integer.parseInt(strnumber.substring(9, 12));// nnnnnnnnn***
		String result = converter.convertSmallerThanThousand(thousands); // 1745.12
		result = result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " "); // removing extra spaces.
		realresult = realresult.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");// removing extra spaces.
		Assert.assertTrue("FALSE RESULT", result.equals(realresult));
	}

	@Test
	void testConvert() {
		String realresult = "two billion  one hundred forty seven million  four hundred eighty three thousand  six hundred forty seven dollars";
		List<String> list = new ArrayList<String>();
		list = converter.splitUnit(ConstantsTest.testDatas[11]); // 2147483647
		String result = "";
		if (list.size() == 1) {
			result = converter.convert(Long.valueOf(list.get(0))) + " dollars";
		}

		// dollar and cent
		if (list.size() == 2) {
			result = converter.convert(Long.valueOf(list.get(0))) + " dollars"
					+ converter.convert(Long.valueOf(list.get(1))) + " cent";
		}
		result = result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " "); // removing extra spaces.
		realresult = realresult.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");// removing extra spaces.
		Assert.assertTrue("FALSE RESULT", result.equals(realresult));
	}

}
