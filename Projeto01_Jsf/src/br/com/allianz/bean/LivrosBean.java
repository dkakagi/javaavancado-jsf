package br.com.allianz.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.allianz.dao.LivrosDao;
import br.com.allianz.models.Livro;

@ManagedBean(name="beanLivro")
@RequestScoped
public class LivrosBean {
	
	private Livro livro;
	
	public LivrosBean() {
		if(livro == null) {
			livro = new Livro();
		}
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	//método de ação (action) que, quando executado, retorna o redirecionamento conforme o resultado da execução
	public String incluiLivro() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		
		try {
			new LivrosDao().IncluirLivro(livro);
			msg.setSummary("OK");
			msg.setDetail("Livro Incluído");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage("Sucesso", msg);
			return "sucesso";
		} catch (Exception e) {
			msg.setSummary("Erro:");
			msg.setDetail(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			e.printStackTrace();
			context.addMessage("erro", msg);
			return "erro";
		}
	}
	
	//Propriedade usada para apresenta a lista dos livros cadastrados
	public List<Livro> getListaLivros() throws Exception{
		return new LivrosDao().listarLivros();
	}
}
