package index;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manutencao.Manutencao;
import manutencao.ManutencaoService;
import veiculo.Veiculo;
import veiculo.VeiculoService;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Veiculo> veiculos =  VeiculoService.getVeiculos(request);
		
		String relatorioVeiculos = "";
		
		if(veiculos.size() == 0) {
			relatorioVeiculos = "<p>Não existem veículos registrados</p>";
		}else {
			String cabecalho = 
					"<p>Veículos Registrados</p>" +
        			"<table id='listagem' align='center'>"+
					"	<tr>"+
					"		<th>"+
					"			Descrição"+
					"		</th>" +
					"		<th>"+
					"			Placa" +
					"		</th>"  +
					"		<th>"+
					"			Tipo" +
					"		</th>"  +
					"		<th>"+
					"			Ano" +
					"		</th>" +
					"	</tr>";
        	
        	
        	String listagem = "";
        	
        	for(Veiculo v: veiculos) {
        		listagem += 
						"	<tr>"+
						"		<td>"+
									v.getDescricao() +
						"		</td>" +
						"		<td>"+
									v.getPlaca() +
						"		</td>"  +
						"		<td>"+
									v.getTipo().getDescricao() +
						"		</td>"  +
						"		<td>"+
									v.getAno() +
						"		</td>" +
						"	</tr>";
        	}
        	
        	String total = 
        			"<tr>"+
					"	<td colspan='4'>"+
        			"		Total de Veículos: "+ veiculos.size() +
					"	</td>"+
					"</tr>" +
					"</table>";
        	
        	relatorioVeiculos = cabecalho + listagem + total;
		}
		
		List<Manutencao> manutencoes = ManutencaoService.getManutencoes(request);
		
		String relatorioManutencoes = "";
		
		if(manutencoes.size() == 0) {
			relatorioManutencoes = "<p>Não existem manutenções registradas</p>";
		}else {
			String cabecalho = 
					"<p>Manutenções Registradas</p>" +
        			"<table id='listagem' align='center'>"+
					"	<tr>"+
					"		<th>"+
					"			Descrição"+
					"		</th>" +
					"		<th>"+
					"			Quilometragem" +
					"		</th>"  +
					"		<th>"+
					"			Valor" +
					"		</th>"  +
					"		<th>"+
					"			Veículo" +
					"		</th>" +
					"	</tr>";
        	
        	
        	String listagem = "";
        	
        	for(Manutencao m: manutencoes) {
        		listagem += 
						"	<tr>"+
						"		<td>"+
									m.getDescricao() +
						"		</td>" +
						"		<td>"+
									m.getQuilometragem() +
						"		</td>"  +
						"		<td>"+
									m.getValor().toString().replaceAll("\\.", ",")+
						"		</td>"  +
						"		<td>"+
									m.getVeiculo().getPlaca() + " - " + m.getVeiculo().getDescricao() +
						"		</td>" +
						"	</tr>";
        	}
        	
        	String total = 
        			"<tr>"+
					"	<td colspan='4'>"+
        			"		Total de Manutenções: "+ manutencoes.size() +
					"	</td>"+
					"</tr>" +
					"</table>";
        	
        	relatorioManutencoes = cabecalho + listagem + total;
		}
		
		
		defaultPage(response, relatorioVeiculos + "<br /> <br />" + relatorioManutencoes);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void defaultPage(HttpServletResponse response, String dinamico) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
        response.getWriter().println(
        		"<!DOCTYPE html>" + 
        		"<html>" + 
        		"<head>" + 
        		"<meta charset='UTF-8'>" + 
        		"<title>Registro de Manutenções</title>" + 
        		"<style type='text/css'>" + 
        		"	body {" + 
        		"		margin: 0;" + 
        		"		font-family: Arial, Helvetica, sans-serif;" + 
        		"	}" + 
        		"	.topnav {" + 
        		"  		overflow: hidden;" + 
        		"  		background-color: #333;" + 
        		"	}" +  
        		"	.topnav a {" + 
        		"		float: left;" + 
        		"  		color: #f2f2f2;" + 
        		"  		text-align: center;" + 
        		"  		padding: 14px 16px;" + 
        		"  		text-decoration: none;" + 
        		"  		font-size: 17px;" + 
        		"	}" + 
        		"" + 
        		"	.topnav a:hover {" + 
        		"  		background-color: #ddd;" + 
        		"  		color: black;" + 
        		"	}" + 
        		"" + 
        		"	.topnav a.active {" + 
        		"  		background-color: #4CAF50;" + 
        		"  		color: white;" + 
        		"	}"+ 
        		"" + 
        		"	.button {" + 
        		"  		background-color: #4CAF50; /* Green */" + 
        		"  		border: none;" + 
        		"  		color: white;" + 
        		"  		padding: 5px 32px;" + 
        		"  		text-align: center;" + 
        		"  		text-decoration: none;" + 
        		"  		display: inline-block;" + 
        		"  		font-size: 16px;" + 
        		"  		margin: 4px 2px;" + 
        		"  		-webkit-transition-duration: 0.4s; /* Safari */" + 
        		"  		transition-duration: 0.2s;" + 
        		"  		cursor: pointer;" + 
        		"	}"+ 
        		""+ 
        		"	.buttongreen {" + 
        		"  		background-color: white; " + 
        		"  		color: black; " + 
        		"  		border: 2px solid #4CAF50;" + 
        		"	}" + 
        		""+ 
        		"	.buttongreen:hover {" + 
        		"  		background-color: #4CAF50;" + 
        		"  		color: white;" + 
        		"	}"+
        		""+
        		"	.buttonred {" + 
        		"  		background-color: white; " + 
        		"  		color: black; " + 
        		"  		border: 2px solid #f44336" + 
        		"	}" + 
        		""+ 
        		"	.buttonred:hover {" + 
        		"  		background-color: #f44336;" + 
        		"  		color: white;" + 
        		"	}"+
        		""+
        		"	table#listagem {" + 
        		"  		border: 1px solid black;" + 
        		"  		border-collapse: collapse;" + 
        		"	}" + 
        		""+
        		"	table#listagem th{" + 
        		"  		border: 1px solid black;" + 
        		"  		border-collapse: collapse;" +
        		"  		padding: 15px;" + 
        		"  		text-align: left;" +
        		"	}" +
        		""+
        		"	table#listagem td {" + 
        		"  		border: 1px solid black;" + 
        		"  		border-collapse: collapse;" + 
        		"  		padding: 15px;" + 
        		"  		text-align: left;" +
        		"	}" +
        		"</style>" + 
        		"</head>" + 
        		"<body style='text-align: center'>" + 
        		"	" + 
        		"	<div class='topnav'>" + 
        		"		<a style='float: left; margin-right: 20px; font-weight: bold; font-size: 19px; border-right: 1px solid white'>Gerenciamento de Manutenções</a>" + 
        		"  		<a class='active' href='/registro-manutencao/'>Home</a>" + 
        		"  		<a href='/registro-manutencao/veiculo'>Veículos</a>" + 
        		"  		<a href='/registro-manutencao/manutencao'>Manutenções</a>" + 
        		"	</div>" +
        		"	<div>" +
        		"	  <h2>Home</h2>" +
        		"	  <h3>Este são seus itens registrados:</h3>"
        		);
        
        response.getWriter().println(dinamico);
        
        response.getWriter().println(
        		"	</div>" + 
        		"</body>" + 
        		"</html>");
	}
}
