package com.FrancoBatista.huertasDelmunicipio.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.FrancoBatista.huertasDelmunicipio.Servicio.ProductorServicio;
import com.FrancoBatista.huertasDelmunicipio.entidades.Productor;
import com.FrancoBatista.huertasDelmunicipio.excepciones.MiException;

import java.util.List;

@Controller
@RequestMapping("/productor")
public class ProductorControlador {

    private final ProductorServicio productorServicio;

    @Autowired
    public ProductorControlador(ProductorServicio productorServicio) {
        this.productorServicio = productorServicio;
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "productorForm.html";
    }

    @PostMapping("/registrar")
    public String registro(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String DNI, @RequestParam String domicilio, ModelMap modelo) {
        try {
            productorServicio.crearProductor(nombre, apellido, DNI, domicilio);
            modelo.put("Exito", "El productor fue cargado correctamente!!");
        } catch (MiException ex) {
            modelo.put("Error", ex.getMessage());
            return "productorForm.html";
        }
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<Productor> productores = productorServicio.listarProductores();
        modelo.addAttribute("productores", productores);
        return "productorListar.html";
    }

    @GetMapping("/modificar/{idProductor}")
    public String mostrarFormularioModificar(@PathVariable("idProductor") String idProductor, ModelMap modelo) {
        Productor productor = productorServicio.getone(idProductor);
        modelo.addAttribute("productor", productor);
        return "productorModificar.html";
    }

    @PostMapping("/modificar/{idProductor}")
    public String modificar(@PathVariable String idProductor, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String DNI, @RequestParam String domicilio, ModelMap modelo) {
        try {
            productorServicio.modificarProductor(idProductor, nombre, apellido, DNI, domicilio);
            return "redirect:/productor/lista";
        } catch (MiException ex) {
            modelo.put("Error", ex.getMessage());
            return "productorModificar.html";
        }
    }
}
