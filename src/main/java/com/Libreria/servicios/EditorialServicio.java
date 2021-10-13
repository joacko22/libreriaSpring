package com.Libreria.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.Libreria.Repository.EditorialRepository;

import com.Libreria.entidades.Editorial;

import antlr.collections.List;
@Service
public class EditorialServicio {
	@Autowired
	public EditorialRepository EditRepo;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Editorial guardar(String nombre) throws Exception {
		if (nombre.isBlank() || nombre.isEmpty()) {
			throw new Exception("el Autor requiere un nombre");
		}
		Editorial edit = new Editorial();
		edit.setNombre(nombre);
		edit.setAlta(true);
		return EditRepo.save(edit);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Editorial modificar(String id, String nombre) throws Exception {
		if (nombre.isBlank() || nombre.isEmpty()) {
			throw new Exception("la Editorial requiere un nombre");
		}
		Optional<Editorial> respuesta = EditRepo.findById(id);// creamos un optional para validar si existe un autor por id
		if (respuesta.isPresent()) {

			Editorial edit = respuesta.get();// en caso de que exista un id con ese autor lo trae
			edit.setNombre(nombre);// y le modifica el nombre

			return EditRepo.save(edit);// aca mandamos al repositorio nuestra entidad y resultado
		} else {
			throw new Exception("no existe un Autor con ese id o nombre");
		}
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void eliminar(String id) {
		EditRepo.deleteById(id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Editorial baja(String id) throws Exception {

		Optional<Editorial> respuesta = EditRepo.findById(id);// creamos un optional para validar si existe un autor por id
		if (respuesta.isPresent()) {

			if (respuesta.isEmpty()) {
				throw new Exception("el Autor esta vacio");
			}
			Editorial edit = respuesta.get();// en caso de que exista un id con ese autor lo trae
			edit.setAlta(false);// y le modifica el nombre

			return EditRepo.save(edit);// aca mandamos al repositorio nuestra entidad y resultado
		} else {
			throw new Exception("no existe un Autor con ese id o nombre");
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Editorial alta(String id) throws Exception {

		Optional<Editorial> respuesta = EditRepo.findById(id);// creamos un optional para validar si existe un autor por id
		if (respuesta.isEmpty()) {
			throw new Exception("el Autor esta vacio");
		}
		if (respuesta.isPresent()) {

			
			Editorial edit = respuesta.get();// en caso de que exista un id con ese autor lo trae
			edit.setAlta(true);// y le modifica el nombre

			 return EditRepo.save(edit);// aca mandamos al repositorio nuestra entidad y resultado
		} else {
			throw new Exception("no existe un Autor con ese id o nombre");
		}
	}

	@Transactional (readOnly=true)
	public List listarActivos(){
		return EditRepo.listarActivos();
	}
	@Transactional(readOnly = true)
	public List listarTodos() {
		return (List) EditRepo.findAll();
	}
}


