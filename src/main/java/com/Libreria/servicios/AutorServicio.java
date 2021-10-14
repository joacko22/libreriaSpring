package com.Libreria.servicios;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.Libreria.Repository.AutorRepository;
import com.Libreria.entidades.Autor;



@Service
public class AutorServicio {
	@Autowired
	public AutorRepository autorRepo;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Autor guardar(String nombre) throws Exception {
		if (nombre.contains(" ") || nombre.isEmpty() || nombre==null) {
			throw new Exception("el Autor requiere un nombre");
		}
		Autor autor = new Autor();
		autor.setNombre(nombre);
		autor.setAlta(true);
		return autorRepo.save(autor);
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Autor obtenerID(String id) throws Exception {
		Optional<Autor> respuesta = autorRepo.findById(id);// creamos un optional para validar si existe un autor por id
		if (respuesta.isPresent()) {

			Autor au = respuesta.get();// creamos un autor y lo traemos
			
			return au;// aca mandamos al repositorio nuestra entidad y resultado
		} else {
			throw new Exception("no existe un Autor con ese id o nombre");
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Autor modificar(String id, String nombre) throws Exception {
		if (nombre.isBlank() || nombre.isEmpty()) {
			throw new Exception("el Autor requiere un nombre");
		}
		Optional<Autor> respuesta = autorRepo.findById(id);// creamos un optional para validar si existe un autor por id
		if (respuesta.isPresent()) {

			Autor au = respuesta.get();// en caso de que exista un id con ese autor lo trae
			au.setNombre(nombre);// y le modifica el nombre

			return autorRepo.save(au);// aca mandamos al repositorio nuestra entidad y resultado
		} else {
			throw new Exception("no existe un Autor con ese id o nombre");
		}
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void eliminar(String id) {
		autorRepo.deleteById(id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Autor baja(String id) throws Exception {

		Optional<Autor> respuesta = autorRepo.findById(id);// creamos un optional para validar si existe un autor por id
		if (respuesta.isPresent()) {

			if (respuesta.isEmpty()) {
				throw new Exception("el Autor esta vacio");
			}
			Autor au = respuesta.get();// en caso de que exista un id con ese autor lo trae
			au.setAlta(false);// y le modifica el nombre

			return autorRepo.save(au);// aca mandamos al repositorio nuestra entidad y resultado
		} else {
			throw new Exception("no existe un Autor con ese id o nombre");
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Autor alta(String id) throws Exception {

		Optional<Autor> respuesta = autorRepo.findById(id);// creamos un optional para validar si existe un autor por id
		if (respuesta.isEmpty()) {
			throw new Exception("el Autor esta vacio");
		}
		if (respuesta.isPresent()) {

			
			Autor au = respuesta.get();// en caso de que exista un id con ese autor lo trae
			au.setAlta(true);// y le modifica el nombre

			 return autorRepo.save(au);// aca mandamos al repositorio nuestra entidad y resultado
		} else {
			throw new Exception("no existe un Autor con ese id o nombre");
		}
	}
	/** otra forma de hacer el alta y baja
	 * @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Autor alta(String id) {

		Autor entidad = perroRepository.getOne(id);

		entidad.setAlta(true);
		return autorRepo.save(entidad);
	}
	 */
	@Transactional (readOnly=true)
	public List<Autor> listarActivos(){
		return autorRepo.autoresActivos();
	}
	@Transactional(readOnly = true)
	public List<Autor> listarTodos() {
		return  autorRepo.findAll();
	}
}
