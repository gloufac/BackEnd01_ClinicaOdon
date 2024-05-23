package ClinicaOdontologica.dao;

import ClinicaOdontologica.model.Odontologo;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class OdontologoDaoList {

    private static final Logger logger = Logger.getLogger(BaseDatos.class);
    private static final HashMap<String, Odontologo> map_odontologos = new HashMap<>();

    public OdontologoDaoList() {
    }

    public Odontologo guardar(Odontologo odontologo) {
        var odBuscado = buscar(odontologo);
        if (Objects.isNull(odBuscado)) {
            map_odontologos.put(odontologo.getNumeroMatricula(), odontologo);
            logger.info("Odontologo Map se ha agregado");
        } else {
            logger.info("Odontologo Map se ha encontrado");
        }
        return buscar(odontologo);
    }

    public Odontologo buscar(Odontologo odontologo){
        String llaveMatricula = odontologo.getNumeroMatricula();
        return map_odontologos.get(llaveMatricula);
    }

    public List<Odontologo> buscarTodos(){
        return map_odontologos.values().stream().toList();
    }
}
