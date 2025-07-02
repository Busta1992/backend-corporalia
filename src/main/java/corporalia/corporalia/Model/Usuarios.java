package corporalia.corporalia.Model;

import corporalia.corporalia.DTO.UsuariosDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="usuarios") // 🔹 Aseguramos que el nombre de la tabla sea "usuarios"
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 🔹 Generación automática del ID
    @Column(name = "id") // 🔹 Mapeo a la columna "id" en la BD
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100) // 🔹 Configuración de la columna "nombre"
    private String nombre;

    @Column(name = "password", nullable = false, length = 255) // 🔹 Configuración para la columna "password"
    private String password;

    // Constructor que recibe un DTO
    public Usuarios(UsuariosDTO usuariosDTO) {
        this.id = usuariosDTO.getId();
        this.nombre = usuariosDTO.getNombre();
        this.password = usuariosDTO.getPassword(); // 🔹 Mapeamos la contraseña desde el DTO
    }
}