package step_definitions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import utils.ConfigReader;

public class Hooks {

    @Before
    public void setUp(){
        RestAssured.baseURI = ConfigReader.getPropertyValue("baseURI");

    }

    @After
    public void tearDown (Scenario scenario){

        if (scenario.isFailed()){

        }

    }
}
