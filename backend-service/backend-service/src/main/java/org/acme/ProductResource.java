package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Product;
import org.acme.service.ProductService;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Path("/api/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService productService;

    @GET
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        return productService.getById(id)
                .map(product -> Response.ok(product).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity(Map.of("error", "Producto con ID " + id + " no encontrado"))
                        .build());
    }

    @POST
    public Response create(Product product) {
        if (product == null || product.getName() == null || product.getPrice() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "Los campos 'name' y 'price' son obligatorios"))
                    .build();
        }
        Product created = productService.addProduct(product);
        return Response.created(URI.create("/api/products/" + created.getId()))
                .entity(created)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Product product) {
        if (product == null || product.getName() == null || product.getPrice() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "Los campos 'name' y 'price' son obligatorios"))
                    .build();
        }
        return productService.update(id, product)
                .map(updated -> Response.ok(updated).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity(Map.of("error", "Producto con ID " + id + " no encontrado"))
                        .build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = productService.delete(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Producto con ID " + id + " no encontrado"))
                    .build();
        }
    }
}

