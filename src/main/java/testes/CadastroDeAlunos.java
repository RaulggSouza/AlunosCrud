package testes;

import dao.AlunoDAO;
import jakarta.persistence.NoResultException;
import modelo.Aluno;
import util.JPAUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class CadastroDeAlunos {
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        Scanner sc = new Scanner(System.in);

        int opt;
        do {
            menu();
            opt = Integer.parseInt(sc.nextLine());
            if (opt < 1 || opt > 6) {
                System.out.println("Digite uma opção válida");
                continue;
            }
            switch (opt) {
                case 1:
                    cadastrarAluno(sc);
                    break;
                case 2:
                    removerAluno(sc);
                    break;
                case 3:
                    atualizarAluno(sc);
                    break;
                case 4:
                    buscarPorNome(sc);
                    break;
                case 5:
                    buscarTodosAlunos();
                    break;
            }
        } while (opt != 6);
        System.out.println("Thanks for using the System!");
        sc.close();
    }

    private static void menu() {
        System.out.println("** CADASTRO DE ALUNOS **\n");
        System.out.println("1- Cadastrar aluno");
        System.out.println("2- Excluir aluno");
        System.out.println("3- Alterar aluno");
        System.out.println("4- Buscar aluno pelo nome");
        System.out.println("5- Listar alunos (com status de aprovação)");
        System.out.println("6- FIM\n");
        System.out.print("Digite a opção desejada: ");

    }

    private static void cadastrarAluno(Scanner sc){
        Aluno a = new Aluno();
        var em = JPAUtil.getEntityManager();
        AlunoDAO dao = new AlunoDAO(em);

        System.out.println("CADASTRAR ALUNO:");
        getNewData(sc, a);

        em.getTransaction().begin();
        dao.create(a);
        em.getTransaction().commit();
        em.close();
    }

    private static void getNewData(Scanner sc, Aluno a) {
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
        a.setNome(name);
        a.setRa(ra);
        a.setEmail(email);
        a.setNota1(nota1);
        a.setNota2(nota2);
        a.setNota3(nota3);
    }

    private static void buscarPorNome(Scanner sc){
        var em = JPAUtil.getEntityManager();
        AlunoDAO dao = new AlunoDAO(em);

        System.out.println("CONSULTAR ALUNO:");
        System.out.print("Digite o nome do aluno: ");
        String name = sc.nextLine();

        try {
            Aluno aluno = dao.findByName(name);
            showAluno(aluno);
        }catch (NoResultException e){
            System.out.println("\nAluno não encontrado!");
        }
    }

    private static void showAluno(Aluno aluno) {
        System.out.println("Dados do aluno:");
        System.out.println("Nome: "+ aluno.getNome());
        System.out.println("Email: " + aluno.getEmail());
        System.out.println("RA: " + aluno.getRa());
        System.out.println("Notas: " + aluno.getNota1() + " - " + aluno.getNota2() + " - " + aluno.getNota3());
    }

    private static void buscarTodosAlunos() {
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

    private static void removerAluno(Scanner sc){
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

    private static void atualizarAluno(Scanner sc){
        var em = JPAUtil.getEntityManager();
        AlunoDAO dao = new AlunoDAO(em);

        System.out.println("ALTERAR ALUNO:");
        System.out.print("Digite o nome: ");
        String name = sc.nextLine();

        try {
            Aluno aluno = dao.findByName(name);
            showAluno(aluno);
            System.out.println("\nNOVOS DADOS:");
            em.getTransaction().begin();
            getNewData(sc, aluno);
            em.getTransaction().commit();
            System.out.println("Aluno atualizado com sucesso!");
        }catch (NoResultException e){
            System.out.println("\nAluno não encontrado!");
        }
        em.close();
    }
}
