package entidades;

import app.Banco;

import java.util.Scanner;

public class Transferencia
{

    public static void menuTransf()

    {
        System.out.println(" ");
        System.out.println("=== Transferencia ===");
        System.out.println("=====================");
        System.out.println("1- Transferir para outra Conta Corrente");
        System.out.println("2- Transferir para a Poupança");

        Scanner input = new Scanner(System.in);
        int opcao = input.nextInt();

        if (opcao == 1) //Transferencia entre contas:
        {
            System.out.println("Digite o valor a ser transferido: ");
            double valor = input.nextDouble();

            System.out.println("Digite o numero da Conta Destino: ");
            int nConta = input.nextInt();

            Conta contaDestino = Banco.acharConta(nConta);
            if (valor <= Banco.contaLogin.getContaCorrente().getSaldo())
            {

                //Transferindo o valor de uma conta p/outra:
                Banco.contaLogin.getContaCorrente().setSaldo(Banco.contaLogin.getContaCorrente().getSaldo() - valor);
                contaDestino.getContaCorrente().setSaldo(contaDestino.getContaCorrente().getSaldo() + valor);

                //Adicionando a transação ao Extrato das 2 contas:
                Banco.contaLogin.getContaCorrente().getHistorico().add("-R$" + valor + " [Transferencia: " + contaDestino.getNome() + "]");
                contaDestino.getContaCorrente().getHistorico().add("+R$ " + valor + " [Transferencia: " + Banco.contaLogin.getNome() + "]");

                System.out.println("Transferencia Realizada!");
            }
            else
            {
                System.out.println("Saldo da Conta Corrente insuficiente");
            }

        }
        else if (opcao == 2)
        {
            System.out.println("Digite o valor a ser transferido: ");
            double valor = input.nextDouble();

            if (valor <= Banco.contaLogin.getContaCorrente().getSaldo())
            {
                Banco.contaLogin.getContaCorrente().setSaldo(Banco.contaLogin.getContaCorrente().getSaldo()-valor);
                Banco.contaLogin.getContaCorrente().getHistorico().add("-R$" + valor + " (Poupança)");
                Banco.contaLogin.getContaPoupanca().setSaldo(Banco.contaLogin.getContaPoupanca().getSaldo()+valor);
                Banco.contaLogin.getContaPoupanca().getHistorico().add("+R$"+valor);

                System.out.println("Transferencia Realizada!");
            }
            else{
                System.out.println("Saldo da Conta Corrente insuficiente");
            }

        }
        else
        {
            System.out.println("Opção inválida");
        }
        Banco.contaLogin.login();
    }



}
