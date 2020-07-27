package com.in28minutes.springboot.StringCalculator;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class StringCalculator {

	static Logger logger = Logger.getLogger(StringCalculator.class.getName());

	public static int Add(String inputString) {
		String delimiter = ",|n";
		String numbersWithoutDelimiter = inputString;
		if (inputString.startsWith("//[")) {

			int delimiterIndex = inputString.indexOf("//") + 3;
			delimiter = "\\" + inputString.substring(delimiterIndex, delimiterIndex + 1);
			numbersWithoutDelimiter = inputString.substring(inputString.indexOf("\n") + 1);

		} else if (inputString.startsWith("//")) {
			int delimiterIndex = inputString.indexOf("//") + 2;
			delimiter = inputString.substring(delimiterIndex, delimiterIndex + 1);
			numbersWithoutDelimiter = inputString.substring(inputString.indexOf("n") + 1);
		}
		return calculateString(numbersWithoutDelimiter, delimiter);
	}

	private static int calculateString(final String numbers, final String delimiter) {
		int sum = 0;

		String[] numbersArray = numbers.replaceAll("\n", ",").split(delimiter);
		Set<Integer> negativeNumbers = new HashSet<>();

		for (String number : numbersArray) {
			if (!number.trim().isEmpty()) {

				int integerNum = Integer.parseInt(number.trim());

				if (integerNum < 0) {
					negativeNumbers.add(integerNum);
				} else if (integerNum <= 1000) {
					sum += integerNum;
				}

			}
		}
		composeExceptionMessage(negativeNumbers);
		return sum;

	}

	private static void composeExceptionMessage(Set<Integer> negativeNumbers) {

		if (negativeNumbers.size() > 0) {
			throw new RuntimeException("Negatives not allowed : " + negativeNumbers.toString());
		}

	}
}
