package dominio.modelo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Agrupador implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;

	private String descricao;

	@Temporal(TemporalType.DATE)
	private Date diaVencimento;

	@OneToMany(mappedBy="agrupador")
	private List<Compromisso> compromissos;

	public Agrupador() {}

	public int getCodigo() { return this.codigo; }
	
	public void setCodigo(int codigo) { this.codigo = codigo; }

	public String getDescricao() { return this.descricao; }

	public void setDescricao(String descricao) { this.descricao = descricao; }
	
	public Date getDiaVencimento() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data = sdf.format(this.diaVencimento);
		Date dataRetorno;
		
		try {
			dataRetorno = sdf.parse(data);
		} catch (ParseException e) {			
			dataRetorno = null;
		}		
		return dataRetorno;		
	}

	public void setDiaVencimento(Date diaVencimento) { this.diaVencimento = diaVencimento; }
	
	private List<Compromisso> getCompromissos() { return this.compromissos; }	

	public Compromisso addCompromisso(Compromisso compromisso) {
		getCompromissos().add(compromisso);
		compromisso.setAgrupador(this);

		return compromisso;
	}

	public Compromisso removeCompromisso(Compromisso compromisso) {
		getCompromissos().remove(compromisso);
		compromisso.setAgrupador(null);

		return compromisso;
	}
	
	public boolean equals(Agrupador agrupador){
		return 	agrupador.getDescricao().equals(this.getDescricao()) &&
				agrupador.getDiaVencimento().compareTo(this.getDiaVencimento()) == 0;			
	}
}