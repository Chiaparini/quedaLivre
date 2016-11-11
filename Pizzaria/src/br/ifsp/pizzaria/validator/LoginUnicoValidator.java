package br.ifsp.pizzaria.validator;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import br.ifsp.pizzaria.entities.Usuario;
import br.ifsp.pizzaria.repository.UsuarioRepository;

@FacesValidator("ValidadorLoginUnico")
public class LoginUnicoValidator implements Validator{
	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		try {
			String login = (String) value;
			EntityManagerFactory factory = 
					Persistence.createEntityManagerFactory("pizzaria");
			EntityManager manager = factory.createEntityManager();
			UsuarioRepository repo = new UsuarioRepository(manager);
			List<Usuario> usuarios = new ArrayList<>();
			usuarios = repo.verificarValorLogin(login);
			if(!usuarios.isEmpty()){
				FacesMessage mensagem = new FacesMessage ("O valor para login " + login + " já existe");
				mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(mensagem);
			}
		}
		catch (ParseException e) {
		e.printStackTrace();
	} 
	
	}
}
