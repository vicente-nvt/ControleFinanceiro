package dominio.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@NamedQuery(name="Pagamento.findAll", query="SELECT p FROM Pagamento p")
public class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;

	@Temporal(TemporalType.DATE)
	private Date dataPagamento;

	private BigDecimal valorPago;

	@ManyToOne
	@JoinColumn(name="codigoCompromisso")
	private Compromisso compromisso;

	public Pagamento() {}
	
	public Pagamento(Date dataPagamento, Compromisso compromisso, BigDecimal valorPago)
	{
		this.dataPagamento = dataPagamento;
		this.compromisso = compromisso;
		this.valorPago = valorPago;
	}
	
	public int getCodigo() {
		return this.codigo;
	}

	public Date getDataPagamento() {
		return this.dataPagamento;
	}

	public BigDecimal getValorPago() {
		return this.valorPago;
	}

	public Compromisso getCompromisso() {
		return this.compromisso;
	}

	public void setCompromisso(Compromisso compromisso) {
		this.compromisso = compromisso;
	}
}