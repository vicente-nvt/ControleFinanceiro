package infra.conversores;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import dominio.modelo.Agrupador;
import infra.conversores.ConversorDeAgrupadorJson;

public class ConversorDeAgrupadorJSONTeste {

	JSONObject agrupadorJson;
	JSONObject cabecalhoAgrupadorJson;
	JSONArray arrayJson;
	Agrupador agrupador;
	ConversorDeAgrupadorJson conversor;
	
	@Before
	public void criarObjetoJson()
	{		
		agrupadorJson = new JSONObject();
		cabecalhoAgrupadorJson = new JSONObject();
		arrayJson = new JSONArray();
		conversor = new ConversorDeAgrupadorJson();
		
		agrupadorJson.put("descricao", "Teste");
		agrupadorJson.put("diaVencimento", "10/03/2018");	
		cabecalhoAgrupadorJson.put("agrupador", agrupadorJson);
		
		arrayJson.put(cabecalhoAgrupadorJson);
		
		Calendar data = Calendar.getInstance();
		data.set(2018, 2, 10);

		agrupador = new Agrupador();
		agrupador.setDescricao("Teste");
		agrupador.setDiaVencimento(data.getTime());
	}
	
	@Test
	public void deveTransformarJsonEmAgrupador() 
	{		
		Agrupador agrupador = conversor.jsonToDomain(arrayJson);
		
		assertTrue(agrupador.equals(this.agrupador));		
	}
	
	@Test
	public void deveTransformarAgrupadorEmJson()
	{			
		String descricaoEsperada = "Teste";
		String diaVencimentoEsperado = "10/03/2018";
		
		JSONObject agrupadorJson = (JSONObject) conversor.domainToJson(agrupador)
				.getJSONObject(0)
				.get("agrupador");		
				
		assertEquals(descricaoEsperada , agrupadorJson.get("descricao"));
		assertEquals(diaVencimentoEsperado, agrupadorJson.get("diaVencimento"));
	}	
}