public class Cliente extends Thread {
    private final String nome;
    private final Conta conta;
    private final Loja[] lojas;
    private final Banco banco;

    public Cliente(String nome, Conta conta, Loja[] lojas, Banco banco) {
        this.nome = nome;
        this.conta = conta;
        this.lojas = lojas;
        this.banco = banco;
    }

    @Override
    public void run() {
        // Compras até o saldo da conta estar vazio
        while (conta.getSaldo() > 0) {
            // Escolhendo uma loja aleatoriamente
            Loja loja = lojas[(int) (Math.random() * lojas.length)];
            // Escolhendo o valor da compra aleatoriamente
            double valorCompra = Math.random() < 0.5 ? 100 : 200;

            // Realizando a compra na loja escolhida
            synchronized (loja) {
                if (conta.getSaldo() >= valorCompra) {
                    banco.transferencia(conta, loja.getConta(), valorCompra); // Transferência de fundos da conta do cliente para a conta da loja
                    System.out.println(nome + " realizou uma compra de R$" + valorCompra + " na " + loja.getNome() +
                            ". Saldo restante: R$" + conta.getSaldo());
                } else {
                    System.out.println("Saldo insuficiente para " + nome + " realizar a compra de R$" + valorCompra);
                    break;
                }
            }

            // Alternando entre as lojas
            try {
                Thread.sleep(100); // Aguardando um tempo antes de ir para a próxima loja
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Conta de " + nome + " ficou sem saldo. Encerrando compras.");
    }

    public String getNome() {
        return nome;
    }
}
