package com.ugarte.springboot.app.models.dao;

import java.util.List;

import com.ugarte.springboot.app.models.entity.Cliente;

public interface IClienteDao {
    
    public List<Cliente> findAll();
    public void save(Cliente cliente);
    public Cliente buscar(Long id);
    public void eliminar(Long id);
}
