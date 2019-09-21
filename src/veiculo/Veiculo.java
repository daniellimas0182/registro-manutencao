package veiculo;

import java.util.ArrayList;
import java.util.List;

public class Veiculo {
	
	private int ano;
	private String placa;
	private VeiculoTipo tipo;
	private String descricao;
	
	public Veiculo(int ano, String placa, VeiculoTipo tipo, String descricao) {
		this.ano = ano;
		this.placa = placa;
		this.tipo = tipo;
		this.descricao = descricao;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public VeiculoTipo getTipo() {
		return tipo;
	}

	public void setTipo(VeiculoTipo tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static class WrapperList {
		
		private List<Veiculo> veiculos = new ArrayList<>();

		public List<Veiculo> getVeiculos() {
			return veiculos;
		}

		public void setVeiculos(List<Veiculo> veiculos) {
			this.veiculos = veiculos;
		}
		
		public Veiculo get(int index) {
			return this.veiculos.get(index);
		}
		
		public void add(Veiculo veiculo) {
			this.veiculos.add(veiculo);
		}
		
		public void delete(int index) {
			this.veiculos.remove(index);
		}
	}
}
