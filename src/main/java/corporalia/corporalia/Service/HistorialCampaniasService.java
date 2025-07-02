package corporalia.corporalia.Service;
import corporalia.corporalia.DTO.HistorialCampaniasDTO;
import corporalia.corporalia.Model.HistorialCampanias;
import corporalia.corporalia.Repository.HistorialCampaniasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistorialCampaniasService {
    @Autowired
    private HistorialCampaniasRepository historialRepo;

    // 🔹 Obtener todo
    public List<HistorialCampaniasDTO> findAll() {
        return historialRepo.findAll()
                .stream()
                .map(HistorialCampaniasDTO::new)
                .collect(Collectors.toList());
    }

    // 🔹 Buscar por nFlota
    public List<HistorialCampaniasDTO> findBynFlota(String nFlota) {
        return historialRepo.findBynFlota(nFlota)
                .stream()
                .map(HistorialCampaniasDTO::new)
                .collect(Collectors.toList());
    }

    // 🔹 Guardar nuevo registro
    public HistorialCampaniasDTO save(HistorialCampaniasDTO dto) {
        System.out.println("--------- INTENTO DE GUARDADO (HISTORIAL) ---------");
        System.out.println("nFlota: " + dto.getNFlota());
        System.out.println("fechaInicio: " + dto.getFechaInicio());
        System.out.println("fechaFin: " + dto.getFechaFin());

        if (dto.getNFlota() == null || dto.getNFlota().trim().isEmpty()) {
            throw new RuntimeException("El campo 'nFlota' es obligatorio y no puede estar vacío.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        HistorialCampanias entity = new HistorialCampanias();

        entity.setNFlota(dto.getNFlota());
        entity.setMatricula(dto.getMatricula());
        entity.setCampania(dto.getCampania());
        entity.setCarretera(dto.getCarretera());
        entity.setOperador(dto.getOperador());
        entity.setObservaciones(dto.getObservaciones());

        if (dto.getFechaInicio() != null && !dto.getFechaInicio().isEmpty()) {
            entity.setFechaInicio(LocalDateTime.parse(dto.getFechaInicio(), formatter));
        } else {
            entity.setFechaInicio(null);
        }

        if (dto.getFechaFin() != null && !dto.getFechaFin().isEmpty()) {
            entity.setFechaFin(LocalDateTime.parse(dto.getFechaFin(), formatter));
        } else {
            entity.setFechaFin(null);
        }

        HistorialCampanias saved = historialRepo.save(entity);
        return new HistorialCampaniasDTO(saved);
    }

    // 🔹 Eliminar por id
    public void deleteById(Long id) {
        if (!historialRepo.existsById(id)) {
            throw new RuntimeException("No existe historial con id: " + id);
        }
        historialRepo.deleteById(id);
    }
}
