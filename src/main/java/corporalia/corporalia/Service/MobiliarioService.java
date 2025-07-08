package corporalia.corporalia.Service;

import corporalia.corporalia.DTO.BBDDDTO;
import corporalia.corporalia.DTO.CarreterasDTO;
import corporalia.corporalia.DTO.MobiliarioDTO;
import corporalia.corporalia.Model.BBDD;
import corporalia.corporalia.Model.Carreteras;
import corporalia.corporalia.Model.Mobiliario;
import corporalia.corporalia.Repository.MobiliarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MobiliarioService {

    @Autowired
    private MobiliarioRepository mobiliarioRepository;

    public List<MobiliarioDTO> findAll() {
        return mobiliarioRepository.findAll()
                .stream()
                .map(MobiliarioDTO::new)
                .collect(Collectors.toList());
    }

    public MobiliarioDTO findByCodigo(String codigo) {
        Mobiliario mobiliario = mobiliarioRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("No se encontró el registro con el número de codigo: " + codigo));
        return new MobiliarioDTO(mobiliario);
    }

    public MobiliarioDTO save(MobiliarioDTO dto) {
        System.out.println("--------- INTENTO DE GUARDADO ---------");
        System.out.println("Recibido DTO:");
        System.out.println("codigo: " + dto.getCodigo());
        System.out.println("fechaInicio: " + dto.getFechaInicio());
        System.out.println("fechaFin: " + dto.getFechaFin());

        if (dto.getCodigo() == null || dto.getCodigo().trim().isEmpty()) {
            throw new RuntimeException("El campo 'codigo' es obligatorio y no puede estar vacío.");
        }

        if (mobiliarioRepository.existsById(dto.getCodigo())) {
            return update(dto.getCodigo(), dto);
        } else {
            Mobiliario entity = mapDtoToEntity(dto);
            Mobiliario saved = mobiliarioRepository.save(entity);
            return new MobiliarioDTO(saved);
        }
    }

    public MobiliarioDTO update(String codigo, MobiliarioDTO dto) {
        Mobiliario existing = mobiliarioRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("No se encontró el registro con el número de flota: " + codigo));

        updateEntityFromDto(existing, dto);
        Mobiliario updated = mobiliarioRepository.save(existing);
        return new MobiliarioDTO(updated);
    }

    public void deleteByCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new RuntimeException("Número de flota inválido para eliminar.");
        }
        if (!mobiliarioRepository.existsById(codigo)) {
            throw new RuntimeException("El registro con el número de flota: " + codigo + " no existe.");
        }
        mobiliarioRepository.deleteById(codigo);
    }

    public List<MobiliarioDTO> findByProvincia(String provincia) {
        return mobiliarioRepository.findByProvincia(provincia)
                .stream()
                .map(MobiliarioDTO::new)
                .collect(Collectors.toList());
    }

    public List<MobiliarioDTO> obtenerPorMunicipio(String municipio) {
        List<Mobiliario> lista = mobiliarioRepository.findByMunicipioIgnoreCase(municipio);
        return lista.stream()
                .map(MobiliarioDTO::new)
                .collect(Collectors.toList());
    }

    // ✅ NUEVO: Guardado en lote directo (bulk-fast)
    public List<MobiliarioDTO> saveBulkDirecto(List<MobiliarioDTO> dtos) {
        List<Mobiliario> entidades = dtos.stream()
                .map(this::mapDtoToEntity)
                .collect(Collectors.toList());

        List<Mobiliario> guardados = mobiliarioRepository.saveAll(entidades);

        return guardados.stream()
                .map(MobiliarioDTO::new)
                .collect(Collectors.toList());
    }

    // 🔁 Conversión DTO → Entidad
    private Mobiliario mapDtoToEntity(MobiliarioDTO dto) {
        Mobiliario entity = new Mobiliario();

        entity.setCodigo(dto.getCodigo());
        entity.setCampania(dto.getCampania());
        entity.setMunicipio(dto.getMunicipio());
        entity.setProvincia(dto.getProvincia());
        entity.setCp(dto.getCp());
        entity.setObservaciones(dto.getObservaciones());
        entity.setColor(dto.getColor());
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaFin(dto.getFechaFin());

        return entity;
    }

    // 🔁 Actualización Entidad desde DTO
    private void updateEntityFromDto(Mobiliario entity, MobiliarioDTO dto) {
        entity.setCampania(dto.getCampania());
        entity.setMunicipio(dto.getMunicipio());
        entity.setProvincia(dto.getProvincia());
        entity.setCp(dto.getCp());
        entity.setObservaciones(dto.getObservaciones());
        entity.setColor(dto.getColor());
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaFin(dto.getFechaFin());
        }
    }



