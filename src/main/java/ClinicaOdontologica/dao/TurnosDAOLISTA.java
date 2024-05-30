package ClinicaOdontologica.dao;


import ClinicaOdontologica.model.Turno;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TurnosDAOLISTA implements iDao<Turno>{
    private static final Logger logger=Logger.getLogger(TurnosDAOLISTA.class);
    private List<Turno> turnos= new ArrayList<>();
    @Override
    public Turno guardar(Turno turno) {
        logger.info("iniciando las operaciones de guardado de un turno");
        //que hacemos para guardar un turno?
        PacienteDaoH2 daoPaciente= new PacienteDaoH2();
        OdontologoDaoH2 daoOdontologo= new OdontologoDaoH2();
        turno.setPaciente(daoPaciente.buscarPorID(turno.getPaciente().getId()));
        turno.setOdontologo(daoOdontologo.buscarPorID(turno.getOdontologo().getId()));
        turnos.add(turno);
        logger.info("turno guardado");
        return turno;
    }

    @Override
    public Turno buscarPorID(Integer id) {
        for (Turno turno : turnos) {
            if(turno.getId().equals(id)){
                return turno;
            }
                  }
        return null;
    }

    @Override
    public void actualizar(Turno turno) {
        logger.info("iniciando las operaciones de actualizar un turno");
        if (turno.getId() != null) {

            PacienteDaoH2 daoPaciente= new PacienteDaoH2();
            OdontologoDaoH2 daoOdontologo= new OdontologoDaoH2();
            turno.setPaciente(daoPaciente.buscarPorID(turno.getPaciente().getId()));
            turno.setOdontologo(daoOdontologo.buscarPorID(turno.getOdontologo().getId()));

            List<Integer> indices = IntStream.range(0, turnos.size())
                    .filter(i -> turnos.get(i).getId().equals(turno.getId()))
                    .boxed()
                    .collect(Collectors.toList());

            // Print the indices
            System.out.println(indices);

            if (!indices.isEmpty()) {
                turnos.set(indices.getFirst(), turno);
            }
        }
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("iniciando las operaciones de eliminar un turno");

        List<Integer> indices = IntStream.range(0, turnos.size())
                .filter(i -> turnos.get(i).getId().equals(id))
                .boxed()
                .collect(Collectors.toList());

        if (!indices.isEmpty()) {
            turnos.remove(indices.getFirst());
        }
    }

    @Override
    public List<Turno> buscarTodos() {
        return turnos;
    }

    @Override
    public Turno buscarPorString(String valor) {
        return null;
    }
}
