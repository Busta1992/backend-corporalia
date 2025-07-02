package corporalia.corporalia.Service;

import corporalia.corporalia.DTO.BBDDDTO;
import corporalia.corporalia.Model.BBDD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FabricaBBDDService {

    private static final Logger logger = LoggerFactory.getLogger(FabricaBBDDService.class);

    public BBDD escribirBBDD(BBDDDTO bbddDTO) {
        if (bbddDTO == null) {
            logger.error("El DTO proporcionado es nulo");
            throw new IllegalArgumentException("El DTO no puede ser nulo");
        }
        logger.info("Convirtiendo DTO a Entidad: " + bbddDTO);
        return new BBDD(bbddDTO);
    }

    public BBDDDTO escribirBBDDDTO(BBDD bbdd) {
        if (bbdd == null) {
            logger.error("La entidad proporcionada es nula");
            throw new IllegalArgumentException("La entidad no puede ser nula");
        }
        logger.info("Convirtiendo Entidad a DTO: " + bbdd);
        return new BBDDDTO(bbdd);
    }

    public List<BBDDDTO> escribirBBDDDTO(List<BBDD> bbddList) {
        if (bbddList == null || bbddList.isEmpty()) {
            logger.warn("La lista de entidades está vacía o es nula");
            return List.of(); // Devuelve una lista vacía para evitar errores
        }
        logger.info("Convirtiendo lista de entidades a lista de DTOs");
        return bbddList.stream()
                .map(this::escribirBBDDDTO)
                .collect(Collectors.toList());
    }
}