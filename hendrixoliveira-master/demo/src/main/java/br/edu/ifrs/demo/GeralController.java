package br.edu.ifrs.demo;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.model.Usuario;

@RestController
@CrossOrigin(origins = "*")// Resolve problemas de CORS
public class GeralController {

  
    @GetMapping("/usuarios")
	public List<Usuario> getAll() {
		return Usuario.getAll();
	}

    @GetMapping("/usuario/{id}")
	public Usuario getOne(
                        @PathVariable("id") long id
        ) {
		Usuario u =  new Usuario();
        u.setId(id);
        if(u.load()){
            return u;
        }else{
            return null;
        }        
	}  

    @PostMapping(value = "/usuario")
    public boolean save(Usuario u){
        System.out.println(u);
        return u.insert();
    }

    @PostMapping(value = "/usuario/{id}")
    public boolean update( @PathVariable("id") long id, Usuario u){
        u.setId(id);
        return u.update();
    }

    @GetMapping(value = "/usuario/remove/{id}")
    public boolean delete( @PathVariable("id") long id){
        Usuario u = new Usuario();
        u.setId(id);
        return u.delete();
    }

    @PostMapping("/Validar")
	public long getValidar( Usuario u
                        
        ) {
    
            System.out.println(u);
        if(u.verificaSenha()){
            return u.getId();
        }else{
            return 0;
        }        
	}

}
