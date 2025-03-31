package domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Inscripcion {
    private Alumno alumno;
    private List<Materia> materias;

    public Inscripcion(Alumno alumno) {
        this.alumno = alumno;
        this.materias = new ArrayList<>();
    }

    public void agregarMaterias(Materia ... materias){
        Collections.addAll(this.materias, materias);
    }

    public boolean aprobada(){
        List<Materia> materiasCorrelativas = this.materias
                .stream()
                .flatMap(materia -> materia.getMateriasCorrelativas().stream())
                .collect(Collectors.toList());

        return materiasCorrelativas
                .stream()
                .allMatch(materia -> alumno.aproboMateria(materia));
    }
}
