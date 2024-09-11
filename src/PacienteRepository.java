import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacienteRepository {
    private List<Paciente> pacientes;
    private final String arquivoPacientes = "pacientes.dat";

    // Construtor
    public PacienteRepository() {
        this.pacientes = new ArrayList<>();
        carregarDados(); // Carrega os dados do arquivo ao iniciar
    }

    // Adiciona um paciente à lista e salva no arquivo
    public void addPaciente(Paciente paciente) {
        pacientes.add(paciente);
        salvarDados(); // Salva os dados no arquivo após adição
    }

    // Obtém um paciente pelo ID
    public Optional<Paciente> getPacienteById(int id) {
        return pacientes.stream()
                .filter(paciente -> paciente.getId() == id)
                .findFirst();
    }

    // Remove um paciente da lista e salva no arquivo
    public void removePaciente(int id) {
        pacientes.removeIf(paciente -> paciente.getId() == id);
        salvarDados(); // Salva os dados no arquivo após remoção
    }

    // Atualiza um paciente existente e salva no arquivo
    public void updatePaciente(Paciente pacienteAtualizado) {
        Optional<Paciente> pacienteOptional = getPacienteById(pacienteAtualizado.getId());
        if (pacienteOptional.isPresent()) {
            Paciente pacienteExistente = pacienteOptional.get();
            pacienteExistente.setNome(pacienteAtualizado.getNome());
            pacienteExistente.setIdade(pacienteAtualizado.getIdade());
            pacienteExistente.setStatus(pacienteAtualizado.getStatus());
            salvarDados(); // Salva os dados no arquivo após atualização
        }
    }

    // Retorna todos os pacientes
    public List<Paciente> getAllPacientes() {
        return new ArrayList<>(pacientes);
    }

    // Salva a lista de pacientes no arquivo
    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoPacientes))) {
            oos.writeObject(pacientes);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    // Carrega a lista de pacientes do arquivo
    private void carregarDados() {
        File file = new File(arquivoPacientes);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoPacientes))) {
                @SuppressWarnings("unchecked")
                List<Paciente> pacientesCarregados = (List<Paciente>) ois.readObject();
                pacientes = pacientesCarregados != null ? pacientesCarregados : new ArrayList<>();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao carregar os dados: " + e.getMessage());
            }
        } else {
            pacientes = new ArrayList<>(); // Cria uma lista vazia se o arquivo não existir
        }
    }
}