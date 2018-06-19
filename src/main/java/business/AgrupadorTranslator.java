package business;

import org.json.JSONArray;
import org.json.JSONObject;
import model.Agrupador;
import utils.Transform;

public class AgrupadorTranslator {

	private Agrupador agrupador;
	
	public Agrupador transformarJsonEmAgrupador(JSONArray arrayJson) throws Exception {
		
		try {
			agrupador = new Agrupador();
						
			JSONObject agrupadorJson = (JSONObject) arrayJson.getJSONObject(0).get("agrupador");
			
			agrupador.setDescricao(agrupadorJson.getString("descricao"));
			agrupador.setDiaVencimento(Transform.StringToDate(agrupadorJson.getString("diaVencimento")));			
			
			return agrupador;
			
		} catch (Exception e) {
			throw new Exception ("Falha ao transformar JSON em Agrupador: " + e.getMessage());			
		}		

	}
		
	
}
