package io.mohashi.resource;

import io.mohashi.model.Coffee.Coffee;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
//import java.util.List;

import static io.mohashi.model.Coffee.Coffee.newCoffee;
import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.ok;

@Path("/coffee")
public class CoffeeResource {

    private final List<Coffee> coffeeList;

    public CoffeeResource() {
        coffeeList = new ArrayList<>(asList(
                newCoffee()
                        .id(1L)
                        .name("Nespresso")
                        .describption("Coffee with strong taste")
                        .amountByBatch(10L).build(),
                newCoffee()
                        .id(2L)
                        .name("Nescafé")
                        .describption("Coffee with medium taste")
                        .amountByBatch(10L).build()
        ));
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response get() {
        return ok(coffeeList).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response insert(Coffee coffee) {
        coffee.id = (long) coffeeList.size() + 1;
        coffeeList.add(coffee);
        return Response.accepted().build();
    }

    @PUT
    @Path("{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response update(@PathParam Long id, Coffee coffee) {
        Coffee coffeeStored = coffeeList.stream()
                  .filter(c -> c.id.equals(id))
                  .findAny().orElseThrow(NotFoundException::new);

        if (nonNull(coffee.name))
            coffeeStored.name = coffee.name;
        if (nonNull(coffee.description))
            coffeeStored.description = coffee.description;
        if (nonNull(coffee.amountPerBatch))
            coffeeStored.amountPerBatch = coffee.amountPerBatch;

        return Response.accepted().build();
    }

    @GET
    @Path("{id}")
    @Produces(APPLICATION_JSON)
    public Response get(@PathParam Long id) {
        Coffee coffee = coffeeList
                .stream().filter(c -> c.id.equals(id))
                .findAny().orElseThrow(NotFoundException::new);
        return Response.ok(coffee).build();
    }
}
