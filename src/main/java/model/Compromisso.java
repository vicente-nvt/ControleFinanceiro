package model;

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
public class Compromisso implements Serializable, IPersistent {
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
	
	public Movimento getMovimento(){
		return Movimento.valueOf(this.movimento);
	}
	
	public void setMovimento(Movimento movimento){
		this.movimento = movimento.toString();
	}

	public int getParcela() {
		return this.parcela;
	}

	public void setParcela(int parcela) {
		this.parcela = parcela;
	}

	public Tipo getTipo() {
		return Tipo.valueOf(this.tipo);
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo.toString();
	}

	public int getTotalParcelas() {
		return this.totalParcelas;
	}

	public void setTotalParcelas(int totalParcelas) {
		this.totalParcelas = totalParcelas;
	}

	public BigDecimal getValorEfetivo() {
		return this.valorEfetivo;
	}

	public void setValorEfetivo(BigDecimal valorEfetivo) {
		this.valorEfetivo = valorEfetivo;
	}

	public BigDecimal getValorPrevisto() {
		return this.valorPrevisto;
	}

	public void setValorPrevisto(BigDecimal valorPrevisto) {
		this.valorPrevisto = valorPrevisto;
	}

	public Agrupador getAgrupador() {
		return this.agrupador;
	}

	public void setAgrupador(Agrupador agrupador) {
		this.agrupador = agrupador;
	}

	public List<Pagamento> getPagamentos() {
		return this.pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

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

	public Date getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Date diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

}