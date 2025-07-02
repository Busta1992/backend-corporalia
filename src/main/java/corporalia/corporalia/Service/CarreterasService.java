package corporalia.corporalia.Service;

import corporalia.corporalia.DTO.BBDDDTO;
import corporalia.corporalia.DTO.CarreterasDTO;
import corporalia.corporalia.Model.Carreteras;
import corporalia.corporalia.Repository.CarreterasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarreterasService {

    @Autowired
    private CarreterasRepository carreterasRepository;

    public List<CarreterasDTO> findAll() {
        return carreterasRepository.findAll()
                .stream()
                .map(CarreterasDTO::new)
                .collect(Collectors.toList());
    }

    public CarreterasDTO findByNFlota(String nFlota) {
        Carreteras carreteras = carreterasRepository.findById(nFlota)
                .orElseThrow(() -> new RuntimeException("No se encontró el registro con el número de flota: " + nFlota));
        return new CarreterasDTO(carreteras);
    }

    public CarreterasDTO save(CarreterasDTO dto) {
        System.out.println("--------- INTENTO DE GUARDADO ---------");
        System.out.println("Recibido DTO:");
        System.out.println("nFlota: " + dto.getNFlota());
        System.out.println("fechaInicio: " + dto.getFechaInicio());
        System.out.println("fechaFin: " + dto.getFechaFin());

        if (dto.getNFlota() == null || dto.getNFlota().trim().isEmpty()) {
            throw new RuntimeException("El campo 'nFlota' es obligatorio y no puede estar vacío.");
        }

        if (carreterasRepository.existsById(dto.getNFlota())) {
            return update(dto.getNFlota(), dto);
        } else {
            Carreteras entity = new Carreteras();

            entity.setNFlota(dto.getNFlota());
            entity.setCarretera(dto.getCarretera());
            entity.setOperador(dto.getOperador());
            entity.setLineas(dto.getLineas());
            entity.setMatricula(dto.getMatricula());
            entity.setBase(dto.getBase());
            entity.setModelo(dto.getModelo());
            entity.setPlantilla(dto.getPlantilla());
            entity.setExtra(dto.getExtra());
            entity.setTrasera(dto.getTrasera());
            entity.setIzqDelantera(dto.getIzqDelantera());
            entity.setIzqPlus(dto.getIzqPlus());
            entity.setIzqTrasera(dto.getIzqTrasera());
            entity.setDerDelantera(dto.getDerDelantera());
            entity.setDerPlus(dto.getDerPlus());
            entity.setDerTrasera(dto.getDerTrasera());
            entity.setCampania(dto.getCampania());
            entity.setProvincia(dto.getProvincia());
            entity.setInfo_plus(dto.getInfo_plus());
            entity.setObservaciones(dto.getObservaciones());
            entity.setZona(dto.getZona());
            entity.setColor(dto.getColor());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

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

            Carreteras saved = carreterasRepository.save(entity);
            return new CarreterasDTO(saved);
        }
    }

    public CarreterasDTO update(String nFlota, CarreterasDTO dto) {
        Carreteras existing = carreterasRepository.findById(nFlota)
                .orElseThrow(() -> new RuntimeException("No se encontró el registro con el número de flota: " + nFlota));

        existing.setCarretera(dto.getCarretera());
        existing.setOperador(dto.getOperador());
        existing.setLineas(dto.getLineas());
        existing.setMatricula(dto.getMatricula());
        existing.setBase(dto.getBase());
        existing.setModelo(dto.getModelo());
        existing.setPlantilla(dto.getPlantilla());
        existing.setExtra(dto.getExtra());
        existing.setTrasera(dto.getTrasera());
        existing.setIzqDelantera(dto.getIzqDelantera());
        existing.setIzqPlus(dto.getIzqPlus());
        existing.setIzqTrasera(dto.getIzqTrasera());
        existing.setDerDelantera(dto.getDerDelantera());
        existing.setDerPlus(dto.getDerPlus());
        existing.setDerTrasera(dto.getDerTrasera());
        existing.setCampania(dto.getCampania());
        existing.setProvincia(dto.getProvincia());
        existing.setInfo_plus(dto.getInfo_plus());
        existing.setObservaciones(dto.getObservaciones());
        existing.setZona(dto.getZona());
        existing.setColor(dto.getColor());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        System.out.println("--------- INTENTO DE ACTUALIZACIÓN ---------");
        System.out.println("nFlota: " + dto.getNFlota());
        System.out.println("fechaInicio: " + dto.getFechaInicio());
        System.out.println("fechaFin: " + dto.getFechaFin());

        if (dto.getFechaInicio() != null && !dto.getFechaInicio().isEmpty()) {
            existing.setFechaInicio(LocalDateTime.parse(dto.getFechaInicio(), formatter));
        } else {
            existing.setFechaInicio(null);
        }

        if (dto.getFechaFin() != null && !dto.getFechaFin().isEmpty()) {
            existing.setFechaFin(LocalDateTime.parse(dto.getFechaFin(), formatter));
        } else {
            existing.setFechaFin(null);
        }

        Carreteras updated = carreterasRepository.save(existing);
        return new CarreterasDTO(updated);
    }

    public void deleteByNFlota(String nFlota) {
        if (nFlota == null || nFlota.trim().isEmpty()) {
            throw new RuntimeException("Número de flota inválido para eliminar.");
        }
        if (!carreterasRepository.existsById(nFlota)) {
            throw new RuntimeException("El registro con el número de flota: " + nFlota + " no existe.");
        }
        carreterasRepository.deleteById(nFlota);
    }

    public List<CarreterasDTO> findByCarretera(String carretera) {
        return carreterasRepository.findByCarretera(carretera)
                .stream()
                .map(CarreterasDTO::new)
                .collect(Collectors.toList());
    }

    public List<CarreterasDTO> findByProvincia(String provincia) {
        return carreterasRepository.findByProvincia(provincia)
                .stream()
                .map(CarreterasDTO::new)
                .collect(Collectors.toList());
    }
}