package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.PostUser;
import utils.ConfigReader;


public class UserStepDef {
    Response response;
    PostUser user = new PostUser();
    String userID = "";

    @Given("Post a user using these inputs {string} and {string}")
    public void post_a_user_using_these_inputs_and(String username, String password) {
        user.setUserName(username);
        user.setPassword(password);
        response = RestAssured.given().header("Content-Type", "application/json")
                .body(user)
                .when().post(ConfigReader.getPropertyValue("userCreationPath"));
    }

    @Then("Verify status code is {int}")
    public void verify_status_code_is(int statuscode) {
        response.prettyPrint();
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(statuscode, actualStatusCode);
    }

    @And("Verify response contains correct {string}")
    public void verifyResponseContainsCorrect(String username) {

        String responseString = response.then().log().all().extract().asString();
        JsonPath jp = new JsonPath(responseString);
        userID = jp.get("userID");
        String actualUsername = jp.get("username");
        Assert.assertEquals(username, actualUsername);

    }


    @And("Delete user with {string} and {string}")
    public void deleteUserWithPassword(String username, String password)  {
        response = given().header("Content-Type","application/json").auth().preemptive().basic(username, password)
                .when().delete(ConfigReader.getPropertyValue("userCreationPath")+"/"+userID);
        response.prettyPrint();
        Assert.assertEquals(204,response.statusCode());
    }


}