package corporalia.corporalia.controller;

import corporalia.corporalia.DTO.BBDDDTO;
import corporalia.corporalia.Service.BBDDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Corporalia/v1/BBDD")
public class BBDDController {

    @Autowired
    private BBDDService bbddService;

    // Obtiene todos los registros
    @GetMapping
    public ResponseEntity<List<BBDDDTO>> findAll() {
        return ResponseEntity.ok(bbddService.findAll());
    }

    // Obtiene registros filtrados por provincia
    @GetMapping("/provincia/{provincia}")
    public ResponseEntity<List<BBDDDTO>> findByProvincia(@PathVariable String provincia) {
        return ResponseEntity.ok(bbddService.findByProvincia(provincia));
    }

    // Obtiene un registro único por su código
    @GetMapping("/{codigo}")
    public ResponseEntity<BBDDDTO> findByCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(bbddService.findByCodigo(codigo));
    }

    // Crea un nuevo registro
    @PostMapping
    public ResponseEntity<BBDDDTO> save(@RequestBody BBDDDTO bbddDTO) {
        return ResponseEntity.ok(bbddService.save(bbddDTO));
    }

    // Actualiza un registro existente por su código
    @PutMapping("/{codigo}")
    public ResponseEntity<BBDDDTO> update(@PathVariable String codigo, @RequestBody BBDDDTO bbddDTO) {
        return ResponseEntity.ok(bbddService.update(codigo, bbddDTO));
    }

    // Elimina un registro por su código
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deleteByCodigo(@PathVariable String codigo) {
        bbddService.deleteByCodigo(codigo); // Llama al servicio para realizar la eliminación
        return ResponseEntity.noContent().build(); // Devuelve un código 204 (sin contenido)
    }
}