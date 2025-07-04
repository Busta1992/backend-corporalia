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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        boolean hayCambios = false;

        if (!equals(existingBBDD.getClub(), bbddDTO.getClub())) {
            existingBBDD.setClub(bbddDTO.getClub());
            hayCambios = true;
        }
        if (!equals(existingBBDD.getCp(), bbddDTO.getCp())) {
            existingBBDD.setCp(bbddDTO.getCp());
            hayCambios = true;
        }
        if (!equals(existingBBDD.getDireccion(), bbddDTO.getDireccion())) {
            existingBBDD.setDireccion(bbddDTO.getDireccion());
            hayCambios = true;
        }
        if (!equals(existingBBDD.getMunicipio(), bbddDTO.getMunicipio())) {
            existingBBDD.setMunicipio(bbddDTO.getMunicipio());
            hayCambios = true;
        }
        if (!equals(existingBBDD.getObservacion(), bbddDTO.getObservacion())) {
            existingBBDD.setObservacion(bbddDTO.getObservacion());
            hayCambios = true;
        }
        if (!equals(existingBBDD.getProvincia(), bbddDTO.getProvincia())) {
            existingBBDD.setProvincia(bbddDTO.getProvincia());
            hayCambios = true;
        }
        if (!equals(existingBBDD.getCampania(), bbddDTO.getCampania())) {
            existingBBDD.setCampania(bbddDTO.getCampania());
            hayCambios = true;
        }

        // Fecha inicio
        LocalDateTime nuevaFechaInicio = null;
        if (bbddDTO.getFechaInicio() != null && !bbddDTO.getFechaInicio().isEmpty()) {
            nuevaFechaInicio = LocalDateTime.parse(bbddDTO.getFechaInicio(), formatter);
        }
        if (!equals(existingBBDD.getFechaInicio(), nuevaFechaInicio)) {
            existingBBDD.setFechaInicio(nuevaFechaInicio);
            hayCambios = true;
        }

        // Fecha fin
        LocalDateTime nuevaFechaFin = null;
        if (bbddDTO.getFechaFin() != null && !bbddDTO.getFechaFin().isEmpty()) {
            nuevaFechaFin = LocalDateTime.parse(bbddDTO.getFechaFin(), formatter);
        }
        if (!equals(existingBBDD.getFechaFin(), nuevaFechaFin)) {
            existingBBDD.setFechaFin(nuevaFechaFin);
            hayCambios = true;
        }

        if (!hayCambios) {
            // No se modificó nada
            return new BBDDDTO(existingBBDD);
        }

        BBDD updatedBBDD = bbddRepository.save(existingBBDD);
        return new BBDDDTO(updatedBBDD);
    }

    // Método auxiliar para comparar con null safety
    private boolean equals(Object a, Object b) {
        return (a == null && b == null) || (a != null && a.equals(b));
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