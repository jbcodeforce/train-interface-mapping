package ibm.gse.eda;

import static io.restassured.RestAssured.with;

import org.junit.jupiter.api.Test;

import ibm.gse.eda.app.dto.TrainSearchRequest;
import ibm.gse.eda.app.dto.TrainSearchResponse;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@QuarkusTest
public class SearchResourceTest {

    @Test
    public void sendBasicSearchRequest() {
        TrainSearchRequest req = new TrainSearchRequest();
        req.destinationLocation = "London";
        req.originLocation = "Paris";
        req.outwardDate = "03/01/2021";
    
        Response resp = with().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
          .body(req)
          .when().post("/consolidatorA")
          .then()
             .statusCode(200)
             .contentType(ContentType.JSON)
        .extract()
        .response();
        TrainSearchResponse searchResp= resp.body().as(TrainSearchResponse.class);
        System.out.println(searchResp.routerList.get(0).supplier);
    }

}