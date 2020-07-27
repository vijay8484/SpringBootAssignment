package com.in28minutes.springboot.StringCalculator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StringCalculator.class, secure = false)
public class StringCalculatorTest {

	@Test
	public void emptyStringReturnsZero() {

		int result = StringCalculator.Add("");
		Assert.assertEquals(0, result);

	}

	@Test
	public void sumOfSingleNumberInString() {

		int result = StringCalculator.Add("1");
		Assert.assertEquals(1, result);

	}

	@Test
	public void sumOfTwoCommaSaperatedNumberInString() {

		int result = StringCalculator.Add("1,4");
		Assert.assertEquals(5, result);

	}

	@Test
	public void whenAnyNumberOfNumbersIsUsedThenReturnValuesAreTheirSum() {
		Assert.assertEquals(75, StringCalculator.Add("3,5,15,20,32"));
	}

	/*
	 * @Test(expected = RuntimeException.class) public void
	 * methodShouldHaveUptoTwoNumbers() {
	 * 
	 * int result = stringCalculator.Add("1,3,5"); Assert.assertEquals(6, result);
	 * 
	 * }
	 */

	@Test(expected = RuntimeException.class)
	public void whenNonNumberIsUsed() {

		int result = StringCalculator.Add("1,A");
		Assert.assertEquals(6, result);

	}

	@Test
	public void whenNewLineIsUsedBetweenNumbers() {
		Assert.assertEquals(6, StringCalculator.Add("1\n2,3"));
	}

	@Test
	public void whenDelimiterPresent() {
		Assert.assertEquals(3, StringCalculator.Add("//;\\n1;2"));
	}

	@Test(expected = RuntimeException.class)
	public void whenNegativeNumberIsUsedPresentInString() {
		StringCalculator.Add("3,-8,20,-15");
	}

	@Test
	public void dontConsiderNumberGreterThan1000() {
		Assert.assertEquals(5 + 1000 + 3, StringCalculator.Add("5,1000,1005,3,1540"));
	}

	@Test
	public void whenDelimiterOfAnyLength() {
		Assert.assertEquals(6, StringCalculator.Add("//[***]\n1***2***3"));
	}
}