// ProductorServicio.java

package com.FrancoBatista.huertasDelmunicipio.Servicio;

import com.FrancoBatista.huertasDelmunicipio.entidades.Productor;
import com.FrancoBatista.huertasDelmunicipio.excepciones.MiException;
import com.FrancoBatista.huertasDelmunicipio.repositorios.ProductorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductorServicio {

    private final ProductorRepositorio productorRepositorio;

    @Autowired
    public ProductorServicio(ProductorRepositorio productorRepositorio) {
        this.productorRepositorio = productorRepositorio;
    }

    @Transactional
    public void crearProductor(String nombre, String apellido, String DNI, String domicilio) throws MiException {
        validar(nombre, apellido, DNI, domicilio);

        Productor productor = new Productor();
        productor.setNombre(nombre);
        productor.setApellido(apellido);
        productor.setDNI(DNI);
        productor.setDomicilio(domicilio);
        productor.setAlta(new Date());

        productorRepositorio.save(productor);
    }
    
public void eliminarProductor(String idProductor) throws MiException {
    Optional<Productor> respuesta = productorRepositorio.findById(idProductor);

    if (respuesta.isPresent()) {
        Productor productor = respuesta.get();
        productorRepositorio.delete(productor);
    } else {
        throw new MiException("No se encontró el productor con el ID proporcionado");
    }
}


    public List<Productor> listarProductores() {
        return productorRepositorio.findAll();
    }

    @Transactional
    public void modificarProductor(String idProductor, String nombre, String apellido, String DNI, String domicilio) throws MiException {
        validar(nombre, apellido, DNI, domicilio);

        Optional<Productor> respuesta = productorRepositorio.findById(idProductor);

        if (respuesta.isPresent()) {
            Productor productor = respuesta.get();
            productor.setNombre(nombre);
            productor.setApellido(apellido);
            productor.setDNI(DNI);
            productor.setDomicilio(domicilio);
            productorRepositorio.save(productor);
        }
    }

    private void validar(String nombre, String apellido, String DNI, String domicilio) throws MiException {
        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("El nombre del productor no puede ser nulo o estar vacío");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new MiException("El apellido del productor no puede ser nulo o estar vacío");
        }
        if (DNI == null || DNI.isEmpty()) {
            throw new MiException("El DNI del productor no puede ser nulo o estar vacío");
        }
        if (domicilio == null || domicilio.isEmpty()) {
            throw new MiException("El domicilio del productor no puede ser nulo o estar vacío");
        }
    }
    
    public Productor getone(String id){
        
        return productorRepositorio.getOne(id);
        
        
    }
}
