public class Funcionario extends Thread {
    private final String nome;
    private final Conta contaSalario;
    private final Conta contaInvestimento;
    private static final double SALARIO = 1400;

    public Funcionario(String nome, Conta contaSalario, Conta contaInvestimento) {
        this.nome = nome;
        this.contaSalario = contaSalario;
        this.contaInvestimento = contaInvestimento;
    }

    @Override
    public void run() {
        synchronized (contaSalario) {
            contaSalario.creditar(SALARIO);
            double valorInvestimento = SALARIO * 0.2;
            contaInvestimento.creditar(valorInvestimento);
            System.out.println(nome + " recebeu salário de R$" + SALARIO + ". Investiu R$" + valorInvestimento + " na conta de investimento.");
        }
    }

    public String getNome() {
        return nome;
    }

    public static double getSalario() {
        return SALARIO;
    }
}
