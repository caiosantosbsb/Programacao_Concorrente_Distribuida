import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banco {
    private final Lock lock = new ReentrantLock();
    private double totalTransferido = 0;


    public void transferencia(Conta origem, Conta destino, double valor) {
        lock.lock();
        try {
            if (origem.getSaldo() >= valor) {
                origem.debitar(valor);
                destino.creditar(valor);
                totalTransferido += valor;
                System.out.println("Transferência de R$" + valor + " da conta de " + origem.getTitular() +
                        " para a conta de " + destino.getTitular() + " realizada com sucesso.");
            } else {
                System.out.println("Saldo insuficiente para a transferência de R$" + valor + " da conta de " +
                        origem.getTitular() + " para a conta de " + destino.getTitular() + ".");
            }
        } finally {
            lock.unlock();
        }
    }

    public double getTotalTransferido() {
        return totalTransferido;
    }

    public void exibirSaldosFinais(Conta[] contas) {
        System.out.println("\nSaldo Final de Contas:");
        for (Conta conta : contas) {
            System.out.println("Conta de " + conta.getTitular() + ": R$" + conta.getSaldo());
        }
    }
}
