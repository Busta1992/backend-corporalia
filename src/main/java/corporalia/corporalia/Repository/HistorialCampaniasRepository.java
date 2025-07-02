package corporalia.corporalia.Repository;
import corporalia.corporalia.Model.HistorialCampanias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialCampaniasRepository extends JpaRepository<HistorialCampanias, Long> {
    List<HistorialCampanias> findBynFlota(String nFlota);
}
