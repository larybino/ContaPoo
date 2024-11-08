package dao;
import entidade.Conta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ContaDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("bancoPU");

    public Conta inserir(Conta conta) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(conta);
        em.getTransaction().commit();
        return conta;
    }

    public Conta alterar(Conta conta) {
        Conta contaBanco = null;
        if (conta.getId() != null) {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            contaBanco = buscarPorId(conta.getId());

            if (contaBanco != null) {
                em.merge(contaBanco);
            }

            em.getTransaction().commit();
            em.close();
        }
        return contaBanco;
    }

    public void excluir(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Conta conta = em.find(Conta.class, id);
        if (conta != null) {
            em.remove(conta);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Conta> listarTodos() {
        EntityManager em = emf.createEntityManager();
        // hql: hibernate query language
        List<Conta> movimentacaoes = em.createQuery("from Conta", Conta.class).getResultList();
        em.close();
        return movimentacaoes;
    }
    // buscar todas as movimentacaoes de acordo com o CPF
    // buscar todas as movimentacaoes de acordo com o tipo da transação


    public Conta buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Conta conta = em.find(Conta.class, id);
        em.close();
        return conta;
    }

    public int contarOperacoesPorDia(Long id) {
        EntityManager em = emf.createEntityManager();
        Long count = em.createQuery(
            "SELECT COUNT(m) FROM Movimentacao m WHERE m.conta.id = :id_conta AND DATE(m.dataTransacao) = CURRENT_DATE", Long.class)
            .setParameter("id_conta", id)
            .getSingleResult();
        em.close();
        return count.intValue();
    }
    

    public Double calcularSaldo(Long id) {
        EntityManager em = emf.createEntityManager();
        return em.createQuery(
            "SELECT COALESCE(SUM(m.valorOperacao), 0.0) FROM Movimentacao m WHERE m.conta.id = :id_conta", Double.class)
            .setParameter("id_conta", id)
            .getSingleResult();
    }
    
}
