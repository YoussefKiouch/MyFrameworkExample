package Api.endpoints;

//UserEndpoints.java
//Created for perform create, Read, Update, Delete


import Payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import  io.restassured.response.Response;

public class UserEndPoints {

   public static Response CreateUser(User payload)
   {
      Response response=given()
               .contentType(ContentType.JSON)
               .accept(ContentType.JSON)
               .body(payload)
               .when()
               .post(Routes.Post_Url);

              return response;
   }


    public static Response ReadUser(String userName)
    {
        Response response=given()
                .pathParam("username",userName)
                .when()
                .get(Routes.Get_Url);

        return response;
    }


    public static Response UpdateUser(User payload, String userName)
    {
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",userName)
                .body(payload)
                .when()
                .put(Routes.Update_Url);

        return response;
    }

    public static Response DeleteUser(String UserName)
    {
        Response response=given()
                .pathParam("username",UserName)
                .when()
                .delete(Routes.Delete_Url);

        return response;
    }

}
