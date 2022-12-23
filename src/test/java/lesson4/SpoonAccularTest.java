package lesson4;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SpoonAccularTest extends AbstractTest {

    @Test
    void getRecipePositiveTest() {
        given().spec(getRequestSpecification())
                .when()
                .queryParam("includeIngredients ", " potato")
                .queryParam("excludeCuisine", "italian, american, greek")
                .get("https://api.spoonacular.com/recipes/complexSearch?includeIngredients=potato&excludeCuisine=italian, american, greek")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getRecipePositiveTest1() {
        given().spec(getRequestSpecification())
                .when()
                .queryParam("type", "soup")
                .queryParam("excludeIngredients", "onion")
                .queryParam("maxReadyTime", "30")
                .queryParam("minFat", "5")
                .queryParam("maxFat", "30")
                .get("https://api.spoonacular.com/recipes/complexSearch?type=soup&excludeIngredients=onion&maxReadyTime=30&minFat=5&maxFat=30")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getRecipePositiveTest2() {
        given().spec(getRequestSpecification())
                .when()
                .queryParam("type", "drink")
                .queryParam("minAlcohol", "20")
                .queryParam("maxAlcohol", "30")
                .get("https://api.spoonacular.com/recipes/complexSearch?type=drink&minAlcohol=20&maxAlcohol=30")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getRecipePositiveTest3() {
        given().spec(getRequestSpecification())
                .when()
                .queryParam("includeIngredients", "pecan")
                .queryParam("minCaffeine", "20")
                .queryParam("minCalories", "700")
                .get("https://api.spoonacular.com/recipes/complexSearch?includeIngredients=pecan&minCaffeine=20&minCalories=700")
                .then()
                .spec(responseSpecification);
    }


    @Test
    void getRecipePositiveTest4() {
        given().spec(getRequestSpecification())
                .when()
                .queryParam("minVitaminB1", "2")
                .queryParam("minVitaminB2", "1")
                .queryParam("minVitaminB3", "2")
                .queryParam("minVitaminB6", "2")
                .queryParam("minVitaminB12", "3")
                .get("https://api.spoonacular.com/recipes/complexSearch?minVitaminB1=2&minVitaminB2=1&minVitaminB3=2&minVitaminB6=2&minVitaminB12=3")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getAccountInfoWithExternalEndpointTest(){
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title","Burger")
                .post("https://api.spoonacular.com/recipes/cuisine").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("American"));
    }

    @Test
    void getAccountInfoWithExternalEndpointTest1(){
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title","sushi")
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("Japanese"));
    }

    @Test
    void getAccountInfoWithExternalEndpointTest2(){
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title","kimchi")
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("Korean"));
    }


    @Test
    void getAccountInfoWithExternalEndpointTest3(){
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title","chicken pie")
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("Mediterranean"));
    }

    @Test
    void getAccountInfoWithExternalEndpointTest4(){
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title","french fries")
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("American"));
    }



    @Test
    void testGetShoppingList(){
        given().spec(requestSpecification)
                .when()
                .pathParam("username","dsky")
                .queryParam("hash", "4b5v4398573406")
                .get("https://api.spoonacular.com/mealplanner/dsky/shopping-list")
                .then()
                .statusCode(200);
    }

    @Test
    void testAddToShoppingList(){
        given().spec(requestSpecification)
                .when()
                .pathParam("username","dsky")
                .queryParam("hash", "4b5v4398573406")
                .queryParam("item", "1 package baking powder")
                .queryParam("aisle", "Baking")
                .post("https://api.spoonacular.com/mealplanner/dsky/shopping-list")
                .then()
                .statusCode(200);
    }


    @Test
    void testDeleteFromShoppingList(){
        given().spec(requestSpecification)
                .when()
                .pathParam("username","dsky")
                .pathParam("id", "15678")
                .queryParam("hash", "4b5v4398573406")
                .post("https://api.spoonacular.com/mealplanner/dsky/shopping-list/items/15678?hash=4b5v4398573406")
                .then()
                .statusCode(200);
    }

    @Test
    void testComputeShoppingList1(){
        given().spec(requestSpecification)
                .when()
                .post("https://api.spoonacular.com/mealplanner/shopping-list/compute")
                .then()
                .statusCode(200);
    }


}