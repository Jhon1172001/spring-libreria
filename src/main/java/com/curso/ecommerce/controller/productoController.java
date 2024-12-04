package com.curso.ecommerce.controller;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.IUsuarioService;
import com.curso.ecommerce.service.ProductoService;
import com.curso.ecommerce.service.UploadFileService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	
	@Autowired
	private UploadFileService upload;
	
	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("productos",productoService.findAll());
		return "productos/show";
	}
	
	@GetMapping("/create")
	public String create() {
		return "productos/create";
	}
	
	@PostMapping("/save")
	public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
		
		Usuario u= usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString() )).get();
		producto.setUsuario(u);
		
		//imagen
		if (producto.getId()==null) { //cuando se crea un producto
			String nombreImagen= upload.saveImage(file);
			producto.setImagen(nombreImagen);
		}else {
			
		}
		
		productoService.save(producto);
		return "redirect:/productos";
		
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Producto producto= new Producto();
		Optional<Producto>optionalProducto=productoService.get(id);
		producto= optionalProducto.get();
		
		LOGGER.info("Producto buscado: {}",producto);
		model.addAttribute("producto", producto);
		
		
		return "productos/edit";
	}
	
	@PostMapping("/update")
	public String update(Producto producto,@RequestParam("img") MultipartFile file) throws IOException {
		Producto p = new Producto();
		p=productoService.get(producto.getId()).get();
		
		
		if (file.isEmpty()) { // editamos el producto pero no cambiamos la imagen

			
			producto.setImagen(p.getImagen());
		}else {// cuando se edita tambien la imagen
			
			//eliminar cuando no sea la imagen por defecto
			if (!p.getImagen().equals("default.jpg")) {
				upload.deleteImage(p.getImagen());
			}
			String nombreImagen= upload.saveImage(file);
			producto.setImagen(nombreImagen);
		}
		
		producto.setUsuario(p.getUsuario());
		productoService.update(producto);
		return "redirect:/productos";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		
		Producto p = new Producto();
		p=productoService.get(id).get();
		
		//eliminar cuando no sea la imagen por defecto
		if (!p.getImagen().equals("default.jpg")) {
			upload.deleteImage(p.getImagen());
		}
		
		productoService.delete(id);
		return "redirect:/productos";
	}
public String deletde(@PathVariable Integer id) {
		
		Producto p = new Producto();
		p=productoService.get(id).get();
		
		//eliminar cuando no sea la imagen por defecto
		if (!p.getImagen().equals("default.jpg")) {
			upload.deleteImage(p.getImagen());
		}
		
		productoService.delete(id);
		return "redirect:/productos";
	}
public String deleste(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String deletdfe(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String delsete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String deslete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String delssete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String deldete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String delfete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String dsselete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String delffete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String deleteg(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String sdelete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String deleteff(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String deletegg(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String desdlete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String deletegf(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String delddsete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String delefste(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String defslete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String delefdte(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String deletgfe(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String delejhte(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String dedslete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String delejtte(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String delefte(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String deleffte(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String deledsdte(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String delegdfdte(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String delefdfte(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String dfdelete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String deldfete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String degdfflete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String desdsdslete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String delefddfdte(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String deldsdsdete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String fdfdelete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String deletegdgd(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String deledfdfte(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String deledfdfdte(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String deddvdlete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String delbdbdete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String dedvdvdvlete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String deledvdvte(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String delvdvdete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String deldvdete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String dedvvlete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String decdcdlete(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String delecdcdte(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}
public String delecdcte(@PathVariable Integer id) {
	
	Producto p = new Producto();
	p=productoService.get(id).get();
	
	//eliminar cuando no sea la imagen por defecto
	if (!p.getImagen().equals("default.jpg")) {
		upload.deleteImage(p.getImagen());
	}
	
	productoService.delete(id);
	return "redirect:/productos";
}

}
