package ClinicaOdontologica.service;

import ClinicaOdontologica.model.Odontologo;
import ClinicaOdontologica.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    @Autowired
    private OdontologoRepository odontologoRepository;

    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    public Odontologo actualizarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }

    public Optional<Odontologo> buscarOdontologoPorId(Long id){
        return odontologoRepository.findById(id);
    }

    public void eliminarOdontologo(Long id) {
        odontologoRepository.deleteById(id);
    }

    public List<Odontologo> buscarOdontologos(){
        return odontologoRepository.findAll();
    }

    public Optional<Odontologo> buscarOdontologPorMatricula(String matricula){
        return odontologoRepository.findByNumeroMatricula(matricula);
    }

    public boolean existeOdontologoPorMatriculaId(String matricula, Long id){
        var odontologoBuscado = odontologoRepository.findByNumeroMatricula(matricula);
        if(odontologoBuscado.isPresent() && !odontologoBuscado.get().getId().equals(id)){
            return true;
        }
        return false;
    }
}
