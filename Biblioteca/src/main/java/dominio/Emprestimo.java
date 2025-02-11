package dominio;

import java.time.LocalDate;

public class Emprestimo {
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private int usuarioId;
    private int livroId;
 
    public Emprestimo(Integer livroId ,Integer usuarioId, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.usuarioId = usuarioId;
        this.livroId = livroId;
    }
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }


    public int getUsuarioId() {
        return usuarioId;
    }

    public int getLivroId() {
        return livroId;
    }
    @Override
    public String toString() {
        return "Empréstimo em: " + dataEmprestimo + ", deve devolver até: " + dataDevolucao;
    }
}
