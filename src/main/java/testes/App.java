package testes;

import dao.AlunoDAO;
import jakarta.persistence.NoResultException;
import modelo.Aluno;
import persistence.Atualizar;
import persistence.Buscar;
import persistence.Cadastrar;
import persistence.Remover;
import util.JPAUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class App {
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
                    Cadastrar.cadastrarAluno(sc);
                    break;
                case 2:
                    Remover.removerAluno(sc);
                    break;
                case 3:
                    Atualizar.atualizarAluno(sc);
                    break;
                case 4:
                    Buscar.buscarPorNome(sc);
                    break;
                case 5:
                    Buscar.buscarTodosAlunos();
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
}
