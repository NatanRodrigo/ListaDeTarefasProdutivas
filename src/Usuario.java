import java.util.ArrayList;

public class Usuario {
    private String name;
    private ArrayList<Tarefa> tarefas;

    public String getName() {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public ArrayList<Tarefa>getTarefas(){
        return tarefas;
    }

    public void setTarefas(ArrayList<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }
}
