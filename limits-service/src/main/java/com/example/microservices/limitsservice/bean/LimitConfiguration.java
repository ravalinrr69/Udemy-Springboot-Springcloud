package com.example.microservices.limitsservice.bean;

public class LimitConfiguration {

	int maximum;
	int minimum;
	
	public LimitConfiguration() {
		super();
	}
	
	public LimitConfiguration(int maximum, int minimum) {
		super();
		this.maximum = maximum;
		this.minimum = minimum;
	}
	public int getMaximum() {
		return maximum;
	}
	public int getMinimum() {
		return minimum;
	}
	
}
