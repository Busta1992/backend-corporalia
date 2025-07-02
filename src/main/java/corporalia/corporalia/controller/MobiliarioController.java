package corporalia.corporalia.controller;

import corporalia.corporalia.DTO.CarreterasDTO;
import corporalia.corporalia.DTO.MobiliarioDTO;


import corporalia.corporalia.Service.MobiliarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Corporalia/v1/mobiliario")

public class MobiliarioController {
    @Autowired
    private MobiliarioService mobiliarioService;

    // Obtiene todos los registros
    @GetMapping
    public ResponseEntity<List<MobiliarioDTO>> findAll() {
        return ResponseEntity.ok(mobiliarioService.findAll());
    }

    @GetMapping("/municipio/{municipio}")
    public ResponseEntity<List<MobiliarioDTO>> obtenerPorMunicipio(@PathVariable String municipio) {
        return ResponseEntity.ok(mobiliarioService.obtenerPorMunicipio(municipio));
    }




    @GetMapping("/provincia/{provincia}")
    public ResponseEntity<List<MobiliarioDTO>> findByProvincia(@PathVariable String provincia) {
        return ResponseEntity.ok(mobiliarioService.findByProvincia(provincia));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<MobiliarioDTO> findByCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(mobiliarioService.findByCodigo(codigo));
    }

    // Crea un nuevo registro
    @PostMapping
    public ResponseEntity<MobiliarioDTO> save(@RequestBody MobiliarioDTO mobiliarioDTO) {
        System.out.println("========= ENTRADA EN CONTROLLER =========");
        System.out.println("DTO recibido codigo: " + mobiliarioDTO.getCodigo());
        System.out.println("DTO recibido provincia: " + mobiliarioDTO.getProvincia());
        return ResponseEntity.ok(mobiliarioService.save(mobiliarioDTO));
    }


    // Actualiza un registro existente por su código
    @PutMapping("/{codigo}")
    public ResponseEntity<MobiliarioDTO> update(@PathVariable String codigo, @RequestBody MobiliarioDTO mobiliarioDTO) {
        return ResponseEntity.ok(mobiliarioService.update(codigo, mobiliarioDTO));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable String codigo) {
        mobiliarioService.deleteByCodigo(String.valueOf(codigo));
        return ResponseEntity.noContent().build();
    }

}
