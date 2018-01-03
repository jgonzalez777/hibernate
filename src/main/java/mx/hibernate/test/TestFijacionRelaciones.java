package mx.hibernate.test;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mx.hibernate.model.Autor;
import mx.hibernate.model.Libro;

public class TestFijacionRelaciones {

private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
	
	public static void main(String[] args) {		
		crearDatos();
		imprimirDatos();		
	}
	
	static void crearDatos(){
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Libro libro1 = new Libro();
		libro1.setId(1L);
		libro1.setTitulo("JPA e Hibernate");
		em.persist(libro1);
		
		Autor autor1 = new Autor(1L, "J.J. Benítez", "Española");
		autor1.addLibro(libro1);//Es importante que el otro lado de la relación se entere, por lo tanto se generan los 2 metodos add y remove libros
		//Relación bidireccional, donde el autor se entera de los libros que tiene y los libros al autor que pertenece.
		System.out.println("Libros escriots (pre-save): " + autor1.getLibros().size());
		em.persist(autor1);
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	static void imprimirDatos(){
		EntityManager em = emf.createEntityManager();
		
		Autor autor = em.find(Autor.class, 1L);
		List<Libro> libros = autor.getLibros();		
		
		System.out.println("Libros escritos (post-save): " + libros.size());
		for(Libro libro : libros){
			System.out.println("* " + libro.toString());
		}		
		
		em.close();
	}

}
