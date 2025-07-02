package corporalia.corporalia.Repository;

import corporalia.corporalia.Model.Mobiliario;
import corporalia.corporalia.Model.Mobiliario;
import corporalia.corporalia.Model.Mobiliario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MobiliarioRepository extends JpaRepository<Mobiliario, String> {
    List<Mobiliario> findByProvincia(String provincia);
    List<Mobiliario> findByMunicipioIgnoreCase(String municipio);

}
