package com.tsty.initialize;

public class Initializer {
	static void initialize(final Upper anUpper) {
		if (anUpper instanceof Lower) {
			Lower lower = (Lower) anUpper;
			lower.lowerString = "lowerInited";
		}
		anUpper.upperString = "upperInited";
	}
}
