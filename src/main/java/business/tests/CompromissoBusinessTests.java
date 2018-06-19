package business.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Test;

import business.CompromissoBusiness;
import model.Agrupador;
import model.Compromisso;
import model.Movimento;
import model.Tipo;

public class CompromissoBusinessTests {

	private Compromisso compromisso;
	private Agrupador agrupador;	
	private EntityManager manager = Persistence.createEntityManagerFactory("ControleFinanceiro").createEntityManager();
	
	@Test
	public void lancarCompromissoUnicoComAgrupador(){
		
		agrupador = new Agrupador();
		
		Calendar data = Calendar.getInstance();
		data.set(2018, 3, 15);

		agrupador.setDescricao("Cartão Santander");
		agrupador.setDiaVencimento(data.getTime());
		
		compromisso = new Compromisso();
		
		compromisso.setAgrupador(agrupador);
		compromisso.setDescricao("Compromisso Único");
		compromisso.setMovimento(Movimento.SAIDA);
		compromisso.setTipo(Tipo.UNICO);
		compromisso.setValorEfetivo(new BigDecimal("150.00"));
		compromisso.setValorPrevisto(new BigDecimal("150.00"));			
		
		assertTrue(new CompromissoBusiness(manager).lancarCompromisso(null));						
	}
	
	@After 
	public void removerLancamentoCompromisso(){
		try {
			new CompromissoBusiness(manager).removerLancamento(compromisso);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
