package entidades;

import java.util.Scanner;

import static app.Banco.contaLogin;
import static entidades.Emprestimo.menuEmprest;
import static entidades.Transferencia.menuTransf;

public class Conta
{
    private String nome;
    private String cpf;
    private int senha;
    private Corrente contaCorrente;
    private Poupanca contaPoupanca;
    private Investimento contaInvestimento;
    private int numeroConta;

    private static int numContas = 1;

    //Construtor:
    public Conta (String nome, String cpf, int senha)
    {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.numeroConta = numContas;

        numContas += 1;
    }

    public Conta() {

    }


    public void login()
    {
        Scanner input = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("==== Selecione uma operação disponivel ======");
        System.out.println("=============================================");
        System.out.println("1- Conta Corrente");
        System.out.println("2- Conta Poupança");
        System.out.println("3- Investimento");
        System.out.println("4- Emprestimo");
        System.out.println("5- Transferencia");
        System.out.println("6- Voltar");
        System.out.println("7- Sair");

        int opcaoConta = input.nextInt();

        switch (opcaoConta)
        {
            case 1:
            {
                contaLogin.getContaCorrente().menuCC();
                break;
            }
            case 2:
            {
                contaLogin.getContaPoupanca().menuPoupanca();
                break;
            }
            case 3:
            {
                contaLogin.getContaInvestimento().menuInvest();
                break;
            }
            case 4:
            {
                menuEmprest();
            }
            case 5:
            {
                menuTransf();
            }
            case 6:
            {
                app.Banco.operacoes();
                break;
            }
            case 7:
            {
                System.out.println("O Banco Integrador Agradece por Usar os Nossos Serviços");
                System.exit(0);
            }
            default:
            {
                System.out.println("Opção inválida");
                System.out.println("Selecione uma nova opção");

                contaLogin.login();
            }

        }

    }

    //Gets e Setts
    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    private void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getSenha() {
        return senha;
    }

    private void setSenha(int senha) {
        this.senha = senha;
    }

    public Corrente getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(Corrente contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public Poupanca getContaPoupanca() {
        return contaPoupanca;
    }

    public void setContaPoupanca(Poupanca contaPoupanca) {
        this.contaPoupanca = contaPoupanca;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    private void setNumeroConta(int numeroConta) {
        this.numeroConta = numContas;
    }

    public Investimento getContaInvestimento() {
        return contaInvestimento;
    }

    public void setContaInvestimento(Investimento contaInvestimento) {
        this.contaInvestimento = contaInvestimento;
    }
}
