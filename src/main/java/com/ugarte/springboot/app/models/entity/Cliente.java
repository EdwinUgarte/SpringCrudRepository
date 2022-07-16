package com.ugarte.springboot.app.models.entity;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;



@Entity // !Se anota ya que sera nuestra tabla en la Bd
@Table(name = "clientes") // ?Damos nombre a la tabla que vamos a crear
// !Es recomendado implementar Serelizable para mantener la consistencia cuando enviamos datos convertidos a alguna Bd,JSON,memoria o http
public class Cliente implements Serializable {

    @Id // !Notacion para decir que esta es la llave primaria en la BD
    @GeneratedValue(strategy = GenerationType.IDENTITY) // !Indicamos que queremos que sea autoincremental
    private Long id;
    
    @NotEmpty //!Se tiene que agregar la dependencia de spring validation en el archivo pom
    private String nombre;
    @NotEmpty
    private String apellido;
    @NotEmpty
    private String email;

    @Column(name = "create_at")//!Sirve para cambiar el nombre del atributo en la base de datos
    @Temporal(TemporalType.DATE)//!Esto solo sirve para dar formato a la fecha .DATE (solo fecha) .TIME(solo hora) .TIMESTAMP(hora y fecha)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createAt;

    @PrePersist //!Esta anotacion de JPA nos ayuda a ejecutar este metodo antes de hacer el persist("insertar")
    public void prePersist(){
        createAt = new Date();
    }

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

}
