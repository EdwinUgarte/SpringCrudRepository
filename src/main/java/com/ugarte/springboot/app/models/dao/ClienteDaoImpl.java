package com.ugarte.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;


import com.ugarte.springboot.app.models.entity.Cliente;

@Repository("clienteDaoJPA")//!Registramos esta clase como un repositorio spring para despues poderlo inyectar
public class ClienteDaoImpl implements IClienteDao{
    
    @PersistenceContext //?Inyecta de forma automatica al Entity al motor de memoria H2
    private EntityManager eManager; //!Se encarga de hacer las operaciones a bases de datos pero a nivel Objetos en las clases Entity 

    @SuppressWarnings("unchecked")//!Para suprimir los mensajes que marca el editor
    @Override
    public List<Cliente> findAll() {
        //? creamos una consulta con .createQuery y despues jalamos el resultado con .getResultList()
        return eManager.createQuery("from Cliente").getResultList();
    }
    
    @Override
     public Cliente buscar(Long id) {
            
    return eManager.find(Cliente.class, id);//!Buscamos al usuario con find que nos pide la clase y la llave primaria
            
    }
    
    @Override
    public void save(Cliente cliente) {
        if(cliente.getId() != null && cliente.getId() > 0){
            eManager.merge(cliente);//!mergue(Object entity) actualiza los datos del objeto 
        }else{
            eManager.persist(cliente);//!persist(Object entity) almacena un objeto entity en el contexto de persistencia y en la base de datos     
        }
        
    }

    @Override
    public void eliminar(Long id) {
        Cliente cliente = buscar(id);
        eManager.remove(cliente);
        
    }
    
    
}
