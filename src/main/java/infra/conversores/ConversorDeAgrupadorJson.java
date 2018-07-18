package infra.conversores;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import dominio.modelo.Agrupador;
import infra.utilitarios.DateTransformation;

public class ConversorDeAgrupadorJson implements IConversorJSON<Agrupador> 
{
	@Override
	public Agrupador jsonToDomain(JSONArray jsonArray) 
	{					
		JSONObject agrupadorJson = (JSONObject) jsonArray.getJSONObject(0).get("agrupador");
		
		Date data;
		try {
			data = DateTransformation.StringToDate(agrupadorJson.getString("diaVencimento"));
		}
		catch (Exception e) { 
			data = null; 
		}

		Agrupador agrupador = new Agrupador();
		agrupador.setDescricao(agrupadorJson.getString("descricao"));
		agrupador.setDiaVencimento(data);
		
		return agrupador;
	}
	
	@Override
	public JSONArray domainToJson(Agrupador agrupador) 
	{
		JSONObject agrupadorJson = new JSONObject();
		JSONObject agrupadorCabecalho = new JSONObject();
		
		agrupadorJson.put("descricao", agrupador.getDescricao());
		agrupadorJson.put("diaVencimento", DateTransformation.DateToString(agrupador.getDiaVencimento()));
		agrupadorCabecalho.put("agrupador", agrupadorJson);
				
		return new JSONArray().put(agrupadorCabecalho);
	}
}