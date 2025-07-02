package corporalia.corporalia.Service;

import corporalia.corporalia.DTO.BBDDDTO;
import corporalia.corporalia.Model.BBDD;
import corporalia.corporalia.Repository.BBDDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BBDDService {

    @Autowired
    private BBDDRepository bbddRepository;

    public List<BBDDDTO> findAll() {
        return bbddRepository.findAll()
                .stream()
                .map(BBDDDTO::new)
                .collect(Collectors.toList());
    }

    public BBDDDTO findByCodigo(String codigo) {
        BBDD bbdd = bbddRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("No se encontró el registro con el código: " + codigo));
        return new BBDDDTO(bbdd);
    }

    public BBDDDTO save(BBDDDTO bbddDTO) {
        System.out.println("--------- INTENTO DE GUARDADO ---------");
        System.out.println("Recibido DTO:");
        System.out.println("codigo: " + bbddDTO.getCodigo());
        System.out.println("fechaInicio: " + bbddDTO.getFechaInicio());
        System.out.println("fechaFin: " + bbddDTO.getFechaFin());

        if (bbddDTO.getCodigo() == null || bbddDTO.getCodigo().trim().isEmpty()) {
            throw new RuntimeException("El campo 'codigo' es obligatorio para guardar.");
        }

        if (bbddRepository.existsById(bbddDTO.getCodigo())) {
            return update(bbddDTO.getCodigo(), bbddDTO);
        } else {
            BBDD bbdd = bbddDTO.toEntity();
            BBDD savedBBDD = bbddRepository.save(bbdd);
            return new BBDDDTO(savedBBDD);
        }
    }

    public BBDDDTO update(String codigo, BBDDDTO bbddDTO) {
        BBDD existingBBDD = bbddRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("No se encontró el registro con el código: " + codigo));

        existingBBDD.setClub(bbddDTO.getClub());
        existingBBDD.setCp(bbddDTO.getCp());
        existingBBDD.setDireccion(bbddDTO.getDireccion());
        existingBBDD.setMunicipio(bbddDTO.getMunicipio());
        existingBBDD.setObservacion(bbddDTO.getObservacion());
        existingBBDD.setProvincia(bbddDTO.getProvincia());
        existingBBDD.setCampania(bbddDTO.getCampania());


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        System.out.println("--------- INTENTO DE ACTUALIZACIÓN ---------");
        System.out.println("Recibido DTO:");
        System.out.println("codigo: " + bbddDTO.getCodigo());
        System.out.println("fechaInicio: " + bbddDTO.getFechaInicio());
        System.out.println("fechaFin: " + bbddDTO.getFechaFin());

        if (bbddDTO.getFechaInicio() != null && !bbddDTO.getFechaInicio().isEmpty()) {
            existingBBDD.setFechaInicio(LocalDateTime.parse(bbddDTO.getFechaInicio(), formatter));
        } else {
            existingBBDD.setFechaInicio(null);
        }

        if (bbddDTO.getFechaFin() != null && !bbddDTO.getFechaFin().isEmpty()) {
            existingBBDD.setFechaFin(LocalDateTime.parse(bbddDTO.getFechaFin(), formatter));
        } else {
            existingBBDD.setFechaFin(null);
        }

        BBDD updatedBBDD = bbddRepository.save(existingBBDD);
        return new BBDDDTO(updatedBBDD);
    }

    public void deleteByCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new RuntimeException("Código inválido para eliminar.");
        }
        if (!bbddRepository.existsById(codigo)) {
            throw new RuntimeException("El registro con el código: " + codigo + " no existe.");
        }
        bbddRepository.deleteById(codigo);
    }

    public List<BBDDDTO> findByProvincia(String provincia) {
        return bbddRepository.findByProvincia(provincia)
                .stream()
                .map(BBDDDTO::new)
                .collect(Collectors.toList());
    }
}