import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PacienteRepository pacienteRepository = new PacienteRepository();
        PacienteService pacienteService = new PacienteService(pacienteRepository);

        boolean running = true;

        while (running) {
            System.out.println("==== Sistema de Gerenciamento de Pacientes ====");
            System.out.println("1. Cadastrar Paciente");
            System.out.println("2. Buscar Paciente por ID");
            System.out.println("3. Atualizar Paciente");
            System.out.println("4. Remover Paciente");
            System.out.println("5. Listar todos os Pacientes");
            System.out.println("6. Dar Baixa no Paciente");
            System.out.println("7. Marcar Paciente como Falecido");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // Consumir nova linha
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    scanner.nextLine();  // Consumir nova linha
                    System.out.print("Status: ");
                    String status = scanner.nextLine();
                    pacienteService.cadastrarPaciente(id, nome, idade, status);

                    perguntarRetornoMenu(scanner);
                    break;

                case 2:
                    System.out.print("Digite o ID do paciente: ");
                    int idBusca = scanner.nextInt();
                    Optional<Paciente> pacienteOptional = pacienteService.buscarPaciente(idBusca);

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

                    perguntarRetornoMenu(scanner);
                    break;

                case 3:
                    System.out.print("ID: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nome: ");
                    String nomeAtualizado = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idadeAtualizada = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Status: ");
                    String statusAtualizado = scanner.nextLine();
                    pacienteService.atualizarPaciente(idAtualizar, nomeAtualizado, idadeAtualizada, statusAtualizado);

                    perguntarRetornoMenu(scanner);
                    break;

                case 4:
                    System.out.print("Digite o ID do paciente a remover: ");
                    int idRemover = scanner.nextInt();
                    pacienteService.removerPaciente(idRemover);

                    perguntarRetornoMenu(scanner);
                    break;

                case 5:
                    System.out.println("Lista de Pacientes:");
                    pacienteService.listarPacientes().forEach(p -> System.out.println(p.getNome()));

                    perguntarRetornoMenu(scanner);
                    break;

                case 6:
                    System.out.print("Digite o ID do paciente para dar baixa: ");
                    int idBaixa = scanner.nextInt();
                    pacienteService.darBaixaPaciente(idBaixa);

                    perguntarRetornoMenu(scanner);
                    break;

                case 7:
                    System.out.print("Digite o ID do paciente falecido: ");
                    int idFalecido = scanner.nextInt();
                    pacienteService.marcarPacienteComoFalecido(idFalecido);

                    perguntarRetornoMenu(scanner);
                    break;

                case 8:
                    running = false;
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }

        scanner.close();
    }

    // Método para perguntar se o usuário deseja retornar ao menu principal
    public static void perguntarRetornoMenu(Scanner scanner) {
        System.out.print("Deseja retornar ao menu principal? (sim/não): ");
        String resposta = scanner.next();

        if (resposta.equalsIgnoreCase("não")) {
            // Se a resposta for "não", esperar 30 segundos
            try {
                System.out.println("Esperando 30 segundos antes de retornar...");
                Thread.sleep(30000); // Espera de 30 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (!resposta.equalsIgnoreCase("sim")) {
            // Se a resposta for inválida, perguntar novamente
            System.out.println("Resposta inválida. Por favor, responda 'sim' ou 'não'.");
            perguntarRetornoMenu(scanner);
        }
    }
}