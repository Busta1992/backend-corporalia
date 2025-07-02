package corporalia.corporalia.Model;
import corporalia.corporalia.DTO.HistorialCampaniasDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "historial_campanias")
@Getter
@Setter



    public class HistorialCampanias {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "n_flota")
        private String nFlota;

        private String matricula;
        private String campania;
        private String carretera;
        private String operador;
        private String observaciones;

        @Column(name = "fecha_inicio")
        private LocalDateTime fechaInicio;

        @Column(name = "fecha_fin")
        private LocalDateTime fechaFin;

        public HistorialCampanias() {}

        public HistorialCampanias(HistorialCampaniasDTO dto) {
            this.nFlota = dto.getNFlota();
            this.matricula = dto.getMatricula();
            this.campania = dto.getCampania();
            this.carretera = dto.getCarretera();
            this.operador = dto.getOperador();
            this.observaciones = dto.getObservaciones();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

            if (dto.getFechaInicio() != null && !dto.getFechaInicio().isEmpty()) {
                this.fechaInicio = LocalDateTime.parse(dto.getFechaInicio(), formatter);
            }

            if (dto.getFechaFin() != null && !dto.getFechaFin().isEmpty()) {
                this.fechaFin = LocalDateTime.parse(dto.getFechaFin(), formatter);
            }
        }
    }


