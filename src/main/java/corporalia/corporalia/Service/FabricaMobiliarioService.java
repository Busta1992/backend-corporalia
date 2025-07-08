package corporalia.corporalia.Service;

import corporalia.corporalia.DTO.CarreterasDTO;
import corporalia.corporalia.DTO.MobiliarioDTO;
import corporalia.corporalia.Model.Carreteras;
import corporalia.corporalia.Model.Mobiliario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FabricaMobiliarioService {
    private static final Logger logger = LoggerFactory.getLogger(FabricaMobiliarioService.class);

    public Mobiliario escribirMobiliario(MobiliarioDTO dto) {
        if (dto == null) {
            logger.error("El DTO proporcionado es nulo");
            throw new IllegalArgumentException("El DTO no puede ser nulo");
        }
        logger.info("Convirtiendo DTO a Entidad: " + dto);
        return new Mobiliario(dto);
    }



    public MobiliarioDTO escribirMobiliarioDTO(Mobiliario entity) {
        if (entity == null) {
            logger.error("La entidad proporcionada es nula");
            throw new IllegalArgumentException("La entidad no puede ser nula");
        }
        logger.info("Convirtiendo Entidad a DTO: " + entity);
        return new MobiliarioDTO(entity);
    }

    public List<MobiliarioDTO> escribirMobiliarioDTO(List<Mobiliario> entityList) {
        if (entityList == null || entityList.isEmpty()) {
            logger.warn("La lista de entidades está vacía o es nula");
            return List.of(); // Devuelve una lista vacía
        }
        logger.info("Convirtiendo lista de entidades a lista de DTOs");
        return entityList.stream()
                .map(this::escribirMobiliarioDTO)
                .collect(Collectors.toList());
    }
}

