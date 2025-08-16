package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import modelo.Aluno;

import java.util.List;

public class AlunoDAO {
    private EntityManager em;

    public AlunoDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Aluno aluno) {
        em.persist(aluno);
    }

    public Aluno findByName(String name) throws NoResultException {
        String jpql = "select a from Aluno a where a.nome = :n";
        return em.createQuery(jpql, Aluno.class).setParameter("n", name).getSingleResult();
    }

    public List<Aluno> findAll() {
        String jpql = "select a from Aluno a";
        return em.createQuery(jpql, Aluno.class).getResultList();
    }

    public void delete(String nome) throws NoResultException {
        em.remove(findByName(nome));
    }

}
