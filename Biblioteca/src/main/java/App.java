import dominio.Usuario;
import dominio.Livro;
import dominio.Emprestimo;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import dao.UsuarioDao;
import dao.EmprestimoDao;
import dao.LivroDao;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UsuarioDao usuarioDao = new UsuarioDao();

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n=== Biblioteca do Cirilo ===");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Listar Usuários");
            System.out.println("3. Atualizar Usuário");
            System.out.println("4. Excluir Usuário");
            System.out.println("5. Cadastrar Livro");
            System.out.println("6. Realizar Empréstimo");
            System.out.println("7. Listar Livros");
            System.out.println("8. Listar Empréstimos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1 -> cadastrarUsuario(scanner);
                case 2 -> listarUsuarios();
                case 3 -> atualizarUsuario(scanner);
                case 4 -> excluirUsuario(scanner);
                case 5 -> cadastrarLivro(scanner);
                case 6 -> realizarEmprestimo(scanner);
                case 7 -> listarLivros();
                case 8 -> listarEmprestimos();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 0);
        scanner.close();
    }

    // Cadastro de usuário
    private static void cadastrarUsuario(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        Usuario usuario = new Usuario(nome, cpf, email);
        usuarioDao.salvar(usuario);
        System.out.println("Usuário cadastrado com êxito!");
    }

    // Listagem de usuários
    private static void listarUsuarios() {
        var usuarios = usuarioDao.listar();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            System.out.println("\n=== Lista de Usuários ===");
            for (Usuario usuario : usuarios) {
                System.out.println(
                        "Nome: " + usuario.getNome() + ", CPF: " + usuario.getCPF() + ", Email: " + usuario.getEmail());
            }
        }
    }

    // Atualização de usuário
    private static void atualizarUsuario(Scanner scanner) {
        System.out.print("Digite o CPF do usuário que deseja atualizar: ");
        String cpf = scanner.nextLine();
        System.out.print("Novo Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo Email: ");
        String email = scanner.nextLine();
        Usuario usuario = new Usuario(nome, cpf, email);
        usuarioDao.atualizar(usuario);
    }

    // Exclusão de usuário
    private static void excluirUsuario(Scanner scanner) {
        System.out.print("Digite o CPF do usuário que deseja excluir: ");
        String cpf = scanner.nextLine();
        usuarioDao.excluir(cpf);
    }

    // Cadastro de livro
    private static void cadastrarLivro(Scanner scanner) {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Edição: ");
        String ed = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        Livro livro = new Livro(autor, titulo, ed, ano);
        LivroDao livroDao = new LivroDao();
        livroDao.salvar(livro);
        System.out.println("Livro cadastrado com êxito!");
    }

    // Realizar empréstimo
    private static void realizarEmprestimo(Scanner scanner) {
        System.out.print("Digite o id do usuario: ");
        Integer usuarioId = scanner.nextInt();
        System.out.print("Digite o id do livro: ");
        Integer livroId = scanner.nextInt();
        Emprestimo emprestimo = new Emprestimo(usuarioId, livroId, LocalDate.now(), LocalDate.now().plusDays(7));
        EmprestimoDao emprestimoDao = new EmprestimoDao();
        emprestimoDao.salvar(emprestimo);
        System.out.println("Empréstimo realizado com êxito!");
    }

    // Listagem de livros
    private static void listarLivros() {
        new LivroDao().listarTodos();

    }

    // Listagem de empréstimos
    private static void listarEmprestimos() {
        EmprestimoDao emprestimoDao = new EmprestimoDao();
        List<Emprestimo> emprestimos = emprestimoDao.buscarTodos();

        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo encontrado.");
        } else {
            System.out.println("\n=== Lista de Empréstimos ===");
            for (Emprestimo emprestimo : emprestimos) {
                System.out.println("Usuário ID: " + emprestimo.getUsuarioId() +
                        " | Livro ID: " + emprestimo.getLivroId() +
                        " | Data Empréstimo: " + emprestimo.getDataEmprestimo() +
                        " | Data Devolução: " + emprestimo.getDataDevolucao());
            }
        }
    }

}