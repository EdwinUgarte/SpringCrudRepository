package com.ugarte.springboot.app.models.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugarte.springboot.app.models.dao.IClienteDao;
import com.ugarte.springboot.app.models.entity.Cliente;

@Service("clienteService")
public class iClienteServiceImpl implements iClienteService {

    @Autowired
    private IClienteDao clienteDao;

    @Override
    @Transactional(readOnly = true)//!Declaramos que solo es de lectura y no de escritura
    public List<Cliente> findAll() {
        return clienteDao.findAll();
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        clienteDao.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente buscar(Long id) {
        return clienteDao.buscar(id);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        clienteDao.eliminar(id);
    }

    
}
