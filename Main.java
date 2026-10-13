import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TelaInicial ti = new TelaInicial();
        
        // Listas tipadas corretamente
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        ArrayList<BolosAniversario> listaAniversario = new ArrayList<>();
        ArrayList<Bolos> listaTradicionais = new ArrayList<>();
        
        // Inicializando administrador
        Administrador admin = new Administrador(
            "Neli Admin", "admin123", "admin@email.com", "01/01/1990",
            "000", "Rua A", "1", "Centro", "Recife", "50000-000", "Dona", 1
        );
        
        // Inicializando bolos de aniversário 
        listaAniversario.add(new BolosAniversario("Médio", 2.5, "Chocolate", "Brigadeiro Gourmet", "Ninho", 120.00, "Homem-Aranha"));
        listaAniversario.add(new BolosAniversario("Grande", 4.0, "Baunilha", "Chantilly", "Morango", 180.00, "Ben 10"));
        
        // Inicializando bolos tradicionais
        listaTradicionais.add(new Bolos("Pequeno", 1.2, "Cenoura", "Chocolate", "Sem recheio", 35.00));
        listaTradicionais.add(new Bolos("Médio", 2.0, "Fubá", "Sem cobertura", "Goiabada", 42.50));
        
        int opcaoInicio = -1;
        Cliente clienteLogado = null;

        do {
            limparTodaTela();
            System.out.println("======== LOJA DE BOLOS DA NELI ========");
            System.out.println("Digite [1] para Cadastrar Novo Cliente");
            System.out.println("Digite [2] para Fazer Login (Cliente ou Admin)");
            System.out.println("Digite [0] para Sair do Sistema");
            System.out.println("========================================");
            System.out.print("Opção: ");
            opcaoInicio = scanner.nextInt();
            scanner.nextLine();
            
            if (opcaoInicio == 1) {
                limparTodaTela();
                boolean c = false;
                ti.pedirCadastro();
                continuarAT(scanner);
                
                do {
                    System.out.println("=============== Cadastro =============");
                    System.out.print("Nome: ");
                    c = ti.setNome(scanner.nextLine());
                    if (c) {
                        continuarAT(scanner);
                    }
                } while (c);
                
                do {
                    System.out.print("Senha: ");
                    c = ti.setSenha(scanner.nextLine());
                    if (c) {
                        continuarA1(scanner);
                        LimparLinhaAnterior();
                    }
                } while (c);
                
                do {
                    System.out.print("Email: ");
                    c = ti.setEmail(scanner.nextLine());
                    if (c) {
                        continuarA1(scanner);
                    }
                } while (c);
                
                System.out.print("Data de nascimento: ");
                ti.setDataDeNascimento(scanner.nextLine());
                System.out.print("CPF: ");
                ti.setCPF(scanner.nextLine());
                System.out.print("Rua: ");
                ti.setRua(scanner.nextLine());
                System.out.print("Numero: ");
                ti.setNumero(scanner.nextLine());
                System.out.print("Bairro: ");
                ti.setBairro(scanner.nextLine());
                System.out.print("Cidade: ");
                ti.setCidade(scanner.nextLine());
                System.out.print("Cep: ");
                ti.setCep(scanner.nextLine());
                System.out.println("====================================");
                
                continuarAT(scanner);
                
                clienteLogado = new Cliente(
                    ti.getNome(), ti.getSenha(), ti.getEmail(), ti.getDataDeNascimento(),
                    ti.getCPF(), ti.getRua(), ti.getNumero(), ti.getBairro(),
                    ti.getCidade(), ti.getCep()
                );
                
                listaClientes.add(clienteLogado);
                System.out.println(clienteLogado.getNome() + " você foi cadastrado!");
                continuarAT(scanner);
                
                menuLoja(scanner, clienteLogado, listaAniversario, listaTradicionais, listaPedidos);
                
            } else if (opcaoInicio == 2) {
                limparTodaTela();
                System.out.println("============== LOGIN ==============");
                System.out.print("Nome de Usuário: ");
                String nomeLogin = scanner.nextLine();
                System.out.print("CPF: ");
                String cpfLogin = scanner.nextLine();
                
                if (admin.getNome().equals(nomeLogin) && admin.getCPF().equals(cpfLogin)) {
                    menuAdministrador(scanner, admin, listaPedidos, listaClientes);
                } else {
                    clienteLogado = null;
                    for (int i = 0; i < listaClientes.size(); i++) {
                        Cliente cli = listaClientes.get(i);
                        if (cli.getNome().equals(nomeLogin) && cli.getCPF().equals(cpfLogin)) {
                            clienteLogado = cli;
                        }
                    }
                    if (clienteLogado != null) {
                        System.out.println("\nLogin realizado com sucesso! Bem-vindo(a) " + clienteLogado.getNome());
                        continuarAT(scanner);
                        menuLoja(scanner, clienteLogado, listaAniversario, listaTradicionais, listaPedidos);
                    } else {
                        System.out.println("\nNome de usuário ou CPF incorretos / não cadastrados!");
                        continuarAT(scanner);
                    }
                }
            }
        } while (opcaoInicio != 0);
        
        scanner.close();
    }

    public static void menuLoja(Scanner scanner, Cliente cliente,
                                ArrayList<BolosAniversario> listaAniversario,
                                ArrayList<Bolos> listaTradicionais,
                                ArrayList<Pedido> listaPedidos) {
        int opcaoBolo;
        do {
            limparTodaTela();
            System.out.println("Cliente ativo: " + cliente.getNome());
            System.out.println("Bem vindo a loja virtual escolha qual");
            System.out.println("tipo de bolo você deseja visualizar: ");
            System.out.println();
            System.out.println("Digite [1] para bolos de aniversário: ");
            imagemBoloAni();
            System.out.println("Digite [2] para bolos tradicionais: ");
            imagemBolo();
            System.out.println("Digite [0] para Sair da Conta");
            
            opcaoBolo = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (opcaoBolo) {
                case 1:
                    limparTodaTela();
                    for (int i = 0; i < listaAniversario.size(); i++) {
                        System.out.println();
                        System.out.println("--- Bolo " + (i + 1) + " ---");
                        imagemBoloAni();
                        listaAniversario.get(i).informacoesBolo();
                    }
                    System.out.printf("\nDigite um numero correspondente a um bolo para pedir:\n");
                    System.out.printf("Ou digite 0(zero) para voltar.\n");
                    int escAni = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (escAni > 0 && escAni <= listaAniversario.size()) {
                        BolosAniversario b = listaAniversario.get(escAni - 1);
                        System.out.print("Confirmar pedido do bolo de " + b.getSabor() + "? [1-Sim / 2-Não]: ");
                        int confirm = scanner.nextInt();
                        scanner.nextLine();
                        if (confirm == 1) {
                            Pedido p = new Pedido(cliente);
                            p.adicionarBolo(b);
                            listaPedidos.add(p);
                            System.out.println("\nPedido feito com sucesso!");
                        }
                        continuarAT(scanner);
                    }
                    opcaoBolo = 0; 
                    break;
                case 2:
                    limparTodaTela();
                    for (int i = 0; i < listaTradicionais.size(); i++) {
                        System.out.println();
                        System.out.println("--- Bolo " + (i + 1) + " ---");
                        imagemBolo();
                        listaTradicionais.get(i).informacoesBolo();
                    }
                    System.out.printf("\nDigite um numero correspondente a um bolo para pedir:\n");
                    System.out.printf("Ou digite 0(zero) para voltar.\n");
                    int escTrad = scanner.nextInt();
                    scanner.nextLine();
                    if (escTrad > 0 && escTrad <= listaTradicionais.size()) {
                        Bolos b = listaTradicionais.get(escTrad - 1);
                        System.out.print("Confirmar pedido do bolo de " + b.getSabor() + "? [1-Sim / 2-Não]: ");
                        int confirm = scanner.nextInt();
                        scanner.nextLine();
                        if (confirm == 1) {
                            Pedido p = new Pedido(cliente);
                            p.adicionarBolo(b);
                            listaPedidos.add(p);
                            System.out.println("\nPedido feito com sucesso!");
                        }
                        continuarAT(scanner);
                    }
                    opcaoBolo = 0;
                    break;
                case 0:
                    limparTodaTela();
                    System.out.println("Saindo...");
                    continuarAT(scanner);
                    opcaoBolo = 100;
                    break;    
            }
        } while (opcaoBolo == 0);
    }

    public static void menuAdministrador(Scanner scanner, Administrador admin,
                                         ArrayList<Pedido> listaPedidos,
                                         ArrayList<Cliente> listaClientes) {
        int opcaoAdmin;
        do {
            limparTodaTela();
            System.out.println("============ PAINEL DO ADMINISTRADOR ============");
            System.out.println("Bem-vindo, " + admin.getNome());
            System.out.println();
            System.out.println("Digite [1] para ver Relatório de Pedidos de Clientes");
            System.out.println("Digite [2] para ver Clientes Cadastrados");
            System.out.println("Digite [0] para Sair do Painel Admin");
            System.out.print("Opção: ");
            opcaoAdmin = scanner.nextInt();
            scanner.nextLine();
            
            if (opcaoAdmin == 1) {
                limparTodaTela();
                System.out.println("=================================================");
                System.out.println("            RELATÓRIO DETALHADO DE PEDIDOS       ");
                System.out.println("=================================================");
                if (listaPedidos.isEmpty()) {
                    System.out.println("\nNenhum pedido realizado ainda.");
                } else {
                    for (int i = 0; i < listaPedidos.size(); i++) {
                        Pedido p = listaPedidos.get(i);
                        System.out.println("\nPEDIDO #" + p.getIdPedido());
                        System.out.println("Cliente: " + p.getCliente().getNome() + " (CPF: " + p.getCliente().getCPF() + ")");
                        System.out.println("--- DETALHES DO BOLO COMPRADO ---");
                        p.getItensComprados().get(0).informacoesBolo();
                        System.out.println("TOTAL DO PEDIDO: R$ " + p.getValorTotal());
                        System.out.println("-------------------------------------------------");
                    }
                }
                System.out.println("\n=================================================");
                continuarAT(scanner);
            } else if (opcaoAdmin == 2) {
                limparTodaTela();
                System.out.println("=== CLIENTES CADASTRADOS ===");
                for (int i = 0; i < listaClientes.size(); i++) {
                    System.out.println((i + 1) + ". " + listaClientes.get(i).getNome() + " (CPF: " + listaClientes.get(i).getCPF() + ")");
                }
                continuarAT(scanner);
            }
        } while (opcaoAdmin != 0);
    }

    public static void limparTodaTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush(); 
    }

    public static void LimparLinhaAnterior() {
        System.out.print("\033[1A\033[2K");
        System.out.flush();
    }

    public static void continuarAT(Scanner scanner) {
        System.out.println();
        System.out.println("Aperte enter para continuar: ");
        scanner.nextLine();
        limparTodaTela();
    }

    public static void continuarA1(Scanner scanner) {
        System.out.println();
        System.out.print("Aperte enter para continuar: ");
        scanner.nextLine();
        LimparLinhaAnterior(); 
        LimparLinhaAnterior(); 
        LimparLinhaAnterior(); 
        LimparLinhaAnterior(); 
    }

    public static void imagemBoloAni() {
        System.out.println("        # ");
        System.out.println("     __|___");
        System.out.println("    |     |");
        System.out.println("    |~ | ~|");
        System.out.println("    |  |  |");
        System.out.println("  __|_____|__");
        System.out.println(" |           |");
        System.out.println(" | Parabéns! |");
        System.out.println(" |           |");
        System.out.println(" |___________|");
    }

    public static void imagemBolo() {
        System.out.println("    ___________  ");
        System.out.println("   |~~~~~~~~~~~| ");
        System.out.println("   |  B O L O  | ");
        System.out.println("   |___________| ");
    }
}

// ================= CLASSE TELA INICIAL =================
class TelaInicial {
    private String nome;
    private String senha;
    private String email;
    private String dataDeNascimento;
    private String cpf;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String cep;

    public TelaInicial() {}

    public TelaInicial(String nome, String senha, String email, String dataDeNascimento, 
                       String cpf, String rua, String numero, String bairro, 
                       String cidade, String cep) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.dataDeNascimento = dataDeNascimento;
        this.cpf = cpf;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
    }

    public void pedirCadastro() {
        System.out.println("====================================");
        System.out.println("   BEM-VINDO À LOJA DE BOLOS DA NELI");
        System.out.println("   Por favor, preencha o cadastro.");
        System.out.println("====================================");
    }

    public boolean setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome inválido! Não pode ficar em branco.");
            return true;
        }
        this.nome = nome;
        return false;
    }

    public boolean setSenha(String senha) {
        if (senha == null || senha.length() < 4) {
            System.out.println("Senha inválida! Digite pelo menos 4 caracteres.");
            return true;
        }
        this.senha = senha;
        return false;
    }

    public boolean setEmail(String email) {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            System.out.println("E-mail inválido! Certifique-se de incluir '@' e '.com'.");
            return true;
        }
        this.email = email;
        return false;
    }

    public void setDataDeNascimento(String dataDeNascimento) { this.dataDeNascimento = dataDeNascimento; }
    public void setCPF(String cpf) { this.cpf = cpf; }
    public void setRua(String rua) { this.rua = rua; }
    public void setNumero(String numero) { this.numero = numero; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public void setCep(String cep) { this.cep = cep; }

    public String getNome() { return nome; }
    public String getSenha() { return senha; }
    public String getEmail() { return email; }
    public String getDataDeNascimento() { return dataDeNascimento; }
    public String getCPF() { return cpf; }
    public String getRua() { return rua; }
    public String getNumero() { return numero; }
    public String getBairro() { return bairro; }
    public String getCidade() { return cidade; }
    public String getCep() { return cep; }
}

// ================= CLASSE BOLOS =================
class Bolos {
    private String tamanho;
    private double peso;
    private String sabor;
    private String cobertura;
    private String recheio;
    private double valor;

    public Bolos(String tamanho, double peso, String sabor, String cobertura, String recheio, double valor) {
        this.tamanho = tamanho;
        this.peso = peso;
        this.sabor = sabor;
        this.cobertura = cobertura;
        this.recheio = recheio;
        this.valor = valor;
    }

    public Bolos() {}

    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
    public String getSabor() { return sabor; }
    public void setSabor(String sabor) { this.sabor = sabor; }
    public String getCobertura() { return cobertura; }
    public void setCobertura(String cobertura) { this.cobertura = cobertura; }
    public String getRecheio() { return recheio; }
    public void setRecheio(String recheio) { this.recheio = recheio; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public void informacoesBolo() {
        System.out.printf("Tamanho: %s\nPeso: %.2f kg\nSabor: %s\nCobertura: %s\nRecheio: %s\nValor: R$ %.2f\n", tamanho, peso, sabor, cobertura, recheio, valor);
    }
}

// ================= CLASSE BOLOS ANIVERSARIO =================
class BolosAniversario extends Bolos {
    private String tema;

    public BolosAniversario(String tamanho, double peso, String sabor, String cobertura, String recheio, double valor, String tema) {
        super(tamanho, peso, sabor, cobertura, recheio, valor);
        this.tema = tema;
    }

    public BolosAniversario() {
        super();
    }

    public void setTema(String tema) { this.tema = tema; }
    public String getTema() { return this.tema; }

    @Override
    public void informacoesBolo() {
        super.informacoesBolo();
        System.out.printf("Tema: %s\n", tema);
    }
}

// ================= CLASSE CLIENTE =================
class Cliente extends TelaInicial {
    private int pontosFidelidade;

    public Cliente(String nome, String senha, String email, String dataDeNascimento, 
                   String cpf, String rua, String numero, String bairro, 
                   String cidade, String cep) {
        this.setNome(nome);
        this.setSenha(senha);
        this.setEmail(email);
        this.setDataDeNascimento(dataDeNascimento);
        this.setCPF(cpf);
        this.setRua(rua);
        this.setNumero(numero);
        this.setBairro(bairro);
        this.setCidade(cidade);
        this.setCep(cep);
        this.pontosFidelidade = 0;
    }

    public Cliente() {
        super();
        this.pontosFidelidade = 0;
    }

    public int getPontosFidelidade() { return pontosFidelidade; }
    public void adicionarPontos(int pontos) { this.pontosFidelidade += pontos; }

    public void exibirPerfilCliente() {
        System.out.println("=== PERFIL DO CLIENTE ===");
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("CPF: " + getCPF());
        System.out.println("Endereço: " + getRua() + ", Nº " + getNumero() + " - " + getBairro());
        System.out.println("Cidade: " + getCidade() + " | CEP: " + getCep());
        System.out.println("Pontos de Fidelidade: " + this.pontosFidelidade);
        System.out.println("==========================");
    }
}

// ================= CLASSE ADMINISTRADOR =================
class Administrador extends TelaInicial {
    private String cargo;
    private int nivelAcesso;

    public Administrador(String nome, String senha, String email, String dataDeNascimento, 
                         String cpf, String rua, String numero, String bairro, 
                         String cidade, String cep, String cargo, int nivelAcesso) {
        this.setNome(nome);
        this.setSenha(senha);
        this.setEmail(email);
        this.setDataDeNascimento(dataDeNascimento);
        this.setCPF(cpf);
        this.setRua(rua);
        this.setNumero(numero);
        this.setBairro(bairro);
        this.setCidade(cidade);
        this.setCep(cep);
        this.cargo = cargo;
        this.nivelAcesso = nivelAcesso;
    }

    public Administrador() {
        super();
    }

    public String getCargo(){ return cargo; }
    public void setCargo(String cargo){ this.cargo = cargo; }
    public int getNivelAcesso(){ return nivelAcesso; }
    public void setNivelAcesso(int nivelAcesso){ this.nivelAcesso = nivelAcesso; }
}

// ================= CLASSE PEDIDO =================
class Pedido {
    private static int contadorId = 1;
    private int idPedido;
    private Cliente cliente;
    private ArrayList<Bolos> itensComprados;
    private double valorTotal;

    public Pedido(Cliente cliente) {
        this.idPedido = contadorId++;
        this.cliente = cliente;
        this.itensComprados = new ArrayList<>();
        this.valorTotal = 0.0;
    }

    public void adicionarBolo(Bolos bolo) {
        this.itensComprados.add(bolo);
        this.valorTotal += bolo.getValor();
    }

    public int getIdPedido() { return idPedido; }
    public Cliente getCliente() { return cliente; }
    public ArrayList<Bolos> getItensComprados() { return itensComprados; }
    public double getValorTotal() { return valorTotal; }
}