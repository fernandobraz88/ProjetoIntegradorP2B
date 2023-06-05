package entidades;

import java.util.ArrayList;
import java.util.Scanner;
import app.Banco;
import static app.Banco.contaLogin;
import static app.Banco.getContaLogin;

public class Corrente extends Conta
{
    private double saldo;
    private final double chequeEspecial;
    private ArrayList<String> historico;


    //Construtor:
    public Corrente()
    {
        super();
        this.saldo = 0;
        this.chequeEspecial = 1000;
        this.historico = new ArrayList<String>();

    }

    //Metodos:

    public void menuCC()
    {
        Scanner input = new Scanner(System.in);
        System.out.println(" ");
        System.out.println("==== Conta Corrente ====");
        System.out.println("========================");
        System.out.println("1 - Sacar");
        System.out.println("2 - Depositar");
        System.out.println("3 - Saldo");
        System.out.println("4 - Extrato");
        System.out.println("5 - Voltar");

        int opcaoCC = input.nextInt();

        switch (opcaoCC)
        {
            case 1:
            {
                System.out.println("Digite o Valor de Saque");
                double valor = input.nextDouble();

                if (valor > 0 && valor <= contaLogin.getContaCorrente().saldo + contaLogin.getContaCorrente().chequeEspecial)
                {
                    System.out.println("Digite sua senha: ");
                    int nSenha = input.nextInt();

                    if (nSenha == contaLogin.getSenha())
                    {
                        contaLogin.getContaCorrente().sacar(valor);
                    }
                    else
                    {
                        System.out.println("Senha incorreta");
                    }
                }
                else
                {
                    System.out.println("Saldo insuficiente ou valor inválido");
                }
                break;
            }
            case 2:
            {
                System.out.println("Digite o Valor de Depósito");
                double valor = input.nextDouble();

                Banco.contaLogin.getContaCorrente().depositar(valor);
                break;
            }
            case 3:
            {
                Banco.contaLogin.getContaCorrente().consultarSaldo();
                break;
            }
            case 4:
            {
                getContaLogin().getContaCorrente().extrato();
                break;
            }
            case 5:
            {
                getContaLogin().login();
                break;
            }
            default:
            {
                System.out.println("Opção inválida");
            }
        }
        menuCC();
    }

    public void sacar(double valor)
    {
        if(valor > contaLogin.getContaCorrente().saldo + contaLogin.getContaCorrente().chequeEspecial )
        {
            System.out.println("Saldo insuficiente");
        }
        else
        {
            contaLogin.getContaCorrente().setSaldo(getSaldo()-valor);
            contaLogin.getContaCorrente().historico.add("-R$"+valor + " (Saque)");
            System.out.printf("Saque de R$%.2f realizado \n",valor);
        }
    }

    public void depositar(double valor)
    {
        if (valor > 0)
        {
            contaLogin.getContaCorrente().setSaldo(getSaldo()+valor);
            contaLogin.getContaCorrente().historico.add("+R$" + valor + " (Deposito)");
            System.out.printf("Deposito de R$%.2f realizado \n", valor);
        }
        else
        {
            System.out.println("Valor de depósito inválido");
        }

    }

    public void consultarSaldo()
    {
        System.out.println("Saldo atual: R$"+ contaLogin.getContaCorrente().getSaldo());
    }

    public String extrato()
    {
        System.out.println(contaLogin.getContaCorrente().historico);
        System.out.println("Saldo atual: R$"+ contaLogin.getContaCorrente().saldo);

        return String.valueOf(contaLogin.getContaCorrente().historico);
    }

    //Gets e Sets:
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getChequeEspecial() {
        return chequeEspecial;
    }

    public ArrayList<String> getHistorico() {
        return historico;
    }

    public void setHistorico(ArrayList<String> historico) {
        this.historico = historico;
    }
}
