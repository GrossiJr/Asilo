import java.util.List;
import java.util.Optional;

public class PacienteService {
    private PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    // Método para cadastrar um novo paciente
    public void cadastrarPaciente(int id, String nome, int idade, String status) {
        Paciente paciente = new Paciente(id, nome, idade, status);
        pacienteRepository.addPaciente(paciente); // Adiciona o paciente ao repositório
        System.out.println("Paciente cadastrado com sucesso.");
    }

    // Método para buscar um paciente por ID
    public Optional<Paciente> buscarPaciente(int id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.getPacienteById(id);
        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
            System.out.println("Paciente encontrado:");
            System.out.println("ID: " + paciente.getId());
            System.out.println("Nome: " + paciente.getNome());
            System.out.println("Idade: " + paciente.getIdade());
            System.out.println("Status: " + paciente.getStatus());
        } else {
            System.out.println("Paciente não encontrado!");
        }
        return pacienteOptional; // Retorna o paciente encontrado (ou vazio se não encontrado)
    }

    // Método para atualizar as informações de um paciente
    public void atualizarPaciente(int id, String nome, int idade, String status) {
        Optional<Paciente> pacienteOptional = pacienteRepository.getPacienteById(id);
        if (pacienteOptional.isPresent()) {
            Paciente pacienteAtualizado = new Paciente(id, nome, idade, status);
            pacienteRepository.updatePaciente(pacienteAtualizado); // Atualiza o paciente no repositório
            System.out.println("Paciente atualizado com sucesso.");
        } else {
            System.out.println("Paciente não encontrado para atualização!");
        }
    }

    // Método para remover um paciente pelo ID
    public void removerPaciente(int id) {
        pacienteRepository.removePaciente(id); // Remove o paciente do repositório
        System.out.println("Paciente removido com sucesso, se existia.");
    }

    // Método para listar todos os pacientes
    public List<Paciente> listarPacientes() {
        List<Paciente> pacientes = pacienteRepository.getAllPacientes();
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
        } else {
            System.out.println("Lista de Pacientes:");
            for (Paciente paciente : pacientes) {
                System.out.println("ID: " + paciente.getId() + ", Nome: " + paciente.getNome() + ", Idade: " + paciente.getIdade() + ", Status: " + paciente.getStatus());
            }
        }
        return pacientes; // Retorna a lista de todos os pacientes
    }

    // Método para dar baixa em um paciente
    public void darBaixaPaciente(int id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.getPacienteById(id);
        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
            paciente.darBaixa(); // Atualiza o status para "Baixa"
            pacienteRepository.updatePaciente(paciente); // Atualiza o paciente no repositório
            System.out.println("Paciente com ID " + id + " deu baixa com sucesso.");
        } else {
            System.out.println("Paciente não encontrado para dar baixa!");
        }
    }

    // Método para marcar um paciente como falecido
    public void marcarPacienteComoFalecido(int id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.getPacienteById(id);
        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
            paciente.marcarComoFalecido(); // Atualiza o status para "Falecido"
            pacienteRepository.updatePaciente(paciente); // Atualiza o paciente no repositório
            System.out.println("Paciente com ID " + id + " marcado como falecido.");
        } else {
            System.out.println("Paciente não encontrado para marcar como falecido!");
        }
    }
}