package com.ugarte.springboot.app.models.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugarte.springboot.app.models.dao.IClienteDao;
import com.ugarte.springboot.app.models.entity.Cliente;

@Service("clienteService")
public class iClienteServiceImpl implements iClienteService {

    //!AQUI ESTAMOS IMPLEMENTANDO LA INTERFACE IClienteDao para poder usar todos sus metodos CRUD
    @Autowired
    private IClienteDao clienteDao;

    @Override
    @Transactional(readOnly = true)//!Declaramos que solo es de lectura y no de escritura
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        clienteDao.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente buscar(Long id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        clienteDao.deleteById(id);
    }

    
}
