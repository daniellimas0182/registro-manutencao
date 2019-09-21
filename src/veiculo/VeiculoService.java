package veiculo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class VeiculoService {
	
	public static List<Veiculo> getVeiculos(HttpServletRequest request) {
		
		
		System.out.println(request.getSession().getAttribute("veiculos"));
		
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

}
