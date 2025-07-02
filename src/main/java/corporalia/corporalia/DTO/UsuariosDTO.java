package corporalia.corporalia.DTO;

import corporalia.corporalia.Model.Usuarios;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosDTO {

    private Integer id;
    private String nombre;
    private String password; // 🔹 Añadimos la contraseña al DTO

    // Constructor que recibe un modelo `Usuarios`
    public UsuariosDTO(Usuarios usuarios) {
        this.id = usuarios.getId();
        this.nombre = usuarios.getNombre();
        this.password = usuarios.getPassword(); // 🔹 Añadimos contraseña desde el modelo
    }
}