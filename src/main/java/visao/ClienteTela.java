package visao;


import controle.ClienteControle;
import entidade.Cliente;

public class ClienteTela {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        ClienteControle controle = new ClienteControle();
        cliente.setNomeCorrentista("Fulano");
        cliente.setCpfCorrentista("03227481510");
        controle.inserir(cliente);
    }
    
}
