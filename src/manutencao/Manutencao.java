package manutencao;

import java.math.BigDecimal;

import veiculo.Veiculo;

public class Manutencao {
	
	private Veiculo veiculo;
	private int quilometragem;
	private BigDecimal valor;
	private String descricao;
	
	public Manutencao(Veiculo veiculo, int quilometragem, BigDecimal valor, String descricao) {
		this.veiculo = veiculo;
		this.quilometragem = quilometragem;
		this.valor = valor;
		this.descricao = descricao;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public int getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(int quilometragem) {
		this.quilometragem = quilometragem;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
