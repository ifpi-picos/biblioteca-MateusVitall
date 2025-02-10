package dominio;

public class Usuario {
   private String nome;
   private String cpf;
   private String email;

   public Usuario(String nome, String cpf, String email) {
      this.nome = nome;
      this.cpf = cpf;
      this.email = email;
   }

   public String getNome() {
      return nome;
   }

   // Atribui um valor a 'nome'
   public void setNome(String nome) {
      this.nome = nome;
   }

   // Retorna o valor atribuido a 'cpf'
   public String getCPF() {
      return cpf;
   }

   // Atribui um valor a 'cpf'
   public void setCPF(String cpf) {
      this.cpf = cpf;
   }

   // Retorna o valor atribuido a 'email'
   public String getEmail() {
      return email;
   }

   // Atribui um valor a 'email'
   public void setEmail(String email) {
      this.email = email;
   }

}
