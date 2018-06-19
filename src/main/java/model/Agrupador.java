package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
public class Agrupador implements Serializable, IPersistent {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;

	private String descricao;

	@Temporal(TemporalType.DATE)
	private Date diaVencimento;

	//bi-directional many-to-one association to Compromisso
	@OneToMany(mappedBy="agrupador")
	private List<Compromisso> compromissos;

	public Agrupador() {
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDiaVencimento() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data = sdf.format(this.diaVencimento);
		Date dataRetorno;
		
		try {
			dataRetorno = sdf.parse(data);
		} catch (ParseException e) {
			System.out.println("Falha ao converter data: " + e.getMessage());
			dataRetorno = null;
		}
		return dataRetorno;		
	}

	public void setDiaVencimento(Date diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public List<Compromisso> getCompromissos() {
		return this.compromissos;
	}

	public void setCompromissos(List<Compromisso> compromissos) {
		this.compromissos = compromissos;
	}

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