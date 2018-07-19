package infra.conversores;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dominio.modelo.Agrupador;
import dominio.modelo.Compromisso;
import dominio.modelo.Movimento;
import dominio.modelo.Tipo;
import infra.repositorio.AgrupadorRepository;
import infra.repositorio.IRepository;
import infra.utilitarios.DateTransformation;

public class ConversorDeCompromissoJson implements IConversorJSON<Compromisso> {

	private IRepository<Agrupador> repositorioDeAgrupador;
	
	public ConversorDeCompromissoJson(IRepository<Agrupador> repositorioDeAgrupador) 
	{
		this.repositorioDeAgrupador = repositorioDeAgrupador;
	}

	@Override
	public Compromisso jsonToDomain(JSONArray jsonArray) 
	{		
		JSONObject compromissoJson = (JSONObject) jsonArray.getJSONObject(0).get("compromisso");
		
		String descricao = compromissoJson.getString("descricao");
		Movimento movimento = Movimento.valueOf(compromissoJson.getString("movimento"));
		int parcela = compromissoJson.getInt("parcela");
		int totalParcelas = compromissoJson.getInt("totalParcelas");
		Tipo tipo = Tipo.valueOf(compromissoJson.getString("tipo"));
		BigDecimal valorEfetivo = compromissoJson.getBigDecimal("valorEfetivo");
		BigDecimal valorPrevisto = compromissoJson.getBigDecimal("valorPrevisto");
		Agrupador agrupador = repositorioDeAgrupador.findById(compromissoJson.getInt("agrupador"));
		Date diaVencimento;
		try {
			diaVencimento = DateTransformation.StringToDate(compromissoJson.getString("diaVencimento"));
		} catch (Exception e) {
			diaVencimento = Calendar.getInstance().getTime();
		}
		
		return new Compromisso(descricao, movimento, parcela, tipo, totalParcelas, 
				valorEfetivo, valorPrevisto, agrupador, diaVencimento);
	}

	@Override
	public JSONArray domainToJson(Compromisso compromisso) 
	{
		return null;
	}
}