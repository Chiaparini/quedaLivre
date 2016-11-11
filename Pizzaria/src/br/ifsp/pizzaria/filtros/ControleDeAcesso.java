package br.ifsp.pizzaria.filtros;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ifsp.pizzaria.entities.Usuario;

@WebFilter (servletNames={"Faces Servlet"})
public class ControleDeAcesso implements Filter{
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	Usuario usu;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req= (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		if (session.getAttribute("usuario") != null || 
				req.getRequestURI().endsWith("index.xhtml") ||
				req.getRequestURI().endsWith("CadastroCliente.xhtml") ||
				req.getRequestURI().endsWith("CadastroFuncionario.xhtml") ||
				//req.getRequestURI().endsWith(".css") ||
				req.getRequestURI().endsWith("Header.xhtml") ||
				req.getRequestURI().endsWith("template.xhtml") ||
				
				req.getRequestURI().endsWith("Login.xhtml")
				 ) {
			
			chain.doFilter(request, response);
			
		}else{
			
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect("Login.xhtml");
			
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	public void setUsu(ServletRequest request, ServletResponse response,
		FilterChain chain) throws IOException, ServletException{
		HttpServletRequest req= (HttpServletRequest) request;
		HttpSession session = req.getSession();
		this.usu = (Usuario) session.getAttribute("usuario");
	}
	
	public Usuario getUsu(){
		return this.usu;
	}
}
