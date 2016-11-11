package br.ifsp.pizzaria.validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;



@FacesValidator("ValidadorSenha")
@ManagedBean
public class SenhaValidator implements Validator{
	
	String confirma;
	
	public String getConfirma() {
		return confirma;
	}

	public void setConfirma(String confirma) {
		this.confirma = confirma;
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		try {
			String confirmaSenha = (String) value;
			
			
			if(!confirmaSenha.equals(confirma)){
				FacesMessage mensagem = new FacesMessage ("As senhas não correspondem");
				mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(mensagem);
			}
		}
		catch (ParseException e) {
			e.printStackTrace();
		}  
	}
}
