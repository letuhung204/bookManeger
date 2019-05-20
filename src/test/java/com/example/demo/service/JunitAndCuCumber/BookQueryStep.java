package com.example.demo.service.JunitAndCuCumber;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/example/demo/service/JunitAndCuCumber/bookQuerry")
public class BookQueryStep {

}