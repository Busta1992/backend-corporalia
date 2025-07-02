package corporalia.corporalia.Repository;


import corporalia.corporalia.Model.Carreteras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarreterasRepository extends JpaRepository<Carreteras, String> {
    List<Carreteras> findByCarretera(String carretera);
    List<Carreteras> findByProvincia(String provincia);

}


