package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Asignatura;
import model.Asignaturaspordocente;

public class AsignaturaController {
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("ExamenProfesoresYMateriasJPA");

	/** 
	 * 
	 */
	public static List<Asignatura> findAll() {
		EntityManager em = entityManagerFactory.createEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM asignatura;", Asignatura.class);
		List<Asignatura> l = (List<Asignatura>) q.getResultList();

		em.close();

		return l;
	}

	/** 
	 * 
	 */
	public static List<Asignatura> findByAsignatura(Asignatura o) {
		EntityManager em = entityManagerFactory.createEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM asignatura where id = ?;", Asignatura.class);
		q.setParameter(1, o.getId());
		List<Asignatura> lista = (List<Asignatura>) q.getResultList();

		em.close();

		return lista;
	}
}
