package index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import veiculo.Veiculo;
import veiculo.VeiculoTipo;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Veiculo> veiculos =  (List<Veiculo>)request.getSession().getAttribute("veiculos");
		
		if(veiculos == null) {
			
			System.out.println("null");
			veiculos = new ArrayList<Veiculo>();
			veiculos.add(new Veiculo(2014, "MLK-5569", VeiculoTipo.MOTO, "Tenere 250"));
			
			request.getSession().setAttribute("veiculos", veiculos);
		}else {
			System.out.println("Ola");
			System.out.println(veiculos.size());
			veiculos.forEach(v -> System.out.println(v.getDescricao()));
		}
		
		
		
//		response.setContentType("text/html; charset=UTF-8");
//		response.setCharacterEncoding("UTF-8");
//        response.getWriter().println("Antonio nunes");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
