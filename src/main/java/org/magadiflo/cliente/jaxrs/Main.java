package org.magadiflo.cliente.jaxrs;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
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
        listar(rootUri);

        System.out.println("================ CREANDO ================");
        Curso nuevoCurso = new Curso();
        nuevoCurso.setNombre("Curso avanzado de GIT");
        nuevoCurso.setDescripcion("Curso avanzado de GIT");
        nuevoCurso.setDuracion(780D);
        nuevoCurso.setInstructor("Andres Guzman");

        Entity<Curso> entityHeader = Entity.entity(nuevoCurso, MediaType.APPLICATION_JSON);
        curso = rootUri.request(MediaType.APPLICATION_JSON).post(entityHeader, Curso.class);
        System.out.println(curso);
        listar(rootUri);

        System.out.println("================ EDITANDO ================");
        Curso editarCurso = curso;
        editarCurso.setNombre("Curso avanzado de GIT y GITHUB");
        entityHeader = Entity.entity(editarCurso, MediaType.APPLICATION_JSON);
        curso = rootUri.path("/" + curso.getId()).request(MediaType.APPLICATION_JSON).put(entityHeader, Curso.class);
        System.out.println(curso);
        listar(rootUri);

        System.out.println("================ ELIMINANDO ================");
        rootUri.path("/" + curso.getId()).request().delete();
        listar(rootUri);
    }

    private static void listar(WebTarget rootUri) {
        System.out.println("================ LISTA ACTUALIZADA ================");
        List<Curso> cursos = rootUri.request(MediaType.APPLICATION_JSON)
                .get(Response.class)
                .readEntity(new GenericType<List<Curso>>() {
                });
        cursos.forEach(System.out::println);
    }
}
