package repository.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Compromisso;
import model.Movimento;
import model.Tipo;
import repository.CompromissoRepository;
import repository.Repository;

public class CompromissoRepositoryTests {


	EntityManagerFactory factory = Persistence.createEntityManagerFactory("ControleFinanceiro");
	EntityManager manager = factory.createEntityManager();
	Repository repository;
	Compromisso compromisso;
	
	@Before
	public void criarCompromisso(){				
		compromisso = new Compromisso();
		
		compromisso.setDescricao("Teste");
		compromisso.setMovimento(Movimento.SAIDA);
		compromisso.setParcela(1);
		compromisso.setTipo(Tipo.UNICO);
		compromisso.setTotalParcelas(1);		
		compromisso.setValorEfetivo(new BigDecimal("10.00"));
		compromisso.setValorPrevisto(new BigDecimal("10.00"));
		
		repository = new Repository(this.manager);
	}
			
	@Test
	public void inserirCompromisso() throws Exception{
		
		Compromisso comp = (Compromisso) repository.save(this.compromisso); 
		
		assertTrue(comp.equals(compromisso));				
	}
	
	@Test
	public void deletarCompromisso() throws Exception{
		
		repository.save(this.compromisso);
		
		assertTrue(repository.delete(this.compromisso));		
	}
	
	@Test
	public void pesquisarCompromissoPorId() throws Exception{
		
		repository.save(this.compromisso);
		
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("ControleFinanceiro");
		CompromissoRepository repo = new CompromissoRepository(fac.createEntityManager());
				
		Compromisso comp = repo.findById(this.compromisso.getCodigo());
		
		fac.close();
		
		assertTrue(comp != compromisso);
		assertTrue(comp.equals(this.compromisso));		
	}
	
	
	@Test
	public void editarCompromisso () throws Exception {
	
		repository.save(compromisso);	
		
		compromisso.setDescricao("Teste Editado");
		compromisso.setMovimento(Movimento.ENTRADA);
		
		repository.edit(compromisso);
					
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("ControleFinanceiro");
		CompromissoRepository repo = new CompromissoRepository(fac.createEntityManager());
				
		Compromisso comp = repo.findById(this.compromisso.getCodigo());
		
		fac.close();		
		
		assertTrue(comp != compromisso);
		assertTrue(comp.equals(compromisso));
	}
	
	@After 
	public void removerCompromisso() throws Exception{
		try {
			repository.delete(compromisso);
		} finally {
			factory.close();
		}
		
	}
	
}
