package com.bignerdranch.android.bqtabs.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@CucumberOptions(features = "features",
glue = "com.bignerdranch.android.bqtabs.tests.steps")

@RunWith(Cucumber.class)
public class TestCase1 {
}
