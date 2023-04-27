package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Asignatura;
import model.Asignaturaspordocente;
import model.Docente;

public class AsignaturapordocenteController {
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("ExamenProfesoresYMateriasJPA");

	/** 
	 * 
	 */
	public static Asignaturaspordocente findByCosas(int idAsignatura, int idProfesor) {
		EntityManager em = entityManagerFactory.createEntityManager();

		Asignaturaspordocente o = null;

		Query q = em.createNativeQuery("SELECT id FROM asignaturaspordocente where idAsignatura = ? and idDocente = ?;",
				Asignaturaspordocente.class);
		q.setParameter(1, idAsignatura);
		q.setParameter(2, idProfesor);

		try {
			o = (Asignaturaspordocente) q.getSingleResult();
			em.close();
			return o;
		} catch (Exception e) {
			return null;
		}
	}

	/** 
	 * 
	 */
	public static List<Asignaturaspordocente> findById(int id) {
		EntityManager em = entityManagerFactory.createEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM asignaturaspordocente where idDocente = ?;",
				Asignaturaspordocente.class);
		q.setParameter(1, id);
		List<Asignaturaspordocente> lista = (List<Asignaturaspordocente>) q.getResultList();

		em.close();

		return lista;
	}

	/** 
	 * 
	 */
	public static List<Asignaturaspordocente> findByIdDistinto(int id) {
		EntityManager em = entityManagerFactory.createEntityManager();

		Query q = em.createNativeQuery("SELECT distinct * FROM asignaturaspordocente where idDocente != ?;",
				Asignaturaspordocente.class);
		q.setParameter(1, id);
		List<Asignaturaspordocente> lista = (List<Asignaturaspordocente>) q.getResultList();

		em.close();

		return lista;
	}

	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static Asignaturaspordocente cargar(int id) {
		Asignaturaspordocente o = null;
		try {
			Connection conn = ConnectionManager.getConexion();

			PreparedStatement ps = conn.prepareStatement("SELECT distinct idAsignatura FROM asignaturaspordocente where idDocente !=" + id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				o = new Asignaturaspordocente();
				o.setId(rs.getInt("idAsignatura"));
				o.setDocente(obtencionUnaSolaEntidad());
				o.setAsignatura(obtencionUnaSolaEntidad2());
			}

			rs.close();
			ps.close();
			conn.close();

			return o;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @return  
	 * 
	 */
	private static Docente obtencionUnaSolaEntidad () {
		EntityManager em = entityManagerFactory.createEntityManager();

		Docente coche = (Docente) em.find(Docente.class, 1);		
		em.close();
		
		return coche;
	}

	private static Asignatura obtencionUnaSolaEntidad2 () {
		EntityManager em = entityManagerFactory.createEntityManager();

		Asignatura coche = (Asignatura) em.find(Asignatura.class, 1);		
		em.close();
		
		return coche;
	}
	
	/** 
	 * 
	 */
	public static List<Asignaturaspordocente> findDistinct(int id) {
		EntityManager em = entityManagerFactory.createEntityManager();

		Query q = em.createNativeQuery("SELECT distinct(idAsignatura) FROM asignaturaspordocente where idDocente != ?",
				Asignaturaspordocente.class);
		q.setParameter(1, id);
		List<Asignaturaspordocente> lista = (List<Asignaturaspordocente>) q.getResultList();

		em.close();

		return lista;
	}

	/**
	 * 
	 * @param o
	 */
	public static void insert(Asignaturaspordocente o) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * 
	 * @param o
	 */
	public static void delete(Asignaturaspordocente o) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		o = em.merge(o);
		em.remove(o);
		System.out.println("HECHO");
		em.getTransaction().commit();
		em.close();
	}
}
