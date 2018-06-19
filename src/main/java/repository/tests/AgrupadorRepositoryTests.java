package repository.tests;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Agrupador;
import repository.Repository;

public class AgrupadorRepositoryTests {
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("ControleFinanceiro");
	private Agrupador agrupador;
	private Repository repository = new Repository(factory.createEntityManager());
	private Calendar diaVencimento;
	
	@Before
	public void criarAgrupador(){
		agrupador = new Agrupador();
		
		agrupador.setDescricao("Teste");		
		diaVencimento = Calendar.getInstance();
		agrupador.setDiaVencimento(diaVencimento.getTime());
	}
	
	
	@Test
	public void inserirAgrupador() throws Exception{
				
		Agrupador agrup = (Agrupador) repository.save(agrupador); 
		
		assertTrue(agrup.equals(agrupador));		
	}
	
	@Test
	public void removerAgrupador() throws Exception{
		
		this.agrupador = (Agrupador) repository.save(this.agrupador);
		
		assertTrue(repository.delete(this.agrupador));
	}
	
	@Test
	public void editarAgrupador() throws Exception{
		
		repository.save(agrupador);
		
		agrupador.setDescricao("Editado");
		
		Calendar data = Calendar.getInstance();
		data.setTime(agrupador.getDiaVencimento());
		data.add(Calendar.DATE, +1);		
		
		agrupador.setDiaVencimento(data.getTime());

		repository.edit(agrupador);
		
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("ControleFinanceiro");
		Repository repo = new Repository(fac.createEntityManager());					
		
		Agrupador agrup = (Agrupador) repo.findById(agrupador);
				
		fac.close();	
		
		assertTrue(agrup != agrupador);
		assertTrue(agrup.equals(agrupador));
	}
	
	@Test
	public void buscarAgrupadorPorId() throws Exception{		
		
		repository.save(this.agrupador);	
		
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("ControleFinanceiro");
		Repository repo = new Repository(fac.createEntityManager());
		
		Agrupador agrupador = (Agrupador) repo.findById(this.agrupador);
		
		fac.close();
		
		assertTrue(agrupador != this.agrupador);
		assertTrue(agrupador.equals(this.agrupador));			
	}
	
	@After
	public void removerAgrupadores() {

		try {					
			repository.delete(this.agrupador);
		} catch (Exception e) {
			factory.close();
		}			
				
		
	}
	
}
