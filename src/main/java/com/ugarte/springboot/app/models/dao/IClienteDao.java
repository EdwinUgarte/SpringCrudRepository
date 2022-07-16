package com.ugarte.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ugarte.springboot.app.models.entity.Cliente;


public interface IClienteDao extends CrudRepository<Cliente, Long>{

//!Implementando interface sin ningun metodo ya que al heredar de la clase CrudRepository esta cuenta con todos los metodos JPA CRUD por debajo, solo hay que implementar esta interface donde deseemos usarla
//?En este ejemplo ir a la clase IClienteServiceImpl y ver como se implemento y los metodos usados
//!OJO estos metodos solo funcionan con las clases Entity 

//*La estructura de la herencia es asi: CrudRepository<"TIPO DE DATO", "TIPO DE DATO DE LA LLAVE PRIMARIA"> */

//?Esta es la forma mas facil y rapida para poder crear un CRUD con esta clase CrudRepository de SpringBoot, consultar todos los metodos en la pagina
//?https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories
}
