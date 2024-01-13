package api.endpoints;
import api.payloads.Stores;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class StoreEndPoints {

    public static Response placeOrderOfPet(Stores payload) {

        return given()
                .contentType(ContentType.JSON)
//                .accept(ContentType.JSON)
                .body(payload)
                .when().post(Routes.post_store_order_url);


    }
    public static Response getStoreOrderDetail(long orderID){
        return given()
                .pathParams("orderId",orderID)
                .when().get(Routes.get_store_order_url);


    }
    public static Response deleteStoreOrder(long orderID){
        return given()
                .pathParams("orderId",orderID)
                .when().delete(Routes.delete_store_order_url);
    }
    public static Response getStoreInventoryByStatus() {
        return given()
                .when().get(Routes.store_Inventory_url);
    }

}
