package br.edu.ifrs.demo;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.model.ItemCompra;

@RestController
@CrossOrigin(origins = "*")// Resolve problemas de CORS
public class ItemCompraController {

  
    @GetMapping("/ItemCompra")
	public List<ItemCompra> getAll() {
		return ItemCompra.getAll();
	}

    @GetMapping("/ItemCompra/{id}")
	public ItemCompra getOne(
                        @PathVariable("id") long id
        ) {
		ItemCompra f =  new ItemCompra();
        f.setId(id);
        if(f.load()){
            return f;
        }else{
            return null;
        }        
	}  

    @PostMapping(value = "/ItemCompra")
    public boolean save(ItemCompra f){
        System.out.println(f);
        return f.insert();
    }

    @PostMapping(value = "/ItemCompra/{id}")
    public boolean update( @PathVariable("id") long id, ItemCompra u){
        u.setId(id);
        return u.update();
    }

    @GetMapping(value = "/ItemCompra/remove/{id}")
    public boolean delete( @PathVariable("id") long id){
        ItemCompra f = new ItemCompra();
        f.setId(id);
        return f.delete();
    }

}
