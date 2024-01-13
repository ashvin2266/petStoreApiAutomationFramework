package api.endpoints;
/* here we keep all url of pet store user modules
* Swager URI----https://petstore.swagger.io
*Create users----https://petstore.swagger.io/v2/user
* Get Users---https://petstore.swagger.io/v2/user/{username}
* Update user----https://petstore.swagger.io/v2/user/{username}
* Delete user----https://petstore.swagger.io/v2/user/{username}
*
*
* */
public class Routes {

    public static String base_url="https://petstore.swagger.io/v2";

//    User Modules url end points
    public static String post_url=base_url+"/user";
    public static String get_url= base_url+"/user/{username}";
    public static String update_url= base_url+"/user/{username}";
    public static  String delete_url= base_url+"/user/{username}";

//    store module url
public static String post_store_order_url=base_url+"/store/order";
    public static String get_store_order_url= base_url+"/store/order/{orderId}";
    public static  String delete_store_order_url= base_url+"/store/order/{orderId}";
    public static  String store_Inventory_url= base_url+"/store/inventory";
}
