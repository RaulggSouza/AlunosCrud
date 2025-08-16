package persistence;

import dao.AlunoDAO;
import jakarta.persistence.NoResultException;
import modelo.Aluno;
import util.JPAUtil;

import java.util.Scanner;

public class Atualizar {
    private Atualizar(){}

    public static void atualizarAluno(Scanner sc){
        var em = JPAUtil.getEntityManager();
        AlunoDAO dao = new AlunoDAO(em);

        System.out.println("ALTERAR ALUNO:");
        System.out.print("Digite o nome: ");
        String name = sc.nextLine();

        try {
            Aluno aluno = dao.findByName(name);
            aluno.showAluno();
            System.out.println("\nNOVOS DADOS:");
            em.getTransaction().begin();
            update(sc, aluno);
            em.getTransaction().commit();
            System.out.println("Aluno atualizado com sucesso!");
        }catch (NoResultException e){
            System.out.println("\nAluno não encontrado!");
        }
        em.close();
    }

    private static void update(Scanner sc, Aluno aluno){
        Aluno a = Cadastrar.getNewData(sc);

        aluno.setNome(a.getNome());
        aluno.setRa(a.getRa());
        aluno.setEmail(a.getEmail());
        aluno.setNota1(a.getNota1());
        aluno.setNota2(a.getNota2());
        aluno.setNota3(a.getNota3());
    }
}
