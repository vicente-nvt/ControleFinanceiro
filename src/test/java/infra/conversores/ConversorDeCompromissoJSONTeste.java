package infra.conversores;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.mockito.Mock;

import dominio.modelo.Agrupador;
import dominio.modelo.Compromisso;
import dominio.modelo.Movimento;
import dominio.modelo.Tipo;
import infra.repositorio.IRepository;

public class ConversorDeCompromissoJSONTeste {

	@Mock
	private IRepository<Agrupador> repositorioAgrupador;
	

	@Test
	public void deveConverterJsonParaCompromisso(){
		
		Compromisso compromissoEsperado = new Compromisso("Teste", Movimento.ENTRADA,			
				1, Tipo.FIXO, 10, new BigDecimal(10.0), new BigDecimal(10.00), 
				new Agrupador(), Calendar.getInstance().getTime());
		
		Compromisso coSmpromissoConvertido = new ConversorDeCompromissoJson();
		
		assertTrue(compromissoEsperado .equals(compromissoConvertido));
	}
	
}
