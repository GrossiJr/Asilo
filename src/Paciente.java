import java.io.Serializable;

public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L; // Identificador de versão de serialização

    private int id;
    private String nome;
    private int idade;
    private String status;

    // Construtor
    public Paciente(int id, String nome, int idade, String status) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.status = status;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Método para dar baixa no paciente
    public void darBaixa() {
        this.status = "Baixa";
    }

    // Método para marcar o paciente como falecido
    public void marcarComoFalecido() {
        this.status = "Falecido";
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", status='" + status + '\'' +
                '}';
    }
}