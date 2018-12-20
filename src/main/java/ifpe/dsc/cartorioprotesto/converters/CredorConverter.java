package ifpe.dsc.cartorioprotesto.converters;

import ifpe.dsc.cartorioprotesto.model.Credor;
import ifpe.dsc.cartorioprotesto.service.CredorServico;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
@FacesConverter(forClass = Credor.class)
public class CredorConverter implements Converter {

	@Inject
	private CredorServico produtoService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		Long id = Long.parseLong(value);
	      try {
	            return produtoService.getCredorPorId(id);
	        } catch (NumberFormatException e) {
	            throw new ConverterException(new FacesMessage(String.format("%s é invalido para o produto", id)), e);
	        }
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		Long id = ((Credor) value).getId();
		return (id != null) ? id.toString() : null;
	}
}
