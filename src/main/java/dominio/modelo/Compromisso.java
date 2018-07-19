package dominio.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name="Compromisso.findAll", query="SELECT c FROM Compromisso c")
public class Compromisso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;
	
	private String descricao;
	
	private String movimento;
	
	private int parcela;
	
	private String tipo;
	
	private int totalParcelas;
	
	private BigDecimal valorEfetivo;
	
	private BigDecimal valorPrevisto;	
	
	@Temporal(TemporalType.DATE)
	private Date diaVencimento;

	@ManyToOne
	@JoinColumn(name="codigoAgrupador")
	private Agrupador agrupador;

	@OneToMany(mappedBy="compromisso")
	private List<Pagamento> pagamentos;

	public Compromisso() {
	}
	
	public Compromisso(String descricao, Movimento movimento, int parcela,
		Tipo tipo, int totalParcelas, BigDecimal valorEfetivo, 
		BigDecimal valorPrevisto, Agrupador agrupador, Date diaVencimento)
	{
		this.movimento = movimento.toString();
		this.tipo = tipo.toString();
		this.parcela = parcela;
		this.totalParcelas = totalParcelas;
		this.valorEfetivo = valorEfetivo;
		this.valorPrevisto = valorPrevisto;
		this.agrupador = agrupador;
		this.diaVencimento = diaVencimento;
	}

	public int getCodigo() { return this.codigo; }
	public String getDescricao() {	return this.descricao;	}
	public Movimento getMovimento(){ return Movimento.valueOf(this.movimento);	}
	public int getParcela() { return this.parcela; }
	public Tipo getTipo() {	return Tipo.valueOf(this.tipo);	}
	public int getTotalParcelas() { return this.totalParcelas; }
	public BigDecimal getValorEfetivo() { return this.valorEfetivo; }
	public BigDecimal getValorPrevisto() { return this.valorPrevisto; }
	public Agrupador getAgrupador() { return this.agrupador; }
	public List<Pagamento> getPagamentos() { return this.pagamentos; }
	public Date getDiaVencimento() { return diaVencimento; }

	public void setAgrupador(Agrupador agrupador) { this.agrupador = agrupador; }
	
	public Pagamento addPagamento(Pagamento pagamento) {
		getPagamentos().add(pagamento);
		pagamento.setCompromisso(this);

		return pagamento;
	}

	public Pagamento removePagamento(Pagamento pagamento) {
		getPagamentos().remove(pagamento);
		pagamento.setCompromisso(null);

		return pagamento;
	}
	
	public boolean equals (Compromisso compromisso) {
		return this.getCodigo() == compromisso.getCodigo() &&
			   this.getDescricao().equals(compromisso.getDescricao()) &&
			   this.getMovimento().compareTo(compromisso.getMovimento()) == 0 &&
			   this.getTipo().compareTo(compromisso.getTipo()) == 0 &&
			   this.getParcela() == compromisso.getParcela() &&
			   this.getTotalParcelas() == compromisso.getTotalParcelas() &&
			   this.getValorEfetivo().equals(compromisso.getValorEfetivo()) &&
			   this.getValorPrevisto().equals(compromisso.getValorPrevisto());
			   
	}	
}