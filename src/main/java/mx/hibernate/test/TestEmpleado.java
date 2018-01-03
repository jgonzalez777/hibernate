package mx.hibernate.test;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mx.hibernate.model.Direccion;
import mx.hibernate.model.Empleado;

public class TestEmpleado {	
		
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");

	public static void main(String[] args) {
		
		EntityManager manager = emf.createEntityManager();
		Empleado e = new Empleado(1L, "González", "Jorge", new GregorianCalendar(1989,2,13).getTime());
		e.setDireccion(new Direccion(15L, "Calle Tarango", "CDMX", "DF", "MEXICO"));
		manager.getTransaction().begin();
		manager.persist(e);
		manager.getTransaction().commit();
		manager.close();
		imprimirEmpleados();
		
		
		
//		EntityManager manager = emf.createEntityManager();
//		Empleado e = new Empleado(1L, "González", "Jorge", new GregorianCalendar(1989,2,13).getTime());
//		manager.getTransaction().begin();
//		manager.persist(e);
//		manager.getTransaction().commit();
//		manager.close();
//		
//		imprimirEmpleados();
//		
//		manager = emf.createEntityManager();
//		manager.getTransaction().begin();		
//		e = manager.merge(e);
//		e.setNombre("Juion");
//		manager.remove(e);
//		manager.getTransaction().commit();
//		manager.close();
//		
//		imprimirEmpleados();
	}

	/*Insertar un empleado*/
	private static void insertInicial() {	
		EntityManager manager = emf.createEntityManager();
		Empleado e = new Empleado(1L, "González", "Jorge", new GregorianCalendar(1989,2,13).getTime());
		manager.getTransaction().begin();
		manager.persist(e);
		manager.getTransaction().commit();
		manager.close();
	}		
		
	/*Imprimir los empleados en el sistema*/
	@SuppressWarnings("unchecked")
	public static void imprimirEmpleados(){
		EntityManager manager = emf.createEntityManager();
		List<Empleado> empleados = (List<Empleado>) manager.createQuery("FROM Empleado").getResultList();
		System.out.println("En esta base de datos hay " + empleados.size() + " empleados.");
		for(Empleado empleado : empleados)
			System.out.println(empleado.toString());
		manager.close();
	}
}
