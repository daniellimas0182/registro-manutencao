package manutencao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import manutencao.Manutencao;

public class ManutencaoService {
	
public static List<Manutencao> getManutencoes(HttpServletRequest request) {
		
		List<Manutencao> manutencoes =  (List<Manutencao>)request.getSession().getAttribute("manutencoes");
		
		if(manutencoes == null) {
			return new ArrayList<Manutencao>();
		}
		
		return manutencoes;
	}
	
	public static void addManutencao(HttpServletRequest request, Manutencao novoManutencao) {
		
		List<Manutencao> manutencoes = getManutencoes(request);
		manutencoes.add(novoManutencao);
		
		request.getSession().setAttribute("manutencoes", manutencoes); 
	}

	public static Manutencao getManutencao(HttpServletRequest request, int index) {
		return getManutencoes(request).get(index);
	}
	
	public static void deleteManutencao(HttpServletRequest request, int index) {
		getManutencoes(request).remove(index);
	}

}
