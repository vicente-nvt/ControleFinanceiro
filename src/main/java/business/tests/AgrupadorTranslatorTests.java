package business.tests;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import business.AgrupadorTranslator;
import model.Agrupador;

public class AgrupadorTranslatorTests {

	JSONObject objetoJsonAgrupador;
	JSONObject cabecalhoJsonAgrupador;
	JSONArray arrayJson;
	Agrupador agrupador;
	
	@Before
	public void criarObjetoJson(){
		
		objetoJsonAgrupador = new JSONObject();
		cabecalhoJsonAgrupador = new JSONObject();
		arrayJson = new JSONArray();
		
		objetoJsonAgrupador.put("descricao", "Teste");
		objetoJsonAgrupador.put("diaVencimento", "10/03/2018");
		
		cabecalhoJsonAgrupador.put("agrupador", objetoJsonAgrupador);
		
		arrayJson.put(cabecalhoJsonAgrupador);
		
		agrupador = new Agrupador();
		
		agrupador.setDescricao("Teste");
		Calendar data = Calendar.getInstance();
		data.set(2018, 2, 10);
		agrupador.setDiaVencimento(data.getTime());
	}
	
	@Test
	public void transformarObjetoJsonEmAgrupador() throws Exception {
		
		AgrupadorTranslator translator = new AgrupadorTranslator();
		
		Agrupador agrupador = translator.transformarJsonEmAgrupador(arrayJson);
		
		assertTrue(agrupador.equals(this.agrupador));		
	}
	
}
