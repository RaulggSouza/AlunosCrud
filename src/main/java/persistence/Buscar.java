package persistence;

import dao.AlunoDAO;
import jakarta.persistence.NoResultException;
import modelo.Aluno;
import util.JPAUtil;

import java.util.List;
import java.util.Scanner;

public class Buscar {
    private Buscar(){}

    public static void buscarPorNome(Scanner sc){
        var em = JPAUtil.getEntityManager();
        AlunoDAO dao = new AlunoDAO(em);

        System.out.println("CONSULTAR ALUNO:");
        System.out.print("Digite o nome do aluno: ");
        String name = sc.nextLine();

        try {
            Aluno aluno = dao.findByName(name);
            aluno.showAluno();
        }catch (NoResultException e){
            System.out.println("\nAluno não encontrado!");
        }
    }

    public static void buscarTodosAlunos() {
        var em = JPAUtil.getEntityManager();
        AlunoDAO dao = new AlunoDAO(em);

        List<Aluno> allStudents = dao.findAll();
        System.out.println("Exibindo todos os alunos:");
        for (Aluno aluno : allStudents) {
            System.out.println("\nNome: " + aluno.getNome());
            System.out.println("Email: " + aluno.getEmail());
            System.out.println("RA: " + aluno.getRa());
            System.out.println("Notas: " + aluno.getNota1() + " - " + aluno.getNota2() + " - " + aluno.getNota3());
            double media = (aluno.getNota1().doubleValue() + aluno.getNota2().doubleValue() + aluno.getNota3().doubleValue()) / 3.0;
            System.out.println("Media: " + media);
            String situacao;
            if (media < 4.0) situacao = "Reprovado";
            else if (media < 6) situacao = "Recuperacao";
            else situacao = "Aprovado";
            System.out.println("Situacao: " + situacao);
        }
    }

}
