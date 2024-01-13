package api.tests;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class UserTests {
    Faker faker;
    User userPayload;
    Logger logger;
@BeforeClass
public void setUp(){
    faker=new Faker();
    userPayload=new User();
    userPayload.setId(faker.idNumber().hashCode());
    userPayload.setUsername(faker.name().username());
    userPayload.setFirstName(faker.name().firstName());
    userPayload.setLastName(faker.name().lastName());
    userPayload.setEmail(faker.internet().safeEmailAddress());
    userPayload.setPassword(faker.internet().password(5,10));
    userPayload.setPhone(faker.phoneNumber().cellPhone());
//    System.out.println(userPayload.getFirstName());

//    creating log file
     logger= LogManager.getLogger(this.getClass());
}
@Test(priority = 1)
    public void testPostUser(){
    logger.info("**************Creating User*******************");
    Response response=UserEndPoints.createUser(userPayload);
    response.then().log().all();
    Assert.assertEquals(response.getStatusCode(),200);
    logger.info("**************User Created *******************");
}
@Test(priority = 2)
public void testGetUserTest(){
    logger.info("**************Retrieving user information*******************");
    Response response=UserEndPoints.readUser(this.userPayload.getUsername());
    response.then().log().all();
    Assert.assertEquals(response.getStatusCode(),200);
    logger.info("**************Created user information is displayed *******************");

}
    @Test(priority = 3)
    public void updateUserTest(){
//    update payload data
        logger.info("**************Updating  User*******************");
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        Response responseAfterUpdate=UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
        responseAfterUpdate.then().log().all().extract().body().asString();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
        String responseText = responseAfterUpdate.body().asString();
        logger.info("**************User is updated*******************");
       //        Checking data after update
        System.out.println("-------------afterUpdate get user detail is ------------");
        Response getResponseAfterUpdate=UserEndPoints.readUser(this.userPayload.getUsername());
        getResponseAfterUpdate.then().log().all();
        Assert.assertEquals(getResponseAfterUpdate.getStatusCode(),200);

    }
    @Test(priority = 4)
    public void testDeleteUserTest() {
        logger.info("**************deleting User*******************");
        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("**************User deleted *******************");

    }


    }
