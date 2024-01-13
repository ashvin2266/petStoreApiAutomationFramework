package api.tests;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import freemarker.log.Logger;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DDTests {
   @Test(priority = 1,dataProvider = "Data", dataProviderClass= DataProviders.class)
    public void createUserTest(String userID,String username,String firstname,String lastname,String email,String password,String phone)
    {

        User userPayload=new User();
        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(username);
        userPayload.setFirstName(firstname);
        userPayload.setLastName(lastname);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);
        System.out.println("*********************Creating user by data drive test****************");
        Response response= UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
    public void deleteUserByUsername(String userName){

       Response response= UserEndPoints.deleteUser(userName);
       response.then().log().all();
       Assert.assertEquals(response.getStatusCode(),200);

    }
}
