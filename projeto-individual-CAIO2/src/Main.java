public class Main {
    public static void main(String[] args) {
        // Criando o banco
        Banco banco = new Banco();

        // Criando as contas das lojas
        Conta contaLoja1 = new Conta("Boutique Elegância", 0);
        Conta contaLoja2 = new Conta("TechZone", 0);

        // Criando os funcionários das lojas
        Funcionario funcionario1Loja1 = new Funcionario("João Silva", contaLoja1, null);
        Funcionario funcionario2Loja1 = new Funcionario("Ana Souza", contaLoja1, null);
        Funcionario funcionario1Loja2 = new Funcionario("Carlos Oliveira", contaLoja2, null);
        Funcionario funcionario2Loja2 = new Funcionario("Mariana Costa", contaLoja2, null);

        // Passando os funcionários para as lojas
        Loja loja1 = new Loja("Boutique Elegância", contaLoja1, new Funcionario[]{funcionario1Loja1, funcionario2Loja1});
        Loja loja2 = new Loja("TechZone", contaLoja2, new Funcionario[]{funcionario1Loja2, funcionario2Loja2});

        // Criando as contas dos funcionários
        Conta contaFuncionario1 = new Conta("João Silva - Salário", 0);
        Conta contaInvestimentoFuncionario1 = new Conta("João Silva - Investimento", 0);

        Conta contaFuncionario2 = new Conta("Ana Souza - Salário", 0);
        Conta contaInvestimentoFuncionario2 = new Conta("Ana Souza - Investimento", 0);

        Conta contaFuncionario3 = new Conta("Carlos Oliveira - Salário", 0);
        Conta contaInvestimentoFuncionario3 = new Conta("Carlos Oliveira - Investimento", 0);

        Conta contaFuncionario4 = new Conta("Mariana Costa - Salário", 0);
        Conta contaInvestimentoFuncionario4 = new Conta("Mariana Costa - Investimento", 0);

        // Criando as contas dos clientes
        Conta contaCliente1 = new Conta("Lúcia Mendes", 1000);
        Cliente cliente1 = new Cliente("Lúcia Mendes", contaCliente1, new Loja[]{loja1, loja2}, banco);

        Conta contaCliente2 = new Conta("Pedro Alves", 1000);
        Cliente cliente2 = new Cliente("Pedro Alves", contaCliente2, new Loja[]{loja1, loja2}, banco);

        Conta contaCliente3 = new Conta("Catarina Santos", 1000);
        Cliente cliente3 = new Cliente("Catarina Santos", contaCliente3, new Loja[]{loja1, loja2}, banco);

        Conta contaCliente4 = new Conta("Rafael Oliveira", 1000);
        Cliente cliente4 = new Cliente("Rafael Oliveira", contaCliente4, new Loja[]{loja1, loja2}, banco);

        Conta contaCliente5 = new Conta("Amanda Pereira", 1000);
        Cliente cliente5 = new Cliente("Amanda Pereira", contaCliente5, new Loja[]{loja1, loja2}, banco);


        // Iniciando as threads dos clientes
        cliente1.start();
        cliente2.start();
        cliente3.start();
        cliente4.start();
        cliente5.start();

        // Aguardando pelas threads dos clientes terminarem
        try {
            cliente1.join();
            cliente2.join();
            cliente3.join();
            cliente4.join();
            cliente5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Pagando os funcionários
        loja1.pagarFuncionarios();
        loja2.pagarFuncionarios();

        // Exibindo saldo final das contas
        Conta[] contas = {contaCliente1, contaCliente2, contaCliente3, contaCliente4, contaCliente5,
                contaFuncionario1, contaFuncionario2, contaFuncionario3, contaFuncionario4,
                contaInvestimentoFuncionario1, contaInvestimentoFuncionario2, contaInvestimentoFuncionario3,
                contaInvestimentoFuncionario4, contaLoja1, contaLoja2};
        banco.exibirSaldosFinais(contas);
    }
}
