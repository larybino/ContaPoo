package visao;


import controle.ClienteControle;
import entidade.Cliente;

public class ClienteTela {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        ClienteControle controle = new ClienteControle();
        cliente.setNomeCorrentista("Fulana");
        cliente.setCpfCorrentista("**********");
        controle.inserir(cliente);
    }
    
}
