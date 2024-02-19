package br.edu.ifrs.demo;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.model.Categoria;

@RestController
@CrossOrigin(origins = "*")// Resolve problemas de CORS
public class CategoriaController {

	
    @GetMapping("/Categorias")
	public List<Categoria> getAll() {
		return Categoria.getAll();
	}

    @GetMapping("/Categorias/{id}")
	public Categoria getOne(
                        @PathVariable("id") long id
        ) {
		Categoria d =  new Categoria();
        d.setId(id);
        if(d.load()){
            return d;
        }else{
            return null;
        }        
	}  

    @PostMapping(value = "/Categoria")
    public boolean save(Categoria d){
        System.out.println(d);
        return d.insert();
        
    }

    @PostMapping(value = "/Categoria/{id}")
    public boolean update( @PathVariable("id") long id, Categoria u){
        u.setId(id);
        return u.update();
    }

    @GetMapping(value = "/Categoria/remove/{id}")
    public boolean delete( @PathVariable("id") long id){
        Categoria d = new Categoria();
        d.setId(id);
        return d.delete();
    }

}
