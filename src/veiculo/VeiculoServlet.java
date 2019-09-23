package veiculo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/veiculo")
public class VeiculoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		String id = request.getParameter("id");
		
		if(id != null && id.length() > 0) { //GET
			
			if("new".equals(id)) {				
				String formInicio = "<form action='/registro-manutencao/veiculo?id=new' method='post'>" +
						"	<table align='center'>"+
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
						"				Placa:"+
						"			</td>" +
						"			<td align='left'>"+
						"				<input required type='text' name='placa'/>" +
						"			</td>" +
						"		</tr>" +
						"		<tr>"+
						"			<td align='right'>"+
						"				Ano:"+
						"			</td>" +
						"			<td align='left'>"+
						"				<input required type='number' min='0' name='ano'/>" +
						"			</td>" +
						"		</tr>" +
						"		<tr>"+
						"			<td align='right'>"+
						"				Tipo de Veículo:"+
						"			</td>" +
						"			<td align='left'>"+
						"				<select name='tipo'>";
	        	
				String opcoes = "";
	        	for(VeiculoTipo tipo : VeiculoTipo.values()) {
	        		opcoes += "<option value='" + tipo + "'>" + tipo.getDescricao() + "</option>";
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
				
				defaultPage(response, "Novo Veículo", formInicio + opcoes + formFim);
			}else if("true".equals(request.getParameter("delete"))){
				doPost(request, response);
			}else {
				
				Veiculo veiculo = VeiculoService.getVeiculo(request, Integer.parseInt(id));
				
				String formInicio = "<form action='/registro-manutencao/veiculo?id=" + id + "' method='post'>" +
						"	<table align='center'>"+
						"		<tr>"+
						"			<td align='right'>"+
						"				Descrição:"+
						"			</td>" +
						"			<td align='left'>"+
						"				<input required type='text' name='descricao' value='" + veiculo.getDescricao() + "'/>" +
						"			</td>" +
						"		</tr>" +
						"		<tr>"+
						"			<td align='right'>"+
						"				Placa:"+
						"			</td>" +
						"			<td align='left'>"+
						"				<input required type='text' name='placa' value='" + veiculo.getPlaca() + "'/>" +
						"			</td>" +
						"		</tr>" +
						"		<tr>"+
						"			<td align='right'>"+
						"				Ano:"+
						"			</td>" +
						"			<td align='left'>"+
						"				<input required type='number' min='0' name='ano' value='" + veiculo.getAno() + "'/>" +
						"			</td>" +
						"		</tr>" +
						"		<tr>"+
						"			<td align='right'>"+
						"				Tipo de Veículo:"+
						"			</td>" +
						"			<td align='left'>"+
						"				<select name='tipo'>";
	        	
				String opcoes = "";
	        	for(VeiculoTipo tipo : VeiculoTipo.values()) {
	        		
	        		String selecionado = "";
	        		
	        		if(tipo == veiculo.getTipo()) {
	        			selecionado = "selected='selected'";
	        		}
	        		
	        		opcoes += "<option value='" + tipo + "'" + selecionado + ">" + tipo.getDescricao() + "</option>";
	        	}
	        	
	        	String formFim = "		</select> " +  
						"			</td>" +
						"		</tr>" +
						"		<tr>"+
						"			<td align='right'>"+
						"				<a href='/registro-manutencao/veiculo?id=" + id + "&delete=true" + "' class='button buttonred'>Excluir</a>"+
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
			
	        List<Veiculo> veiculos = VeiculoService.getVeiculos(request);
	        if(veiculos.size() == 0) {
	        	defaultPage(response, "Listagem de Veículos",
	        			"<p>Sem veículos cadastrados.</p><a href='?id=new' class='button buttongreen'>Novo Veículo</a>"
	        			);
	        }else {
	        	
	        	String cabecalho = 
	        			"<a href='?id=new' class='button buttongreen'>Novo Veículo</a><br /><br />" + 
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
						"		<th>"+
						"			" +
						"		</th>" +
						"	</tr>";
	        	
	        	
	        	String listagem = "";
	        	
	        	int i = 0;
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
							"		<td>"+
							"			<a href='?id=" + i + "'>Editar</a>" +
							"		</td>" +
							"	</tr>";
	        		i++;
	        	}
	        	
	        	
	        	defaultPage(response, "Listagem de Veículos", cabecalho + listagem + "</table>");
	        }
	        
		}
		
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		
		if("new".equals(id)) {
			Veiculo novo = new Veiculo(Integer.parseInt(request.getParameter("ano")), request.getParameter("placa"), VeiculoTipo.valueOf(request.getParameter("tipo")), request.getParameter("descricao"));
			VeiculoService.addVeiculo(request, novo);
		}else {
			
			if("true".equals(request.getParameter("delete"))) {
				VeiculoService.deleteVeiculo(request, Integer.parseInt(id));
			}else {
				Veiculo veiculo = VeiculoService.getVeiculo(request, Integer.parseInt(id));
				
				veiculo.setAno(Integer.parseInt(request.getParameter("ano")));
				veiculo.setDescricao(request.getParameter("descricao"));
				veiculo.setPlaca(request.getParameter("placa"));
				veiculo.setTipo(VeiculoTipo.valueOf(request.getParameter("tipo")));
			}
		}
		
		response.sendRedirect("/registro-manutencao/veiculo");
		
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
        		"  		<a class='active' href='/registro-manutencao/veiculo'>Veículos</a>" + 
        		"  		<a href='/registro-manutencao/manutencao'>Manutenções</a>" + 
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
