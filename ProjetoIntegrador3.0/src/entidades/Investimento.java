package entidades;

import java.util.Scanner;

import static app.Banco.contaLogin;

public class Investimento extends Conta
{
    double saldo;

    //Construtor:
    public Investimento ()
    {
        super();
        this.saldo = 0;
    }

    Scanner input = new Scanner(System.in);

    public void menuInvest()
    {
        System.out.println(" ");
        System.out.println("=== Conta Investimento ===");
        System.out.println("==========================");
        System.out.println("1 - Fazer Investimento");
        System.out.println("2 - Resgatar Investimento");
        System.out.println("3 - Gerar rendimento");
        System.out.println("4 - Saldo ");
        System.out.println("5 - Voltar");


        int opcaoInv = input.nextInt();

        switch (opcaoInv)
        {
            case 1:
            {
                investir();
                break;
            }
            case 2:
            {
                resgatarInvest();
                break;
            }
            case 3:
            {
                rendimentoInvestimento();
                break;
            }
            case 4:
            {
                System.out.println(contaLogin.getContaInvestimento().getSaldo());
                break;
            }
            case 5:
            {
                contaLogin.login();
                break;
            }
            default:
            {
                System.out.println("Opção inválida");
            }
        }
        menuInvest();
    }

    public void investir()
    {
        System.out.println("Regras para o investimento:");
        System.out.println("1- O valor investido ficará retido por 12 meses.");
        System.out.println("2- Ao final deste prazo estará disponivel para resgate.");
        System.out.println("3- Valor Mínimo: R$500");
        System.out.println("Digite o valor que deseja investir");

        double valor = input.nextDouble();

        if (valor > 500 && valor <= contaLogin.getContaCorrente().getSaldo())
        {
            this.saldo = valor;
            contaLogin.getContaCorrente().setSaldo(contaLogin.getContaCorrente().getSaldo() - valor);
            contaLogin.getContaCorrente().getHistorico().add("-R$"+valor+ " (Investimento)");
            System.out.println("Investimento de R$" + valor + "Realizado");
        }
        else
        {
            System.out.println("Valor Inválido ou Saldo da Conta Corrente Insuficiente");
        }

    }

    public void resgatarInvest()
    {
        if (getSaldo() > 0)
        {
            contaLogin.getContaCorrente().setSaldo(contaLogin.getContaCorrente().getSaldo() + contaLogin.getContaInvestimento().saldo);
            contaLogin.getContaCorrente().getHistorico().add("+R$"+getSaldo()+" (Resgate Investimento)");
            setSaldo(0);
            System.out.println("Seu Investimento foi Resgatado e está disponivel na sua Conta Corrente");
        }
        else
        {
            System.out.println("Você não possui investimentos");
        }
    }

    public void rendimentoInvestimento()
    {
        this.saldo += getSaldo()*10/100;
        System.out.println("Seu Investimento Gerou rendimentos");
        System.out.println("Seu saldo da Conta Investimento é: "+ getSaldo());
    }

    //Get e Set:
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
