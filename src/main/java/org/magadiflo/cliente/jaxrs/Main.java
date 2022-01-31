package org.magadiflo.cliente.jaxrs;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.magadiflo.cliente.jaxrs.models.Curso;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        WebTarget rootUri = client.target("http://localhost:8080/72.webapp-jaxrs/api").path("/cursos");

        System.out.println("================ POR ID ================");
        /**
         * 1° FORMA
         * Curso curso = rootUri.path("/1")
         *                 .request(MediaType.APPLICATION_JSON)
         *                 .get(Curso.class);
         * System.out.println(curso);
         */
        //2° FORMA: se obtienen más datos con el response
        Response response = rootUri.path("/1").request(MediaType.APPLICATION_JSON).get();
        Curso curso = response.readEntity(Curso.class);
        System.out.println(curso);
        System.out.println(response.getStatus());
        System.out.println(response.getMediaType());

        System.out.println("================ LISTANDO ================");
        List<Curso> cursos = rootUri.request(MediaType.APPLICATION_JSON)
                .get(Response.class)
                .readEntity(new GenericType<List<Curso>>(){});
        cursos.forEach(System.out::println);



    }
}
