package mx.hibernate.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mx.hibernate.model.Autor;
import mx.hibernate.model.Libro;

public class TestAutores {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
	
	public static void main(String[] args) {		
		crearDatos();
		//imprimirDatos();		
	}
	
	static void crearDatos(){
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Autor autor1 = new Autor(1L, "Pablo Pérez", "Española");
		Autor autor2 = new Autor(2L, "Elena Gómez", "Mexicana");
		Autor autor3 = new Autor(3L, "Miguel López", "Chilena");
		em.persist(autor1);
		em.persist(autor2);
		em.persist(autor3);
		
		em.persist(new Libro(1L, "Programar en Java es fácil", autor2));
		em.persist(new Libro(2L, "Como vestirse con estilo", autor3));
		em.persist(new Libro(3L, "Como cocinar sin quemar la cocina", autor1));
		em.persist(new Libro(4L, "Programar en cobol es divertido", autor2));
		em.persist(new Libro(5L, "Programar en cobol no es divertido", autor2));
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	static void imprimirDatos(){
		EntityManager em = emf.createEntityManager();
		
		Autor autor = em.find(Autor.class, 3L);
		List<Libro> libros = autor.getLibros();
		libros.size();//Se hace este hack para que jpa obtenga los datos (tomando en cuenta que los tiene en Fetch modo LAZY, 
		//con esta sentencia los obtendrá en memoria y no aparecerá el Error Lazy Initialization 
		em.close();
		System.out.println(autor);
		for(Libro libro : libros){
			System.out.println("* " + libro.toString());
		}		
	}

}
