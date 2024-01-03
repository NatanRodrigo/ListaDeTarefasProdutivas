import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static Usuario usuarioLogado = null;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<Usuario> usuarios = new ArrayList<>();


        boolean rodando = true;
        while(rodando) {
            System.out.println("=== CADASTRO E LOGIN ===");
            System.out.println("[1] Criar um usúario");
            System.out.println("[2] Entrar com usúario existente");
            System.out.println("[3] Sair");
            System.out.print("Digite o que deseja fazer!");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1": {
                    System.out.println("=== CADASTRO ===");
                    System.out.print("Digite seu nome: ");
                    String name = scanner.nextLine();

                    Usuario u = new Usuario();
                    u.setName (name);

                    ArrayList<Tarefa> tarefas = new ArrayList<>();
                    u.setTarefas(tarefas);

                    usuarios.add(u);
                    System.out.println("-------> Usuário  cadastrado com sucesso!");

                    break;
                }
                case "2": {
                    System.out.println("=== LOGIN ===");
                    String name = scanner.nextLine();


                    boolean longinSucesso = false;
                    for (Usuario u : usuarios) {
                        String uName = u.getName();

                        boolean nameConfere = name.equals(uName);

                        if (nameConfere) {
                            longinSucesso = true;
                            usuarioLogado = u;
                            break;
                        }
                    }

                    if (!longinSucesso) {
                        System.out.println("-------> Esse usuário não possui cadastro");
                    } else {
                        System.out.println("-------> Login feito com sucesso");
                        homePage();
                    }

                    break;
                }
                case "3": {
                    rodando  = false;
                    System.out.println("Encerrando.....");
                    break;
                }
                default: {
                    break;
                }
            }
        }
        System.out.println("Obrigado por usar nosso progama....");
    }

    public static void homePage() {
        boolean rodando = true;
        while (rodando) {

            System.out.println("=== PAGINA DO USUÁRIO ===");
            System.out.println("[1] Mostrar tarefas");
            System.out.println("[2] Mostrar tarefas finalizadas");
            System.out.println("[3] Mostrar tarefas pendentes");
            System.out.println("[4] Adicionar tarefa");
            System.out.println("[5] Remover tarefa");
            System.out.println("[6] Finalizar tarefa");
            System.out.println("[7] Logout");
            System.out.print("Digite a opção desejada: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1": {
                    System.out.println("=== TAREFAS ===");
                    ArrayList<Tarefa> lista = usuarioLogado.getTarefas();

                    if(lista.isEmpty()) {
                        System.out.println("[Este usuário não possui tarefas]");
                    }

                    for (int i = 0; i < lista.size(); i++) {
                        Tarefa t = lista.get(i);
                        System.out.println("Tarefa " + i);
                        System.out.println("\tDescrição: " + t.getDescricao());
                        System.out.println("\tConcluido: " + t.isConcluido());
                    }
                    break;
                }
                case "2": {
                    System.out.println("=== TAREFAS CONCLUIDAS ===");

                    ArrayList<Tarefa> lista = usuarioLogado.getTarefas();
                    ArrayList<Tarefa> concluidas = new ArrayList<>();

                    for (Tarefa t : lista) {
                        if (t.isConcluido()) {
                            concluidas.add(t);
                        }
                    }

                    if (concluidas.isEmpty()) {
                        System.out.println("[Este usuário não possui tarefas concluidas]");
                    }

                    for (int i = 0; i < concluidas.size(); i++ ) {
                        Tarefa t = concluidas.get(i);
                        System.out.println("Tarefa " + i );
                        System.out.println("\tDescrição: " + t.getDescricao());
                        System.out.println("\tConcluido: " + t.isConcluido());
                    }

                    break;
                }
                case "3": {
                    System.out.println("=== TAREFAS NÂO CONCLUIDAS ===");
                    ArrayList<Tarefa> lista = usuarioLogado.getTarefas();
                    ArrayList<Tarefa> naoConcluidas = new ArrayList<>();

                    for (Tarefa t : lista) {
                        if (!t.isConcluido()) {
                            naoConcluidas.add(t);
                        }

                       if (naoConcluidas.isEmpty()) {
                            System.out.println("[Não há tarefas em aberto]");
                        }

                    }

                    for (int i = 0; i < naoConcluidas.size(); i++) {
                        Tarefa t = naoConcluidas.get(i);

                        System.out.println("tarefa " + i);
                        System.out.println("\tDescrição: " + t.getDescricao());
                        System.out.println("\tConcluida: " + t.isConcluido());
                    }

                    break;
                }
                case "4": {
                    System.out.println("=== NOVA TAREFA ===");
                    System.out.print("Digite o título da tarefa");
                    String titulo = scanner.nextLine();

                    Tarefa t = new Tarefa();
                    t.setDescricao(titulo);
                    t.setConcluido(false);


                    usuarioLogado.getTarefas().add(t);
                    System.out.println("-------> Tarefa adicionada com sucesso");
                    


                    break;
                }
                case "5": {
                    System.out.println("=== CONCLUIR TAREFA ===");
                    ArrayList<Tarefa> lista = usuarioLogado.getTarefas();
                    ArrayList<Tarefa> naoConcluidas = new ArrayList<>();

                    for (Tarefa t : lista) {
                        if (!t.isConcluido()) {
                            naoConcluidas.add(t);
                        }
                    }

                    if (naoConcluidas.isEmpty()) {
                        System.out.println("[Este usuario não tem tarefas para finalizar");
                    } else {

                        for (int i = 0; i < naoConcluidas.size(); i++) {
                            Tarefa t = naoConcluidas.get(i);

                            System.out.println("[" + i + "]" + t.getDescricao());
                        }

                        System.out.print("Digite a tarefa que deseja finalizar: ");
                        int posicao = scanner.nextInt();
                        scanner.nextLine(); // limpar buffer

                        Tarefa  tarefaConcluida = lista.get(posicao);
                        tarefaConcluida.setConcluido(true);
                        System.out.println("-------> Parabéns tarefa concluida com sucesso");
                    }
                    break;
                }
                case "6": {
                    System.out.println("=== REMOVER TAREFA ===");
                    ArrayList<Tarefa> lista = usuarioLogado.getTarefas();

                    for (int i = 0; i < lista.size(); i++) {
                        Tarefa t = lista.get(i);

                        System.out.println("[" + i + "]" + t.getDescricao());
                    }

                    System.out.println("Qual tarefa deseja remover: ");
                    int posicao = scanner.nextInt();
                    scanner.nextLine(); // Limpar buffer

                    lista.remove(posicao);
                    System.out.println("-------> Tarefa removida com sucesso!");


                    break;
                }
                case "7": {
                    System.out.println("Você está saindo do progama");
                    rodando = false;
                    usuarioLogado = null;
                    break;
                }
            }


        }
    }

}
