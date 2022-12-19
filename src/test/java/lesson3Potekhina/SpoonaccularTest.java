package lesson3Potekhina;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class SpoonaccularTest extends lesson3Potekhina.AbstractTest {

    @Test
    void getTest() {
        given()
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        " includeIngredients=cheese,ham,egg&equipment=pan&maxCalories=500&apiKey=" +getApiKey())
                .then()
                .statusCode(200);

        given()
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "cuisine=chinese&diet=vegan&apiKey=" +getApiKey())
                .then()
                .statusCode(200);

        given()
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "minCalories=100&maxCalories=400&minProtein=20&maxProtein=30&apiKey=" +getApiKey())
                .then()
                .statusCode(200);

        given()
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "includeIngredients=broccoli&minVitaminC=20&minVitaminD=3&apiKey=" +getApiKey())
                .then()
                .statusCode(200);

        given()
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "type=drink&minAlcohol=20&maxAlcohol=30&apiKey=" +getApiKey())
                .then()
                .statusCode(200);

    }

    @Test
    void postTest() {

        String cuisine = given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","sushi")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .path("cuisine");
        System.out.println("cuisine: " + cuisine);

        String cuisine1 = given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","kimchi")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .path("cuisine");
        System.out.println("cuisine: " + cuisine1);

        String cuisine2 = given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","french fries")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .path("cuisine");
        System.out.println("cuisine: " + cuisine2);

        String cuisine3 = given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","pasta")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .path("cuisine");
        System.out.println("cuisine: " + cuisine3);

        String cuisine4 = given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","burger")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .path("cuisine");
        System.out.println("cuisine: " + cuisine4);

    }

    @Test
    void addMealTest() {
        String id = given()
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"date\": 1644881179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 avocado.\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/geekbrains/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", getApiKey())
                .delete("https://api.spoonacular.com/mealplanner/geekbrains/items/" + id)
                .then()
                .statusCode(200);
    }

}
