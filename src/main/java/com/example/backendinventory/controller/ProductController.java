package com.example.backendinventory.controller;

import com.example.backendinventory.model.Product;
import com.example.backendinventory.response.GenericoResponse;
import com.example.backendinventory.service.impl.CategoryServiceImpl;
import com.example.backendinventory.service.impl.ProductServiceImpl;
import com.example.backendinventory.util.ConverterImageBytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CategoryServiceImpl categoryService;

    /**
     *
     * @return
     * @throws Exception
     */
    @GetMapping
    public ResponseEntity<GenericoResponse<Product>> getProducts() throws Exception {
        List<Product> lista = productService.getAll();
                lista.forEach((x) -> {
                    if (x.getPicture() !=null && x.getPicture().length > 0) {
                        x.setPicture(ConverterImageBytes.decompressZLib(x.getPicture()));
                    }
                });

        GenericoResponse<Product> genericoResponse =
                new GenericoResponse("GET", Integer.toString(HttpStatus.OK.value()), LocalDateTime.now().toString(), lista);
        return new ResponseEntity<>(genericoResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericoResponse<Product>> getProductById(@PathVariable(name = "id") Integer id) throws Exception {
        Product product = productService.getById(id);
        if(product != null && product.getPicture() != null){
            product.setPicture(ConverterImageBytes.decompressZLib(product.getPicture()));
        }
        GenericoResponse<Product> genericoResponse =
                new GenericoResponse<>("GET", Integer.toString(HttpStatus.OK.value()), LocalDateTime.now().toString(), List.of(product));
        return new ResponseEntity<>(genericoResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GenericoResponse<Product>> postProduct(@RequestParam("name") String name,
                                                                 @RequestParam("price") Double price,
                                                                 @RequestParam("quantity") Integer quantity,
                                                                 @RequestParam("category") Integer category,
                                                                 @RequestParam(value = "picture", required = false) MultipartFile picture
    ) throws Exception {

        // Convertir a Lower los campos en String
        /*
        ConverterString<Product> converterLower = new ConverterString<>();
        Product productLower =  converterLower.ConverterLower(product);

        // Verificar si no se repite un ´name´
        if(productService.getByName(product.getName()) != null) {
            throw new Exception("El nombre del producto ya existe");
        }
         */

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategory(categoryService.getById(category));

        if(picture != null && !picture.isEmpty()){
            byte[] pictuteBytes = picture.getBytes();
            product.setPicture(ConverterImageBytes.compressZLib(pictuteBytes));
        }



        Product auxProduct = productService.save(product);
        if(auxProduct == null){
            throw new Exception("No se pudo guardar objeto");
        }
        GenericoResponse<Product> genericoResponse =
                new GenericoResponse<>("POST", Integer.toString(HttpStatus.CREATED.value()), LocalDateTime.now().toString(), List.of(product));
        return new ResponseEntity<>(genericoResponse, HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public ResponseEntity<GenericoResponse<Product>> patchProduct(@RequestParam(value = "id") Integer id,
                                                                @RequestParam("name") String name,
                                                                @RequestParam("price") Double price,
                                                                @RequestParam("quantity") Integer quantity,
                                                                @RequestParam("category") Integer category,
                                                                @RequestParam(value = "picture", required = false) MultipartFile picture) throws Exception {

        /*
        // Convertir a Lower los campos en String
        ConverterString<Product> converterLower = new ConverterString<>();
        Product productLower =  converterLower.ConverterLower(product);

        // Verificar si no se repite un ´name´
        Product productAux = productService.getByName(product.getName());
        if(productAux != null && productAux.getId() != id) {
            throw new Exception("El nombre de la producto ya existe");
        }
         */


        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategory(categoryService.getById(category));

        Product productVerificar = productService.getById(id);

        if(picture != null && !picture.isEmpty()){
            System.out.println("NOOOOOO");
            byte[] pictuteBytes = picture.getBytes();
            product.setPicture(ConverterImageBytes.compressZLib(pictuteBytes));
        }
        if(picture == null){
            System.out.println("SIIIIII");
            product.setPicture(productVerificar.getPicture());
        }

        Product auxProduct = productService.update(product, id);
        if(auxProduct == null){
            throw new Exception("No se pudo actualizar objeto");
        }
        GenericoResponse<Product> genericoResponse =
                new GenericoResponse<>("POST", Integer.toString(HttpStatus.OK.value()), LocalDateTime.now().toString(), List.of(product));
        return new ResponseEntity<>(genericoResponse, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericoResponse<Product>> deleteProduct(@PathVariable(value = "id") Integer id) throws Exception {
        Product product1 = productService.getById(id);

        productService.delete(id);
        GenericoResponse<Product> genericoResponse = new GenericoResponse<>("DELETE", Integer.toString(HttpStatus.OK.value()), LocalDateTime.now().toString(), List.of(product1));
        return new ResponseEntity<>(genericoResponse, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @param description
     * @return
     * @throws Exception
     */
    @GetMapping("{id}/{description}")
    public ResponseEntity<GenericoResponse<Product>> getProductByIdOrName(@PathVariable(value = "id") Integer id,
                                                                                      @PathVariable(value = "description") String description) throws Exception {
        List<Product> categories = productService.getProductByIdOrName(id, description);
        categories.forEach((x) -> {
            if(x.getPicture() != null && x.getPicture().length > 0){
                x.setPicture(ConverterImageBytes.decompressZLib(x.getPicture()));
            }
        });
        GenericoResponse<Product> genericoResponse =
                new GenericoResponse<>("GET", Integer.toString(HttpStatus.OK.value()), LocalDateTime.now().toString(), categories);
        return new ResponseEntity<>(genericoResponse, HttpStatus.OK);
    }

}
