package com.prueba.app.gml.controllers;

import com.prueba.app.gml.models.dao.IClienteDao;
import com.prueba.app.gml.models.entity.Cliente;
import com.prueba.app.gml.models.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


import javax.validation.Valid;
import java.util.Map;

@Controller // anotacion para controlador en spring
@SessionAttributes("cliente")
public class ClienteController {


    @Autowired
    private IClienteService clienteService; // importar la interface

    /**
     * Metodo Listar
     * @param model
     * @return
     */
    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model){
        model.addAttribute("titulo", "listado de clientes");
        model.addAttribute("clientes", clienteService.findAll());
        return "listar";
    }


    /**
     * Metodo Crear
     * @param model
     * @return
     */
    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model){

        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("titulo", "Formulario de Cliente");
        return "form";

    }


    /**
     * Metodo Guardar
     * @param cliente
     * @return
     */
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status){
        // parte para activar el aviso de errores
        if(result.hasErrors()){
            model.addAttribute("titulo", "Formulario de Cliente");
            return "form";
        }
        clienteService.save(cliente);
        status.setComplete();
        return "redirect:listar";
    }


    /**
     * Metodo Editar
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model){
        Cliente cliente = null;

        // Condicion para validar el id
        if (id>0){
            cliente = clienteService.findOne(id); // si es mayor que 0, lo busca
        } else {
            return  "redirect:/listar"; // si no redirecciona
        }
        model.put("cliente", cliente);
        model.put("titulo", "Editar Cliente");
        return "form";
    }


    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id){ // obtenemos el id de la ruta
        // validamos que el id sea mayor que cero
        if(id > 0){
            clienteService.delete(id);
        }
        return "redirect:/listar";
    }
}
