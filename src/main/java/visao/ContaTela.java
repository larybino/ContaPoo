package visao;

import java.util.Date;

import controle.ClienteControle;
import controle.ContaControle;
import entidade.Cliente;
import entidade.Conta;
import entidade.TipoConta;

public class ContaTela {
    public static void main(String[] args) {
        ClienteControle controleCliente = new ClienteControle();
        Cliente cliente = controleCliente.buscarPorId(2L);
        ContaControle controle = new ContaControle();
        Conta conta = new Conta();
        conta.setDataAbertura(new Date());
        conta.setTipoConta(TipoConta.CONTA_CORRENTE);
        conta.setCliente(cliente);
        controle.adicionarConta(cliente.getId());
        controle.inserir(conta);
    }
}
