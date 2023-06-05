package entidades;

import app.Banco;
import java.util.Scanner;
import java.util.ArrayList;

import static app.Banco.contaLogin;
import static app.Banco.getContaLogin;

public class Poupanca extends Conta
{
    private double saldo;
    private ArrayList<String> historico;


    public Poupanca()
    {
        super();
        this.saldo = 0;
        this.historico = new ArrayList<String>();
    }

    //Metodos:
    public void menuPoupanca()
    {
        Scanner input = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("==== Conta Poupança ====");
        System.out.println("========================");
        System.out.println("1 - Saldo");
        System.out.println("2 - Extrato");
        System.out.println("3 - Resgatar");
        System.out.println("4 - Gerar rendimento"); //opção para simular o rendimento da poupança.
        System.out.println("5 - Voltar");

        int opcaoCP = input.nextInt();

        switch(opcaoCP)
        {
            case 1:
            {
                Banco.contaLogin.getContaPoupanca().consultarSaldo();
                break;
            }
            case 2:
            {
                System.out.println(contaLogin.getContaPoupanca().historico);
                System.out.println("Saldo atual: R$"+ contaLogin.getContaPoupanca().saldo);
                break;
            }
            case 3:
            {
                System.out.println("Digite o valor a ser resgatado: ");
                double valor = input.nextDouble();

                Banco.contaLogin.getContaPoupanca().resgatar(valor);
                break;
            }
            case 4:
            {
                Banco.contaLogin.getContaPoupanca().rendimentoPoupanca();
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

        menuPoupanca();
    }

    public void resgatar(double valor)
    {
        if (Banco.contaLogin.getContaPoupanca().getSaldo() >= valor)
        {
            Banco.contaLogin.getContaPoupanca().setSaldo(getSaldo()-valor);
            Banco.contaLogin.getContaCorrente().setSaldo(contaLogin.getContaCorrente().getSaldo()+valor);

            Banco.contaLogin.getContaPoupanca().historico.add("-R$" + valor);
            Banco.contaLogin.getContaCorrente().getHistorico().add("+R$"+valor +" (Resgate Poupança)");

            System.out.println("Valor resgatado disponivel na Conta Corrente");
        }
        else
        {
            System.out.println("Saldo da Poupança insuficiente");
        }

    }

    public void rendimentoPoupanca()
    {
        Banco.contaLogin.getContaPoupanca().saldo += Banco.contaLogin.getContaPoupanca().getSaldo()*2/100;
        System.out.println("A poupança gerou rendimentos");
        System.out.println("Seu saldo da poupança é: "+ Banco.contaLogin.getContaPoupanca().getSaldo());
    }

    public void consultarSaldo()
    {
        System.out.println("Saldo da Poupança: " + Banco.contaLogin.getContaPoupanca().getSaldo());
    }

    //Gets e Setts:
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<String> getHistorico() {
        return historico;
    }

    public void setHistorico(ArrayList<String> historico) {
        this.historico = historico;
    }
}
