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

    @GetMapping
    public ResponseEntity<List<BBDDDTO>> findAll() {
        return ResponseEntity.ok(bbddService.findAll());
    }

    @GetMapping("/provincia/{provincia}")
    public ResponseEntity<List<BBDDDTO>> findByProvincia(@PathVariable String provincia) {
        return ResponseEntity.ok(bbddService.findByProvincia(provincia));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<BBDDDTO> findByCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(bbddService.findByCodigo(codigo));
    }

    // ✅ NUEVO: Guardado directo en lote
    @PostMapping("/bulk-fast")
    public ResponseEntity<List<BBDDDTO>> saveBulkFast(@RequestBody List<BBDDDTO> dtos) {
        return ResponseEntity.ok(bbddService.saveBulkDirecto(dtos));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<BBDDDTO>> saveBulk(@RequestBody List<BBDDDTO> dtos) {
        List<BBDDDTO> resultados = dtos.stream()
                .map(bbddService::save)
                .toList();

        return ResponseEntity.ok(resultados);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<BBDDDTO> update(@PathVariable String codigo, @RequestBody BBDDDTO bbddDTO) {
        return ResponseEntity.ok(bbddService.update(codigo, bbddDTO));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deleteByCodigo(@PathVariable String codigo) {
        bbddService.deleteByCodigo(codigo);
        return ResponseEntity.noContent().build();
    }
}