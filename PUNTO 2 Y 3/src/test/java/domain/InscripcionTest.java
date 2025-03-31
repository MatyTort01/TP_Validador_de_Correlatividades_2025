package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InscripcionTest {

    @Test
    public void inscripcionVaciaAprobada() {
        // El alumno solicita una inscripcion vacia. Dado que no se aclara que no se pueden realizar inscripciones vacias, asumi que se puede ya que, por ejemplo, en el sistema de inscripciones de la utn se puede dejar una alternativa vacia.
        Alumno alumno = new Alumno(1234567);
        Inscripcion inscripcion = new Inscripcion(alumno);

        Assertions.assertTrue(inscripcion.aprobada());
    }

    @Test
    public void inscripcionUnaMateriaSinCorrelativasAprobada() {
        // El alumno solicita la inscripcion a una materia que NO posee correlativas.
        Alumno alumno = new Alumno(1234567);
        Materia inglesI = new Materia("Ingles I");
        Inscripcion inscripcion = new Inscripcion(alumno);
        inscripcion.agregarMaterias(inglesI);

        Assertions.assertTrue(inscripcion.aprobada());
    }

    @Test
    public void inscripcionUnaMateriaConCorrelativaAceptada() {
        // El alumno solicita la inscripcion a una materia que posee una correlativa. El alumno tiene aprobada la correlativa.
        Alumno alumno = new Alumno(1234567);
        Materia inglesI = new Materia("Ingles I");
        alumno.agregarMateriasAprobadas(inglesI);
        Materia inglesII = new Materia("Ingles II");
        inglesII.agregarMateriasCorrelativas(inglesI);
        Inscripcion inscripcion = new Inscripcion(alumno);
        inscripcion.agregarMaterias(inglesII);

        Assertions.assertTrue(inscripcion.aprobada());
    }

    @Test
    public void inscripcionUnaMateriaConCorrelativaRechazada() {
        // El alumno solicita la inscripcion a una materia que posee una correlativa. El alumno NO tiene aprobada la correlativa.
        Alumno alumno = new Alumno(1234567);
        Materia inglesI = new Materia("Ingles I");
        Materia inglesII = new Materia("Ingles II");
        inglesII.agregarMateriasCorrelativas(inglesI);
        Inscripcion inscripcion = new Inscripcion(alumno);
        inscripcion.agregarMaterias(inglesII);

        Assertions.assertFalse(inscripcion.aprobada());
    }

    @Test
    public void inscripcionVariasMateriasConCorrelativasAceptada() {
        // El alumno solicita la inscripcion a dos materia que poseen cada una de ellas dos correlativas. El alumno tiene aprobadas todas las correlativas.
        Alumno alumno = new Alumno(1234567);
        Materia sistemasYOrganizaciones = new Materia("Sistemas y Organizaciones");
        Materia algoritmosYEstructuraDeDatos = new Materia("Algoritmos y Estructura de Datos");
        Materia fisicaI = new Materia("Fisica I");
        Materia analisisMatematicoI = new Materia("Analisis Matematico I");
        alumno.agregarMateriasAprobadas(sistemasYOrganizaciones, algoritmosYEstructuraDeDatos, fisicaI, analisisMatematicoI);
        Materia analisisDeSistemas = new Materia("Analisis de Sistemas");
        analisisDeSistemas.agregarMateriasCorrelativas(sistemasYOrganizaciones, algoritmosYEstructuraDeDatos);
        Materia fisicaII = new Materia("Fisica II");
        fisicaII.agregarMateriasCorrelativas(fisicaI, analisisMatematicoI);
        Inscripcion inscripcion = new Inscripcion(alumno);
        inscripcion.agregarMaterias(analisisDeSistemas, fisicaII);

        Assertions.assertTrue(inscripcion.aprobada());
    }

    @Test
    public void inscripcionVariasMateriasConCorrelativasRechazada() {
        // El alumno solicita la inscripcion a dos materia que poseen cada una de ellas dos correlativas. El alumno tiene aprobadas las dos correlativas de una materia pero solo una de la otra materia.
        Alumno alumno = new Alumno(1234567);
        Materia sistemasYOrganizaciones = new Materia("Sistemas y Organizaciones");
        Materia algoritmosYEstructuraDeDatos = new Materia("Algoritmos y Estructura de Datos");
        Materia fisicaI = new Materia("Fisica I");
        Materia analisisMatematicoI = new Materia("Analisis Matematico I");
        alumno.agregarMateriasAprobadas(sistemasYOrganizaciones, algoritmosYEstructuraDeDatos, fisicaI);
        Materia analisisDeSistemas = new Materia("Analisis de Sistemas");
        analisisDeSistemas.agregarMateriasCorrelativas(sistemasYOrganizaciones, algoritmosYEstructuraDeDatos);
        Materia fisicaII = new Materia("Fisica II");
        fisicaII.agregarMateriasCorrelativas(fisicaI, analisisMatematicoI);
        Inscripcion inscripcion = new Inscripcion(alumno);
        inscripcion.agregarMaterias(analisisDeSistemas, fisicaII);

        Assertions.assertFalse(inscripcion.aprobada());
    }
}