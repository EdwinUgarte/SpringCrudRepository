package com.ugarte.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ugarte.springboot.app.models.entity.Cliente;
import com.ugarte.springboot.app.models.service.iClienteService;

@Controller
@SessionAttributes("cliente")//!Es necesario utilizar SessionAttributes, ya que necesitamos mantener la consistencia de datos en "cliente" para poder actualizarlo, de lo contrario insertaria uno nuevo
public class ClienteController {
    
    @Autowired
    @Qualifier("clienteService")
    private iClienteService clienteService;

    @GetMapping({"/", "/home", "/index"})
    public String home(Model model){
        model.addAttribute("titulo", "Ugarte Company");
        model.addAttribute("bienvenida", "Bienvenido a Ugarte Company");
        return "home";
    }

    //?Se mapea el metodo de esta forma porque estamos utilizando consultas
    @RequestMapping(value = "listar", method = RequestMethod.GET)
    public String listar(Model model){
        
        model.addAttribute("titulo", "Listado de clientes: ");
        model.addAttribute("clientes", clienteService.findAll());
        return "listar"; //!IMPORTANTE Aqui va el nombre de la vista que retornamos 
    }

    @GetMapping("/form")
    public String crear(Map<String, Object> model){
        Cliente cliente = new Cliente();
        model.put("titulo", "Agregar cliente");
        model.put("cliente", cliente);
        return "form";
    }

    @PostMapping("/form")
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status){//!Aqui se tienen que poner la etiqueta @Valid y el BindingResult para manejar los errores del formulario
        if(result.hasErrors()){
            model.addAttribute("cliente", cliente);
            model.addAttribute("titulo", "Agregar cliente");
            return "form";
        }

        String mensajeFlash = (cliente.getId() != null) ? "Cliente actualizado con exito" : "Cliente nuevo creado con exito";
        clienteService.save(cliente);
        status.setComplete();//!Se cierra la sesion una ves actualizado el usuario
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:listar";
    }

    @GetMapping("/form/{id}")
    public String actualizar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash){

        Cliente cliente = null;
        if(id>0){
            cliente = clienteService.buscar(id);
            if(cliente == null){
                flash.addFlashAttribute("error", "Cliente no encontrado");
                return "redirect:/listar";
            }
        }else{
            flash.addFlashAttribute("error", "Cliente no encontrado");
            return "redirect:/listar";
        }
        model.put("cliente", cliente);
        model.put("titulo", "Editar Cliente");

        return "form";

    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash){
        if(id > 0){
            clienteService.eliminar(id);
            flash.addFlashAttribute("success", "Cliente eliminado con exito");
        }
        return "redirect:/listar";
    }
}
