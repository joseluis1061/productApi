package com.api.arpires.apirest.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.api.arpires.apirest.Repositories.ProductoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.api.arpires.apirest.Entities.Producto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/productos")
public class ProductController {
  @Autowired
  private ProductoRepository productoRepository;

  @GetMapping
  // Retorna todos los productos
  public List<Producto> obtenerProductos(){
    return productoRepository.findAll();
  }
  
  @GetMapping("/{id}")
  public Producto obtenerProductoPorId(@PathVariable Long id){
    return productoRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("No se encontro el producto con id: "+ id));

  }

  @PostMapping
  //Recibe un pruducto de tipo Producto y lo guarda en el respositorie
  public Producto crearProducto(@RequestBody Producto producto){
    return productoRepository.save(producto);
  }


  @PutMapping("/{id}")
  public Producto updateProducto(@PathVariable Long id, @RequestBody Producto detalleProducto){
    //Selecciono producto
    Producto productoActualizar = productoRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("No se encontro el producto con id: "+ id));
    // Modifico los datos
    productoActualizar.setNombre(detalleProducto.getNombre());
    productoActualizar.setPrecion(detalleProducto.getPrecion());
    // Actualizo
    return productoRepository.save(productoActualizar);
  }
  
  @DeleteMapping("/{id}")
  public String eliminarProducto(@PathVariable Long id){
    Producto productoAEliminar = productoRepository.findById(id)
    .orElseThrow(()-> new RuntimeException("No se encuentra el producto con Id: "+ id));
    productoRepository.delete(productoAEliminar);
    return "Se elimino el producto con ID: "+ id + " fue eliminado correctamente";
  }


}
