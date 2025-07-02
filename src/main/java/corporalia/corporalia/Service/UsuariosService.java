package corporalia.corporalia.Service;

import corporalia.corporalia.DTO.UsuariosDTO;
import corporalia.corporalia.Model.Usuarios;
import corporalia.corporalia.Repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;

    @Autowired
    public UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Transactional
    public UsuariosDTO save(UsuariosDTO usuariosDTO) {
        try {
            System.out.println("Guardando usuario: " + usuariosDTO.getNombre());
            Usuarios usuario = new Usuarios();
            usuario.setNombre(usuariosDTO.getNombre());
            usuario.setPassword(usuariosDTO.getPassword()); // Sin encriptación, se guarda directamente
            Usuarios savedUsuario = usuariosRepository.save(usuario);
            System.out.println("Guardado OK: ID = " + savedUsuario.getId());
            return new UsuariosDTO(savedUsuario);
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // Repropaga la excepción
        }
    }

    public List<UsuariosDTO> findAll() {
        return usuariosRepository.findAll()
                .stream()
                .map(UsuariosDTO::new)
                .collect(Collectors.toList());
    }

    public UsuariosDTO findByID(Integer id_usuario) {
        Optional<Usuarios> optionalUsuario = usuariosRepository.findById(id_usuario);
        if (optionalUsuario.isPresent()) {
            return new UsuariosDTO(optionalUsuario.get());
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id_usuario);
        }
    }

    @Transactional
    public void deleteByID(Integer id_usuario) {
        Optional<Usuarios> optionalUsuario = usuariosRepository.findById(id_usuario);
        if (optionalUsuario.isPresent()) {
            usuariosRepository.delete(optionalUsuario.get());
        } else {
            throw new RuntimeException("Usuario no encontrado para eliminar con ID: " + id_usuario);
        }
    }

    @Transactional
    public UsuariosDTO update(UsuariosDTO usuariosDTO) {
        Optional<Usuarios> optionalUsuario = usuariosRepository.findById(usuariosDTO.getId());
        if (optionalUsuario.isPresent()) {
            Usuarios usuario = optionalUsuario.get();
            usuario.setNombre(usuariosDTO.getNombre());
            usuario.setPassword(usuariosDTO.getPassword()); // Actualiza contraseña directamente
            Usuarios updatedUsuario = usuariosRepository.save(usuario);
            return new UsuariosDTO(updatedUsuario);
        } else {
            throw new RuntimeException("Usuario no encontrado para actualizar con ID: " + usuariosDTO.getId());
        }
    }

    public boolean login(String nombre, String password) {
        Usuarios usuario = usuariosRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con nombre: " + nombre));
        // Comparación directa de contraseñas
        return usuario.getPassword().equals(password);
    }
}