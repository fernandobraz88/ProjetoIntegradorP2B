package entidades;

import app.Banco;

import java.util.Scanner;

public class Emprestimo
{

    public static void menuEmprest()
    {
        double valorEmprestimo;
        int quantParcelas = 0;
        double taxaJuros = 0.03;
        double valorParcela = 0;

        System.out.println(" ");
        System.out.println("==== Emprestimo ====");
        System.out.println("====================");
        System.out.println("1 - Fazer Emprestimo");
        System.out.println("2 - Pagar Parcela");
        System.out.println(" ");

        Scanner input = new Scanner(System.in);
        int opcao = input.nextInt();

        if (opcao == 1)
        {
            System.out.println("Digite o valor do emprestimo: ");
            double emprestimo = input.nextDouble();

            if (emprestimo <= 0)
            {
                System.out.println("Valor de Emprestimo inválido");
                return;
            }

            System.out.println("Digite a quantidade de parcelas:");
            quantParcelas = input.nextInt();

            if (quantParcelas <= 0)
            {
                System.out.println("Quantidade de parcelas invalidas");
                return;
            }


            valorEmprestimo = emprestimo + (emprestimo*taxaJuros * quantParcelas);
            valorParcela = valorEmprestimo/quantParcelas;

            System.out.println("Digite sua senha:");
            int nSenha = input.nextInt();

            if (nSenha == Banco.getContaLogin().getSenha())
            {
                Banco.contaLogin.getContaCorrente().setSaldo(Banco.contaLogin.getContaCorrente().getSaldo()+emprestimo);
                Banco.contaLogin.getContaCorrente().getHistorico().add("+R$"+ emprestimo + " (Emprestimo)");

                System.out.println("Emprestimo Disponivel na sua Conta Corrente");
                System.out.println("Valor: R$" + emprestimo);
                System.out.println("Numero de Parcelas: "+ quantParcelas);
                System.out.println("Valor das Parcelas: R$"+ valorParcela);
                System.out.println("Total a pagar: R$" + valorEmprestimo);
            }

        }
        else if (opcao ==2)
        {
            if(valorParcela <= Banco.contaLogin.getContaCorrente().getSaldo())
            {
                System.out.println("Parcela " + quantParcelas + " Paga!");
                Banco.contaLogin.getContaCorrente().setSaldo(Banco.contaLogin.getContaCorrente().getSaldo()-valorParcela);
                Banco.contaLogin.getContaCorrente().getHistorico().add("-R$" + valorParcela +"(Parcela Emprestimo)");
                quantParcelas--;
                System.out.println("Restam apenas "+quantParcelas +" Parcelas");
            }
        }
        else
        {
            System.out.println("Opção invalida");
        }
        Banco.contaLogin.login();

    }


}
