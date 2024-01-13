package api.tests;

import api.endpoints.StoreEndPoints;
import api.payloads.Stores;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.apache.poi.sl.draw.geom.GuideIf.Op.min;

public class StoreTests {
    Faker faker;
    Stores storePayload;
    long orderId;

    @BeforeClass
    public void setUpPayloadData(){
        faker=new Faker();
        storePayload= new Stores();
        storePayload.setId(0);
        storePayload.setPetId(faker.hashCode());
        storePayload.setQuantity(3);
        storePayload.setShipDate("2023-12-29T23:43:35.155Z");
        storePayload.setComplete("true");
        storePayload.setStatus("placed");



    }
    @Test(priority = 1)
    public  void createOrderTest(){
        Response response= StoreEndPoints.placeOrderOfPet(storePayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        String responseBody= response.body().asString();
        JsonPath js=new JsonPath(responseBody);
        orderId=js.get("id");
        System.out.println("***************Order ID is ******"+orderId);

    }
    @Test(priority = 2)
    public  void getStoreOrderDetailTest(){
        Response response= StoreEndPoints.getStoreOrderDetail(orderId);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }
    @Test(priority = 3)
    public void deleteStoreOrderTest(){
        Response response=StoreEndPoints.deleteStoreOrder(orderId);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println("************Response after delete store order*********");
        Response responseAfterDelete = StoreEndPoints.getStoreOrderDetail(orderId);
        responseAfterDelete.then().log().all();
        Assert.assertEquals(responseAfterDelete.getStatusCode(),404);
        Assert.assertEquals(responseAfterDelete.getStatusLine(),"HTTP/1.1 404 Not Found");


    }
    @Test(priority = 4)
    public void getStoreInventoryByStatusTest()
    {
        Response response = StoreEndPoints.getStoreInventoryByStatus();
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
