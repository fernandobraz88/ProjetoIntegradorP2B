package app;

import java.util.Scanner;
import java.util.ArrayList;
import entidades.*;

public class Banco
{
    public static Conta contaLogin;
    static Scanner input = new Scanner(System.in);
    static ArrayList<Conta> contasBancarias;

    public static void main(String[] args)
    {
        contasBancarias = new ArrayList<Conta>();
        System.out.println("===== PROJETO INTEGRADOR =====");
        System.out.println("Integrantes:");
        System.out.println("* Fernando Braz da Silveira");
        System.out.println("* Murilo Kaspar Deininger Neto");
        System.out.println("* Jayme Holanda");

        operacoes();
    }

    public static void operacoes()
    {

        System.out.println(" ");
        System.out.println("----------------------------------------");
        System.out.println("----- Bem vindo ao Banco Integrador ----");
        System.out.println("----------------------------------------");
        System.out.println("##########-Selecione uma opção-#########");
        System.out.println("----------------------------------------");
        System.out.println("| 1 -  Criar Conta        - |");
        System.out.println("| 2 -  Acessar Conta      - |");
        System.out.println("| 3 -  Listar Contas      - |");
        System.out.println("| 4 -  Sair               - |");

        int opcao = input.nextInt();

        switch (opcao)
        {
            case 1:
            {
                criarConta();
                break;
            }
            case 2:
            {
                acessarConta();
                break;
            }
            case 3:
            {
                listarContas();
                break;
            }
            case 4:
                System.out.println("O Banco Integrador Agradece por Usar os Nossos Serviços");
                System.exit(0);
            default:
            {
                System.out.println("Opção inválida");
                System.out.println("Selecione uma nova opção");
            }
                operacoes();

        }
    }

    public static Conta acharConta(int numeroConta)
    {
        Conta conta = null;

        if(contasBancarias.size() > 0)
        {
            for(Conta i: contasBancarias)
            {
                if (i.getNumeroConta() == numeroConta)
                {
                    conta = i;
                }
            }

        }
        return conta;
    }

    public static void criarConta()
    {   Scanner entrada = new Scanner(System.in);

        System.out.println("******* CADASTRO DE USUARIO ********");

        System.out.println("Digite o Nome: ");
        String nome = entrada.nextLine();

        System.out.println("Digite o CPF:");
        String cpf = entrada.nextLine();

        System.out.println("Digite a Senha (6 Números): ");
        int senha = entrada.nextInt();

        Conta conta = new Conta(nome,cpf,senha);
        conta.setContaCorrente(new Corrente());
        conta.setContaPoupanca(new Poupanca());
        conta.setContaInvestimento(new Investimento());
        contasBancarias.add(conta);

        System.out.println("=================================");
        System.out.println("== Parabens " + conta.getNome() +" ==");
        System.out.println("=================================");
        System.out.println("Conta Bancaria Criada com Sucesso!!!");
        System.out.println("Seus dados são: ");
        System.out.println("NOME: "+ conta.getNome());
        System.out.println("CPF: "+ conta.getCpf());
        System.out.println("NUM. da CONTA: " + conta.getNumeroConta());
        System.out.println("SENHA "+ conta.getSenha());


     operacoes();

    }

    public static void acessarConta()
    {
        if (contasBancarias.size() == 0)
        {
            System.out.println("Ainda não ha contas cadastradas");
        }
        else
        {
            System.out.println("Digite o numero da sua conta:");
            int nConta = input.nextInt();
            System.out.println("Digite a sua senha de 6 digitos:");
            int nSenha = input.nextInt();

            Conta conta = acharConta(nConta);

            if (conta != null)
            {
                if (nConta == conta.getNumeroConta() && nSenha == conta.getSenha())
                {
                    setContaLogin(conta);
                    System.out.println(" ");
                    System.out.println("Seja bem vindo(a)" + contaLogin.getNome());
                    contaLogin.login();
                }
                else
                {
                    System.out.println("Numero da conta e/ou senha incorretos");
                    operacoes();
                }

            }
            else
            {
                System.out.println("Conta não encontrada");
            }
        }
        operacoes();
    }

    public static void listarContas()
    {
        if (contasBancarias.size() > 0)
        {
            for (Conta conta : contasBancarias)
            {
                System.out.println("Nome: "+ conta.getNome());
                System.out.println("CPF: "+ conta.getCpf());
                System.out.println("Numero da conta: "+conta.getNumeroConta());
                System.out.println("Senha: "+ conta.getSenha());
                System.out.println("Saldo da Conta Corrente: "+conta.getContaCorrente().getSaldo());
                System.out.println("Saldo da Poupança: "+conta.getContaPoupanca().getSaldo());
                System.out.println("Saldo da C.Investimento: "+conta.getContaInvestimento().getSaldo());
                System.out.println("========================");
                System.out.println(" ");
            }
        } else
        {
            System.out.println("Ainda não há contas cadastradas");
        }
        operacoes();
    }

    public static Conta getContaLogin() {
        return contaLogin;
    }

    public static void setContaLogin(Conta contaLogin) {
        Banco.contaLogin = contaLogin;
    }
}
