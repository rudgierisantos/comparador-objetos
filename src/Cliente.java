

import java.util.List;

import json.GravaLogCampo;

public class Cliente {
	@GravaLogCampo(nome = "Nome do cliente")
	private String nome;

	@GravaLogCampo(nome = "Telefones")
	private List<String> telefones;

	@GravaLogCampo(nome = "Pedidos")
	private List<Pedido> pedidos;
	
	private TipoCliente tipoCliente;
	
	@GravaLogCampo(nome = "Tipo cliente")
	private String descricaoTipoCliente;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
		setDescricaoTipoCliente(tipoCliente);
	}
	
	public void setDescricaoTipoCliente(TipoCliente tipoCliente) {
		switch (tipoCliente) {
		case PESSOA_FISICA:
			descricaoTipoCliente = "Pessoa fisica";
			break;
		case PESSOA_JURIDICA:
			descricaoTipoCliente = "Pessoa Juridica";
			break;
		default:
			break;
		}
	}

	public String getDescricaoTipoCliente() {
		return descricaoTipoCliente;
	}
}
