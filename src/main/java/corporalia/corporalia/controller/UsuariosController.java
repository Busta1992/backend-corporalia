package corporalia.corporalia.controller;

import corporalia.corporalia.DTO.UsuariosDTO;
import corporalia.corporalia.Service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Corporalia/v2/Usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    // Endpoint para guardar un usuario
    @PostMapping
    public ResponseEntity<UsuariosDTO> save(@RequestBody UsuariosDTO usuariosDTO) {
        try {
            return new ResponseEntity<>(usuariosService.save(usuariosDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<UsuariosDTO>> findAll() {
        return new ResponseEntity<>(usuariosService.findAll(), HttpStatus.OK);
    }

    // Endpoint para eliminar un usuario por ID
    @DeleteMapping("/{id_usuario}")
    public ResponseEntity<Void> deleteByID(@PathVariable Integer id_usuario) {
        try {
            usuariosService.deleteByID(id_usuario);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para actualizar la información de un usuario
    @PutMapping
    public ResponseEntity<UsuariosDTO> update(@RequestBody UsuariosDTO usuariosDTO) {
        try {
            UsuariosDTO updateUsuariosDTO = usuariosService.update(usuariosDTO);
            return new ResponseEntity<>(updateUsuariosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para manejar el login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuariosDTO usuariosDTO) {
        try {
            boolean success = usuariosService.login(usuariosDTO.getNombre(), usuariosDTO.getPassword());
            if (success) {
                return new ResponseEntity<>("Login exitoso", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Credenciales incorrectas", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error en el servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
