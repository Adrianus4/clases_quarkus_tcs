package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.acme.model.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
class ProductResourceTest {

    @Test
    void testGetAllProducts() {
        given()
          .when().get("/api/products")
          .then()
             .statusCode(200)
             .body("size()", not(0));
    }

    @Test
    void testGetProductById() {
        given()
          .when().get("/api/products/1")
          .then()
             .statusCode(200)
             .body("name", is("Laptop Gamer"));
    }

    @Test
    void testCreateProduct() {
        Product newProduct = new Product(null, "Monitor 4K", 399.99, "Pantallas");

        given()
          .contentType(ContentType.JSON)
          .body(newProduct)
          .when().post("/api/products")
          .then()
             .statusCode(201)
             .body("id", notNullValue())
             .body("name", is("Monitor 4K"))
             .body("price", is(399.99F));
    }
}

