package corporalia.corporalia.Service;

import corporalia.corporalia.DTO.UsuariosDTO;
import corporalia.corporalia.Model.Usuarios;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class FabricaUsuariosService {
    public Usuarios crearUsuario(UsuariosDTO usuariosDTO) {
        Usuarios usuarios = new Usuarios();
        usuarios.setNombre(usuariosDTO.getNombre()); // 🔹 Solo asignamos `nombre`, el `id` se generará automáticamente
        return usuarios;
    }

    public UsuariosDTO crearUsuarioDTO(Usuarios usuarios) {
        return new UsuariosDTO(usuarios);
    }

}


