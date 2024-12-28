package dominio;

public class Livro {
    private String autor;
    private String titulo;
    private String ed;
    private int ano;
    private boolean emprestado;

    public Livro(String autor, String titulo, String ed, int ano) {
        this.autor = autor;
        this.titulo = titulo;
        this.ed = ed;
        this.ano = ano;
        this.emprestado = false;

    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean getEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }
}
