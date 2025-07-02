package corporalia.corporalia.Service;

import corporalia.corporalia.DTO.CarreterasDTO;
import corporalia.corporalia.Model.Carreteras;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FabricaCarreterasService {

    private static final Logger logger = LoggerFactory.getLogger(FabricaCarreterasService.class);

    public Carreteras escribirCarreteras(CarreterasDTO dto) {
        if (dto == null) {
            logger.error("El DTO proporcionado es nulo");
            throw new IllegalArgumentException("El DTO no puede ser nulo");
        }
        logger.info("Convirtiendo DTO a Entidad: " + dto);
        return new Carreteras(dto);
    }

    public CarreterasDTO escribirCarreterasDTO(Carreteras entity) {
        if (entity == null) {
            logger.error("La entidad proporcionada es nula");
            throw new IllegalArgumentException("La entidad no puede ser nula");
        }
        logger.info("Convirtiendo Entidad a DTO: " + entity);
        return new CarreterasDTO(entity);
    }

    public List<CarreterasDTO> escribirCarreterasDTO(List<Carreteras> entityList) {
        if (entityList == null || entityList.isEmpty()) {
            logger.warn("La lista de entidades está vacía o es nula");
            return List.of(); // Devuelve una lista vacía
        }
        logger.info("Convirtiendo lista de entidades a lista de DTOs");
        return entityList.stream()
                .map(this::escribirCarreterasDTO)
                .collect(Collectors.toList());
    }
}
