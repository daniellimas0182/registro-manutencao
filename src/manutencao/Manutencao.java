package manutencao;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
	
public static class WrapperList {
		
		private List<Manutencao> manutencoes = new ArrayList<>();

		public List<Manutencao> getManutencoes() { 
			return manutencoes;
		}

		public void setVeiculos(List<Manutencao> manutencoes) {
			this.manutencoes = manutencoes;
		}
		
		public Manutencao get(int index) {
			return this.manutencoes.get(index);
		}
		
		public void add(Manutencao manutencao) { 
			this.manutencoes.add(manutencao);
		}
		
		public void delete(int index) {
			this.manutencoes.remove(index);
		}
	}
	
}
