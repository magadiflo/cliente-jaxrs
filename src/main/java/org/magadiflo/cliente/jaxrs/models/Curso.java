package org.magadiflo.cliente.jaxrs.models;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement //Solo para cuando se trabaje con XML. Para JSON no es necesario
public class Curso {

    private Long id;
    private String nombre;
    private String descripcion;
    private String instructor;
    private Double duracion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Curso{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", descripcion='").append(descripcion).append('\'');
        sb.append(", instructor='").append(instructor).append('\'');
        sb.append(", duracion=").append(duracion);
        sb.append('}');
        return sb.toString();
    }
}
