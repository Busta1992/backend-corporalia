package corporalia.corporalia.controller;

import corporalia.corporalia.DTO.BBDDDTO;
import corporalia.corporalia.DTO.CarreterasDTO;

import corporalia.corporalia.Service.CarreterasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Corporalia/v1/carreteras")

public class CarreterasController {
    @Autowired
    private CarreterasService carreterasService;

    @GetMapping
    public ResponseEntity<List<CarreterasDTO>> findAll() {
        return ResponseEntity.ok(carreterasService.findAll());
    }

    @GetMapping("/carretera/{carretera}")
    public ResponseEntity<List<CarreterasDTO>> findByCarretera(@PathVariable String carretera) {
        return ResponseEntity.ok(carreterasService.findByCarretera(carretera));
    }

    @GetMapping("/provincia/{provincia}")
    public ResponseEntity<List<CarreterasDTO>> findByProvincia(@PathVariable String provincia) {
        return ResponseEntity.ok(carreterasService.findByProvincia(provincia));
    }

    @GetMapping("/{nFlota}")
    public ResponseEntity<CarreterasDTO> findByNFlota(@PathVariable String nFlota) {
        return ResponseEntity.ok(carreterasService.findByNFlota(nFlota));
    }

    @PostMapping
    public ResponseEntity<CarreterasDTO> save(@RequestBody CarreterasDTO carreterasDTO) {
        System.out.println("========= ENTRADA EN CONTROLLER =========");
        System.out.println("DTO recibido nFlota: " + carreterasDTO.getNFlota());
        System.out.println("DTO recibido carretera: " + carreterasDTO.getCarretera());
        return ResponseEntity.ok(carreterasService.save(carreterasDTO));
    }

    // ✅ NUEVO: Guardado en lote (bulk-fast)
    @PostMapping("/bulk-fast")
    public ResponseEntity<List<CarreterasDTO>> saveBulkFast(@RequestBody List<CarreterasDTO> dtos) {
        return ResponseEntity.ok(carreterasService.saveBulkDirecto(dtos));
    }

    @PutMapping("/{nFLota}")
    public ResponseEntity<CarreterasDTO> update(@PathVariable String nFLota, @RequestBody CarreterasDTO carreterasDTO) {
        return ResponseEntity.ok(carreterasService.update(nFLota, carreterasDTO));
    }

    @DeleteMapping("/{nFlota}")
    public ResponseEntity<Void> delete(@PathVariable String nFlota) {
        carreterasService.deleteByNFlota(String.valueOf(nFlota));
        return ResponseEntity.noContent().build();
    }
}
