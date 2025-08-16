package persistence;

import dao.AlunoDAO;
import jakarta.persistence.NoResultException;
import util.JPAUtil;

import java.util.Scanner;

public class Remover {
    private Remover() {}

    public static void removerAluno(Scanner sc){
        var em = JPAUtil.getEntityManager();
        AlunoDAO dao = new AlunoDAO(em);
        System.out.println("EXCLUIR ALUNO:");
        try {
            System.out.print("Digite o nome: ");
            String nome = sc.nextLine();
            em.getTransaction().begin();
            dao.delete(nome);
            em.getTransaction().commit();
            System.out.println("Aluno removido com sucesso!");
        }catch (NoResultException e){
            System.out.println("Aluno não encontrado!");
        }
    }
}
