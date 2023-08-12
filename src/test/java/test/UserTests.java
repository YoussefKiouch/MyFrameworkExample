package test;

import Api.endpoints.UserEndPoints;
import Api.endpoints.UserEndPoints2;
import Payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class UserTests {

    Faker faker;
    User userPayLoad;
    public Logger logger;

    @BeforeClass
    public void SetupData() {
        faker = new Faker();
        userPayLoad = new User();

        userPayLoad.setId(faker.idNumber().hashCode());
        userPayLoad.setUsername(faker.name().username());
        userPayLoad.setFirstName(faker.name().firstName());
        userPayLoad.setLastName(faker.name().lastName());
        userPayLoad.setEmail(faker.internet().safeEmailAddress());
        userPayLoad.setPassword(faker.internet().password(5, 10));
        userPayLoad.setPhone(faker.phoneNumber().cellPhone());

        //logs

        logger = LogManager.getLogger(this.getClass());

    }

    @Test(priority = 1)

    public void TestPostUser() {
        logger.info("******Creating User********");
        Response response = UserEndPoints.CreateUser(userPayLoad);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("******User is created********");

    }

    @Test(priority = 2)
    public void TestGetUserByName() {
        logger.info("******Reading User Info********");

       Response response =  UserEndPoints.ReadUser(this.userPayLoad.getUsername());
       response.then().log().all();
       Assert.assertEquals(response.getStatusCode(),200);


    }

    @Test(priority = 3)
    public void TestUpdateUserName() {

        //Update data using payload

        userPayLoad.setFirstName(faker.name().firstName());
        userPayLoad.setLastName(faker.name().lastName());
        userPayLoad.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.UpdateUser(userPayLoad, this.userPayLoad.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        //checking data after update

        Response responseAfterUpdate = UserEndPoints.ReadUser(this.userPayLoad.getUsername());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);


    }
    @Test(priority = 4)
    public void TestDeleteUserByName()
    {

        Response response = UserEndPoints.DeleteUser(this.userPayLoad.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);


    }
}

