
import java.time.LocalDate;

import org.junit.Test;

import com.gdunivo.es.model.Alumnos;
import com.gdunivo.es.repository.AlumnosRepository;
import com.gdunivo.es.repository.MateriasRepository;
import com.gdunivo.es.repository.RepositoryFactory;

public class ProbarConexion {

	@Test
	public void testConexion() {
		Alumnos a = new Alumnos();
		a.setCodAlumno("A000000006");
		a.setNombre("Jose");
		a.setApellido("Ventura");
		a.setDireccion("Morazan");
		a.setFechaNacimiento(LocalDate.of(1976, 10, 23));
		a.setSexo("M");
		AlumnosRepository persis = (AlumnosRepository) RepositoryFactory.getRepository("Alumnos");

	
		persis.busquedaPorApellido("A").forEach(System.out::println);

	}

}
