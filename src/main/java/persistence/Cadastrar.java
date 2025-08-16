package persistence;

import dao.AlunoDAO;
import modelo.Aluno;
import util.JPAUtil;

import java.math.BigDecimal;
import java.util.Scanner;

public class Cadastrar {
    private Cadastrar() {}

    public static void cadastrarAluno(Scanner sc){
        var em = JPAUtil.getEntityManager();
        AlunoDAO dao = new AlunoDAO(em);

        System.out.println("CADASTRAR ALUNO:");
        Aluno a = getNewData(sc);

        em.getTransaction().begin();
        dao.create(a);
        em.getTransaction().commit();
        em.close();
    }

    public static Aluno getNewData(Scanner sc) {
        System.out.print("Digite o nome: ");
        String name = sc.nextLine();
        System.out.print("Digite o RA: ");
        String ra = sc.nextLine();
        System.out.print("Digite o email: ");
        String email = sc.nextLine();
        System.out.print("Digite a nota 1: ");
        BigDecimal nota1 = sc.nextBigDecimal();
        System.out.print("Digite a nota 2: ");
        BigDecimal nota2 = sc.nextBigDecimal();
        System.out.print("Digite a nota 3: ");
        BigDecimal nota3 = sc.nextBigDecimal();
        sc.nextLine();
        return new Aluno(name, ra, email, nota1, nota2, nota3);
    }
}