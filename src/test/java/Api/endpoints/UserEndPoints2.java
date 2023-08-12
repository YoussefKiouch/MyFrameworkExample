package Api.endpoints;
import static io.restassured.RestAssured.given;
import java.util.ResourceBundle;

import Payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


//UserEndpoints.java
//Created for perform create, Read, Update, Delete




public class UserEndPoints2 {

    static ResourceBundle getURl(){

        ResourceBundle routes = ResourceBundle.getBundle("routes");//load properties file
        return routes;

    }


   public static Response CreateUser(User payload)
   {

       String Post_Url = getURl().getString("Post_Url");
      Response response=given()
               .contentType(ContentType.JSON)
               .accept(ContentType.JSON)
               .body(payload)
               .when()
               .post(Post_Url);

              return response;
   }


    public static Response ReadUser(String userName)
    {
        String Get_Url = getURl().getString("Get_Url");
        Response response=given()
                .pathParam("username",userName)
                .when()
                .get(Get_Url);

        return response;
    }


    public static Response UpdateUser(User payload, String userName)
    {

        String Update_Url = getURl().getString("Update_Url");
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",userName)
                .body(payload)
                .when()
                .put(Update_Url);

        return response;
    }

    public static Response DeleteUser(String UserName)
    {
        String Delete_Url = getURl().getString("Delete_Url");
        Response response=given()
                .pathParam("username",UserName)
                .when()
                .delete(Delete_Url);

        return response;
    }

}
