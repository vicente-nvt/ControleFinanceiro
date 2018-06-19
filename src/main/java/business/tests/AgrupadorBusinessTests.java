package business.tests;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.AgrupadorBusiness;
import model.Agrupador;
import utils.Transform;

public class AgrupadorBusinessTests {

	private JSONObject agrupadorJSON;
	private JSONObject cabecalhoAgrupador;
	private JSONArray arrayAgrupador;
	private AgrupadorBusiness business;
	private Agrupador agrupador, agrup;
		
	@Before
	public void criarAgrupadorJSON() throws Exception {
		
		agrupadorJSON = new JSONObject();				
		cabecalhoAgrupador = new JSONObject();
		arrayAgrupador = new JSONArray();
		agrupador = new Agrupador();
	}	
	
	@Test
	public void inserirAgrupador() throws Exception{	

		agrupadorJSON.put("descricao", "Agrupador Teste");
		agrupadorJSON.put("diaVencimento", "25/02/2018");
				
		cabecalhoAgrupador.put("agrupador", agrupadorJSON);
		arrayAgrupador.put(cabecalhoAgrupador);
		
		agrupador.setDescricao("Agrupador Teste");
		agrupador.setDiaVencimento(Transform.StringToDate("25/02/2018"));
				
		business = new AgrupadorBusiness();
		
		agrup = business.lancarAgrupador(arrayAgrupador); 
		
		assertTrue(agrup.equals(agrupador));		
	}
	
	@Test
	public void editarAgrupador() throws Exception{
				
		agrupadorJSON.put("descricao", "Agrupador Teste");
		agrupadorJSON.put("diaVencimento", "25/02/2018");
				
		cabecalhoAgrupador.put("agrupador", agrupadorJSON);
		arrayAgrupador.put(cabecalhoAgrupador);

		business = new AgrupadorBusiness();
		
		agrupador = business.lancarAgrupador(arrayAgrupador);
		
		agrupador.setDescricao("Agrupador Editado");
		agrupador.setDiaVencimento(Transform.StringToDate("21/01/2019"));
		
		agrupadorJSON.put("codigo", agrupador.getCodigo());
		agrupadorJSON.put("descricao", "Agrupador Editado");
		agrupadorJSON.put("diaVencimento", "21/01/2019");
		
		agrup = business.editarAgrupador(arrayAgrupador);
		
		assertTrue(agrup.equals(agrupador));
	}
	
	@Test 
	public void removerAgrupador() {
	
		agrupadorJSON.put("descricao", "Agrupador Teste");
		agrupadorJSON.put("diaVencimento", "25/02/2018");
				
		cabecalhoAgrupador.put("agrupador", agrupadorJSON);
		arrayAgrupador.put(cabecalhoAgrupador);

		business = new AgrupadorBusiness();
		
		agrupador = business.lancarAgrupador(arrayAgrupador);	
		
		assertTrue(business.removerAgrupador(agrupador.getCodigo()));	
	}
	
	@After
	public void removerAgrupadores(){
	
		try {
			new AgrupadorBusiness().removerAgrupador(agrupador.getCodigo());
			new AgrupadorBusiness().removerAgrupador(agrup.getCodigo());
		} finally {		
		}
	}
	
}
