import org.junit.Test;

import com.gdunivo.es.model.Alumnos;
import com.gdunivo.es.model.RolAlumno;
import com.gdunivo.es.model.Roles;
import com.gdunivo.es.repository.RepositoryFactory;
import com.gdunivo.es.repository.RolAlumnoRepository;
import com.gdunivo.es.repository.RolesRepository;

/**
 * @author SPastora
 *
 */
public class ProbarConexion {

	Alumnos a1;
	Alumnos a2;
	Roles r1;
	RolAlumno ra1;
	RolAlumno ra2;

	@Test
	public void testRepository() {
		this.setAlumno();
		this.setRolAlumno();
		RolAlumnoRepository persis = (RolAlumnoRepository) RepositoryFactory.getRepository("RolAlumno");
		System.out.println(persis.busquedaPorAlumno(a1));

	}

	private void setAlumno() {
		this.r1 = new Roles();
		this.r1.setId_Role(3);
		this.a1 = new Alumnos();
		this.a2 = new Alumnos();
		this.a1.setCodAlumno("A000000001");
		this.a2.setCodAlumno("A000000004");
	}

	private void setRolAlumno() {
		this.ra1 = new RolAlumno();
		this.ra2 = new RolAlumno();

		this.ra1.setAlumno(a1);
		this.ra2.setAlumno(a2);

		this.ra1.setRol(r1);
		this.ra2.setRol(r1);

		this.ra1.setContraseña("123");
		this.ra2.setRolAlumno(2);
		;
		this.ra2.setContraseña("admin");

	}

}
