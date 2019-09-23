package manutencao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import veiculo.Veiculo;
import veiculo.VeiculoService;
import veiculo.VeiculoTipo;
import manutencao.Manutencao;



@WebServlet("/manutencao")
public class ManutencaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		String id = request.getParameter("id");
		
		if(id != null && id.length() > 0) { //GET
			
			if("new".equals(id)) {				
				String formInicio = "<form action='/registro-manutencao/manutencao?id=new' method='post'>" +
						"	<table align='center'>"+
						"		<tr>"+
						"			<td align='right'>"+
						"				Quilometragem:"+
						"			</td>" +
						"			<td align='left'>"+
						"				<input required type='number' min='0' name='quilometragem'/>" +
						"			</td>" +
						"		</tr>" +
						"		<tr>"+
						"			<td align='right'>"+
						"				Descrição:"+
						"			</td>" +
						"			<td align='left'>"+
						"				<input required type='text' name='descricao'/>" +
						"			</td>" +
						"		</tr>" +
						"		<tr>"+
						"			<td align='right'>"+
						"				Valor (R$):"+
						"			</td>" +
						"			<td align='left'>"+
						"				<input required type='number' min='0' step='0.01' name='valor'/>" +
						"			</td>" +
						"		</tr>" +
						"		<tr>"+
						"			<td align='right'>"+
						"				Veículo:"+
						"			</td>" +
						"			<td align='left'>"+
						"				<select name='placa' required>";
	        	
			String opcoes = "";
	       	for(Veiculo veiculo : VeiculoService.getVeiculos(request)) {
	       		opcoes += "<option value='" + veiculo.getPlaca() + "'>" + veiculo.getPlaca() + " - " + veiculo.getDescricao() + "</option>";
        	}
	        	
	        	String formFim = "		</select> " +  
						"			</td>" +
						"		</tr>" +
						"		<tr>"+
						"			<td colspan='2' align='right'>"+
						"				<input class='button buttongreen' type='submit' value='Salvar'/>"+
						"			</td>" +
						"		</tr>" +
						"	</table>" +	 
						"</form>";
				
				defaultPage(response, "Nova Manutenção", formInicio + opcoes + formFim);
			}else if("true".equals(request.getParameter("delete"))){
				doPost(request, response);
			}else {
				
				Manutencao manutencao = ManutencaoService.getManutencao(request, Integer.parseInt(id));
				
				String formInicio = "<form action='/registro-manutencao/manutencao?id=" + id + "' method='post'>" +
						"	<table align='center'>"+
						"		<tr>"+
						"			<td align='right'>"+
						"				Quilometragem:"+
						"			</td>" +
						"			<td align='left'>"+
						"				<input required type='text' name='quilometragem' value='" + manutencao.getQuilometragem() + "'/>" +
						"			</td>" +
						"		</tr>" +
						"		<tr>"+
						"			<td align='right'>"+
						"				Descrição:"+
						"			</td>" +
						"			<td align='left'>"+
						"				<input required type='text' name='descricao' value='" + manutencao.getDescricao() + "'/>" +
						"			</td>" +
						"		</tr>" +
						"		<tr>"+
						"			<td align='right'>"+
						"				Valor (R$):"+
						"			</td>" +
						"			<td align='left'>"+
						"				<input required type='number' min='0' name='valor' value='" + manutencao.getValor() + "'/>" +
						"			</td>" +
						"		</tr>" +
						"		<tr>"+
						"			<td align='right'>"+
						"				Veículo:"+
						"			</td>" +
						"			<td align='left'>"+
						"				<select name='placa' required>";
	        	
				String opcoes = "";
	        	for(Veiculo veiculo : VeiculoService.getVeiculos(request)) {
	        		
	        		String selecionado = "";
	        		
		        	if(veiculo.equals(manutencao.getVeiculo())){
		       			selecionado = "selected='selected'";
		        	}
	        		
		        	opcoes += "<option value='" + veiculo.getPlaca()+ "'" + selecionado + ">" + veiculo.getPlaca() + " - " + veiculo.getDescricao() + "</option>";
	        	}
	        	
	        	String formFim = "		</select> " +  	
						"			</td>" +
						"		</tr>" +
						"		<tr>"+
						"			<td align='right'>"+
						"				<a href='/registro-manutencao/manutencao?id=" + id + "&delete=true" + "' class='button buttonred'>Excluir</a>"+
						"			</td>" +
						"			<td align='right'>"+
						"				<input class='button buttongreen' type='submit' value='Salvar'/>"+
						"			</td>" +
						"		</tr>" +
						"	</table>" +	 
						"</form>";
				
				defaultPage(response, "Edição de Manutenção", formInicio + opcoes + formFim);
			}
			
		}else { // LIST
			
	        List<Manutencao> manutencoes = ManutencaoService.getManutencoes(request);
	        if(manutencoes.size() == 0) {
	        	defaultPage(response, "Listagem de Manutenções",
	        			"<p>Sem manutenções cadastradas.</p><a href='?id=new' class='button buttongreen'>Nova Manutenção</a>"
	        			);
	        }else {
	        	
	        	String cabecalho = 
	        			"<a href='?id=new' class='button buttongreen'>Nova Manutenção</a><br /><br />" + 
	        			"<table id='listagem' align='center'>"+
    					"	<tr>"+
						"		<th>"+
						"			Descrição"+
						"		</th>" +
						"		<th>"+
						"			Quilometragem" +
						"		</th>"  +
						"		<th>"+
						"			Valor (R$)" +
						"		</th>"  +
						"		<th>"+
						"			Veículo" +
						"		</th>" +
						"		<th>"+
						"			" +
						"		</th>" +
						"	</tr>";
	        	
	        	
	        	String listagem = "";
	        	
	        	int i = 0;
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
							            m.getValor().toString().replaceAll("\\.", ",") +
							"		</td>"  +
							"		<td>"+
							            m.getVeiculo().getPlaca() + " - " + m.getVeiculo().getDescricao() +
							"		</td>" +
							"		<td>"+
							"			<a href='?id=" + i + "'>Editar</a>" +
							"		</td>" +
							"	</tr>";
	        		i++;
	        	}
	        	
	        	
	        	defaultPage(response, "Listagem de Manutenções", cabecalho + listagem + "</table>");
	        }
	        
		}
		
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		
		if ("new".equals(id)) {
			
			String placa = request.getParameter("placa");
			
			Veiculo veiculoSelecionado = null;
			for(Veiculo veiculo : VeiculoService.getVeiculos(request)) {

				if(veiculo.getPlaca().equals(placa)) {
					veiculoSelecionado = veiculo;
				}
			}
			
			Manutencao novo = new Manutencao(veiculoSelecionado, Integer.parseInt(request.getParameter("quilometragem")), new BigDecimal(request.getParameter("valor").replaceAll(",", ".")),request.getParameter("descricao"));
		    ManutencaoService.addManutencao(request, novo);

		} else {

			if ("true".equals(request.getParameter("delete"))) {
				ManutencaoService.deleteManutencao(request, Integer.parseInt(id));
			} else {
				Manutencao manutencao = ManutencaoService.getManutencao(request, Integer.parseInt(id));

				manutencao.setQuilometragem(Integer.parseInt(request.getParameter("quilometragem")));
				manutencao.setDescricao(request.getParameter("descricao"));
				manutencao.setValor(new BigDecimal(request.getParameter("valor")));
				
				String placa = request.getParameter("placa");
				
				Veiculo veiculoSelecionado = null;
				for(Veiculo veiculo : VeiculoService.getVeiculos(request)) {

					if(veiculo.getPlaca().equals(placa)) {
						veiculoSelecionado = veiculo;
					}
				}
				
				manutencao.setVeiculo(veiculoSelecionado);
			}
		}
		
		response.sendRedirect("/registro-manutencao/manutencao");
		
	}
	
	private void defaultPage(HttpServletResponse response, String titulo, String dinamico) throws IOException {
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
        		"  		<a href='/registro-manutencao/'>Home</a>" + 
        		"  		<a href='/registro-manutencao/veiculo'>Veículos</a>" + 
        		"  		<a class='active' href='/registro-manutencao/manutencao'>Manutenções</a>" + 
        		"	</div>" +
        		"	<div>" +
        		"	  <h2>" + titulo + "</h2>" 
        		);
        
        response.getWriter().println(dinamico);
        
        response.getWriter().println(
        		"	</div>" + 
        		"</body>" + 
        		"</html>");
	}
	
	

}
