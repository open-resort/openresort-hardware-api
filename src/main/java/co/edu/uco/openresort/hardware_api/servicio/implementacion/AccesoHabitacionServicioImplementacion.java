package co.edu.uco.openresort.hardware_api.servicio.implementacion;

import co.edu.uco.openresort.hardware_api.Credenciales;
import co.edu.uco.openresort.hardware_api.cliente.HttpCliente;
import co.edu.uco.openresort.hardware_api.entidad.OperacionTagEntidad;
import co.edu.uco.openresort.hardware_api.repositorio.OperacionTagRepositorio;
import co.edu.uco.openresort.hardware_api.servicio.AccesoHabitacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccesoHabitacionServicioImplementacion implements AccesoHabitacionServicio {

    @Autowired
    private HttpCliente httpCliente;
    @Autowired
    private OperacionTagRepositorio operacionTagRepositorio;

    @Override
    public ResponseEntity<?> darAcceso(long idTag, int idHabitacion) throws Exception {
        OperacionTagEntidad operacionTagEntidad = new OperacionTagEntidad();

        operacionTagEntidad.setTag(idTag);
        operacionTagEntidad.setFecha(LocalDateTime.now());
        operacionTagEntidad.setOperacion("acceso a la habitacion con id: "+ idHabitacion);

        ResponseEntity respuesta = httpCliente.post(Credenciales.ENDPOINT_OPENRESORT_ACCESO,"{\"idTag\":" + idTag + ",\"idHabitacion\":" + idHabitacion + "}");

        //La comparacion debe ser por status code y no por body
        if (respuesta.getBody().toString().equals("acceso concedido")){
            operacionTagRepositorio.insert(operacionTagEntidad);
        }

        return respuesta;
    }

    @Override
    public void quitarAcceso(long idTag, int idHabitacion) {

    }
}
