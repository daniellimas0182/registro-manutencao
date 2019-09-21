package veiculo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class VeiculoService {
	
	public static List<Veiculo> getVeiculos(HttpServletRequest request) {
		
		List<Veiculo> veiculos =  (List<Veiculo>)request.getSession().getAttribute("veiculos");
		
		if(veiculos == null) {
			return new ArrayList<Veiculo>();
		}
		
		return veiculos;
	}
	
	public static void addVeiculo(HttpServletRequest request, Veiculo novoVeiculo) {
		
		List<Veiculo> veiculos = getVeiculos(request);
		veiculos.add(novoVeiculo);
		
		request.getSession().setAttribute("veiculos", veiculos);
	}

	public static Veiculo getVeiculo(HttpServletRequest request, int index) {
		return getVeiculos(request).get(index);
	}
	
	public static void deleteVeiculo(HttpServletRequest request, int index) {
		getVeiculos(request).remove(index);
	}

}
