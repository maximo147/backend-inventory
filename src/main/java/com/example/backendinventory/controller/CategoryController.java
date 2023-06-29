package com.example.backendinventory.controller;

import com.example.backendinventory.model.Category;
import com.example.backendinventory.response.GenericoResponse;
import com.example.backendinventory.service.impl.CategoryServiceImpl;
import com.example.backendinventory.util.CategoryExcelExport;
import com.example.backendinventory.util.ConverterString;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;


    @GetMapping
    public ResponseEntity<GenericoResponse<Category>> getCategories() throws Exception {
        List<Category> lista = categoryService.getAll();
        GenericoResponse<Category> genericoResponse =
                new GenericoResponse("GET", Integer.toString(HttpStatus.OK.value()), LocalDateTime.now().toString(), lista);
        return new ResponseEntity<>(genericoResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericoResponse<Category>> getCategoryById(@PathVariable(name = "id") Integer id) throws Exception {
        Category category = categoryService.getById(id);
        GenericoResponse<Category> genericoResponse =
                new GenericoResponse<>("GET", Integer.toString(HttpStatus.OK.value()), LocalDateTime.now().toString(), List.of(category));
        return new ResponseEntity<>(genericoResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GenericoResponse<Category>> postCategory(@RequestBody Category category) throws Exception {
        if(category == null){
            throw new Exception("No se encontró objeto");
        }
        // Convertir a Lower los campos en String
        ConverterString<Category> converterLower = new ConverterString<>();
        Category categoryLower =  converterLower.ConverterLower(category);

        // Verificar si no se repite un ´name´
        if(categoryService.getByName(category.getName()) != null) {
            throw new Exception("El nombre de la categoría ya existe");
        }

        Category auxCategory = categoryService.save(categoryLower);
        if(auxCategory == null){
            throw new Exception("No se pudo guardar objeto");
        }
        GenericoResponse<Category> genericoResponse =
                new GenericoResponse<>("POST", Integer.toString(HttpStatus.CREATED.value()), LocalDateTime.now().toString(), List.of(category));
        return new ResponseEntity<>(genericoResponse, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<GenericoResponse<Category>> putCategory(@RequestBody Category category, @PathVariable(value = "id") Integer id) throws Exception {
        if(category == null){
            throw new Exception("No se encontró objeto");
        }
        // Convertir a Lower los campos en String
        ConverterString<Category> converterLower = new ConverterString<>();
        Category categoryLower =  converterLower.ConverterLower(category);

        // Verificar si no se repite un ´name´
        Category categoryAux = categoryService.getByName(category.getName());
        if(categoryAux != null && categoryAux.getId() != id) {
            throw new Exception("El nombre de la categoría ya existe");
        }

        Category auxCategory = categoryService.update(categoryLower, id);
        if(auxCategory == null){
            throw new Exception("No se pudo actualizar objeto");
        }
        GenericoResponse<Category> genericoResponse =
                new GenericoResponse<>("POST", Integer.toString(HttpStatus.OK.value()), LocalDateTime.now().toString(), List.of(category));
        return new ResponseEntity<>(genericoResponse, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericoResponse<Category>> deleteCategory(@PathVariable(value = "id") Integer id) throws Exception {
        Category category1 = categoryService.getById(id);

        categoryService.delete(id);
        GenericoResponse<Category> genericoResponse = new GenericoResponse<>("DELETE", Integer.toString(HttpStatus.OK.value()), LocalDateTime.now().toString(), List.of(category1));
        return new ResponseEntity<>(genericoResponse, HttpStatus.OK);
    }

    /**
     *
     */
    @GetMapping("{id}/{description}")
    public ResponseEntity<GenericoResponse<Category>> getCategorieByIdOrName(@PathVariable(value = "id") Integer id,
                                                                                      @PathVariable(value = "description") String description) throws Exception {
        List<Category> categories = categoryService.getCategorieByIdOrName(id, description);
        GenericoResponse<Category> genericoResponse =
                new GenericoResponse<>("GET", Integer.toString(HttpStatus.OK.value()), LocalDateTime.now().toString(), categories);
        return new ResponseEntity<>(genericoResponse, HttpStatus.OK);
    }


    /**
     * Export to excel
     * @param response
     * @throws Exception
     */
    @GetMapping("export/excel")
    public void exportToExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=result_category.xlsx";
        response.setHeader(headerKey, headerValue);
        //ResponseEntity<GenericoResponse> genericoResponseResponseEntity = categoryService.getAll();
        CategoryExcelExport excelExport = new CategoryExcelExport(
                categoryService.getAll()
        );

        excelExport.export(response);
    }

}
