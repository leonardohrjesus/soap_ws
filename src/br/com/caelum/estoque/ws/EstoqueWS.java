package br.com.caelum.estoque.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.caelum.estoque.modelo.item.Filtro;
import br.com.caelum.estoque.modelo.item.Filtros;
import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ListaItens;
import br.com.caelum.estoque.modelo.usuario.AutorizacaoException;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;
import br.com.caelum.estoque.modelo.usuario.Usuario;

@WebService
public class EstoqueWS {

	
	private ItemDao dao = new ItemDao();
	
	@WebMethod(operationName="todosOsItens")
	@WebResult(name = "itens")
	public ListaItens getItens(@WebParam(name= "filtos") Filtros filtros ){
		
		System.out.println("chamando getItens()");
		
		List<Filtro> listfiltros = filtros.getLista();
		List< Item> lista = dao.todosItens(listfiltros);
		
		return new ListaItens(lista);
		
	}
	
	@WebMethod(operationName="CadastrarItem")
	@WebResult(name = "item")
	public Item cadastraItem(@WebParam(name="tokenUsuario", header=true )TokenUsuario token,@WebParam(name="item")Item item) throws AutorizacaoException {
		
		System.out.println("Cadastrando Item " + item + ", Token " + token);
		
		boolean valido = new TokenDao().ehValido(token);
		
		if (!valido) {
			throw new AutorizacaoException("Token invalido");
			
		}
		this.dao.cadastrar(item);
		
		return item;
		
	}
}
