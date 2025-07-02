package corporalia.corporalia.Repository;

import corporalia.corporalia.Model.BBDD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BBDDRepository extends JpaRepository<BBDD, String> {
    List<BBDD> findByProvincia(String provincia);
}