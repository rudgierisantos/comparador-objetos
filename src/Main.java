

import java.util.Arrays;

import gravaLogCampo.LogCampoFactory;

public class Main {
    public static void main(String[] args) throws Exception {
        Cliente antigo = new Cliente();
        antigo.setNome("Rudgieri");
        antigo.setTelefones(Arrays.asList("1111", "2222"));
        antigo.setPedidos(Arrays.asList(new Pedido(1, "Pedido A"), new Pedido(2, "Pedido B")));
        antigo.setTipoCliente(TipoCliente.PESSOA_FISICA);

        Cliente atual = new Cliente();
        atual.setNome("Rudgieri Santos");
        atual.setTelefones(Arrays.asList("1111", "3333"));
        atual.setPedidos(Arrays.asList(new Pedido(1, "Pedido A alterado"), new Pedido(3, "Pedido C")));
        atual.setTipoCliente(TipoCliente.PESSOA_FISICA);

		System.out.println("---------------------------------------// Inclusões // -------------------------------------------------");
		String inclusoes = new LogCampoFactory().getLog(atual);
        System.out.println(inclusoes);


		System.out.println("---------------------------------------// Alterações // ------------------------------------------------");
		String alteracoes = new LogCampoFactory().getLog(antigo, atual);
        System.out.println(alteracoes);
       
    }
}