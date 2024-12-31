import dominio.Usuario;
import dominio.Livro;
import dominio.Emprestimo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Livro> livros = new ArrayList<>();
    private static List<Emprestimo> emprestimos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== Biblioteca: Nal do canal ===");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Cadastrar Livro");
            System.out.println("3. Realizar Empréstimo");
            System.out.println("4. Listar Usuários");
            System.out.println("5. Listar Livros");
            System.out.println("6. Listar Empréstimos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarUsuario(scanner);
                case 2 -> cadastrarLivro(scanner);
                case 3 -> realizarEmprestimo(scanner);
                case 4 -> listarUsuarios();
                case 5 -> listarLivros();
                case 6 -> listarEmprestimos();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    //cadastro usuario
    private static void cadastrarUsuario(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario(nome, cpf, email);
        usuarios.add(usuario);
        System.out.println("Usuário cadastrado com êxito!");
    }
    //cadastro livro
    private static void cadastrarLivro(Scanner scanner) {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Edição: ");
        String ed = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após o número

        Livro livro = new Livro(autor, titulo, ed, ano);
        livros.add(livro);
        System.out.println("Livro cadastrado com êxito!");
    }

    //emprestimo
    private static void realizarEmprestimo(Scanner scanner) {
        if (livros.isEmpty() || usuarios.isEmpty()) {
            System.out.println("Não há livros ou usuários cadastrados.");
            return;
        }

        System.out.println("Selecione o usuário:");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println(i + " - " + usuarios.get(i).getNome());
        }
        System.out.print("Escolha um usuário: ");
        int usuarioIndex = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Selecione o livro:");
        for (int i = 0; i < livros.size(); i++) {
            Livro livro = livros.get(i);
            if (!livro.getEmprestado()) {
                System.out.println(i + " - " + livro.getTitulo());
            }
        }
        System.out.print("Escolha um livro: ");
        int livroIndex = scanner.nextInt();
        scanner.nextLine();

        Livro livro = livros.get(livroIndex);
        if (livro.getEmprestado()) {
            System.out.println("O livro já está emprestado.");
            return;
        }

        livro.setEmprestado(true);
        Emprestimo emprestimo = new Emprestimo(LocalDate.now(), LocalDate.now().plusDays(14));
        emprestimos.add(emprestimo);

        System.out.println("Empréstimo realizado com êxito!");
    }

    
    private static void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            System.out.println("\n=== Lista de Usuários ===");
            usuarios.forEach(usuario -> System.out.println("Nome: " + usuario.getNome() + ", CPF: " + usuario.getCPF() + ", Email: " + usuario.getEmail()));
        }
    }

    private static void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            System.out.println("\n=== Lista de Livros ===");
            livros.forEach(livro -> System.out.println("Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor() + ", Ano: " + livro.getAno() + ", Emprestado: " + (livro.getEmprestado() ? "Sim" : "Não")));
        }
    }

    private static void listarEmprestimos() {
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo realizado.");
        } else {
            System.out.println("\n=== Lista de Empréstimos ===");
            emprestimos.forEach(System.out::println);
        }
    }
}
