package step_definitions;

import io.restassured.RestAssured;
import pojos.PostUser;
import utils.ConfigReader;

public class Test {

    public static void main(String[] args)  {

        PostUser user = new PostUser();
        user.setUserName("ElmUser03");
        user.setPassword("Test123!");

        System.out.println(user);

        RestAssured.baseURI=ConfigReader.getPropertyValue("baseURI");


        RestAssured
                .given().header("Content-Type", "application/json").body(user)
                .when().post("/Account/v1/User").prettyPrint();

    }

}

//USER => user object
    //Name
    //LastName
    //age
    //gender
    //id