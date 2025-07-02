package corporalia.corporalia.controller;

import corporalia.corporalia.DTO.HistorialCampaniasDTO;
import corporalia.corporalia.Service.HistorialCampaniasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Corporalia/v1/historial")


public class HistorialCampaniasController {
    @Autowired
    private HistorialCampaniasService historialService;

    // 📌 Obtener todo el historial
    @GetMapping
    public ResponseEntity<List<HistorialCampaniasDTO>> getAllHistorial() {
        return ResponseEntity.ok(historialService.findAll());
    }

    // 📌 Obtener historial por nFlota
    @GetMapping("/{nFlota}")
    public ResponseEntity<List<HistorialCampaniasDTO>> getHistorialPorNFlota(@PathVariable String nFlota) {
        return ResponseEntity.ok(historialService.findBynFlota(nFlota));
    }

    // 📌 Añadir nuevo registro de historial
    @PostMapping
    public ResponseEntity<HistorialCampaniasDTO> createHistorial(@RequestBody HistorialCampaniasDTO dto) {
        return ResponseEntity.ok(historialService.save(dto));
    }

    // 📌 Eliminar historial por nFlota
    @DeleteMapping("/{id}")  // ✅ Corregido
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        historialService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
