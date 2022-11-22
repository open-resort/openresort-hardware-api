package co.edu.uco.openresort.hardware_api.entidad;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "operacion_tag")
public class OperacionTagEntidad {
    @Id
    private long id;
    private long tag;
    private LocalDateTime fecha;
    private String operacion;
}
