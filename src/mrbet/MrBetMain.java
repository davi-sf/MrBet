package mrbet;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MrBetMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Controller controller = new Controller();
        while (true) {
            String op = opcao(sc);
            if (op.equals("!")) {
                System.out.println("valeu");
                break;
            }
            comando(sc, controller, op);
        }

    }

    public static String opcao(Scanner sc) {
        System.out.println("Este é o menu do MrBet");
        System.out.println("(M)Minha inclusão de times");
        System.out.println("(R)Recuperar time");
        System.out.println("(.)Adicionar campeonato");
        System.out.println("(B)Bora incluir time em campeonato e Verificar se time está em campeonato");
        System.out.println("(E)Exibir campeonatos que o time participa");
        System.out.println("(T)Tentar a sorte e status");
        System.out.println("(H)Historico");
        System.out.println("(!)Já pode fechar o programa!");

        return sc.next().toUpperCase();
    }

    public static void comando(Scanner sc, Controller mrBet, String op) {
        switch (op) {
            case "M":
                cadastraTimeMain(mrBet, sc);
                break;
            case "R":
                recuperaTimeMain(mrBet, sc);
                break;
            case ".":
                try{
                    adicionaCampeonatoMain(mrBet, sc);
                }  catch (InputMismatchException e) {
                    System.out.println("Erro: " + e.getMessage() + " Por favor, tente novamente.");
                }
                break;
            case "B":
                adicionaEVerificaTimeNoCampeonatoMain(mrBet, sc);
                break;
            case "E":
                exibeCampeonatosDoTimeMain(mrBet, sc);
                break;
            case "T":
                try {
                    fazerEVerificarAposta(mrBet, sc);
                } catch (InputMismatchException e) {
                    System.out.println("Erro: " + e.getMessage() + " Por favor, tente novamente.");
                }
                break;
            case "H":
                exibirHistorico(mrBet, sc);
                break;


        }


    }

    public static void cadastraTimeMain(Controller mrBet, Scanner sc) {
        sc.nextLine();
        System.out.println("Insira o codigo do time");
        String codigoTime = sc.nextLine();
        System.out.println("Insira o nome do time");
        String nomeTime = sc.nextLine();
        System.out.println("Insira o mascote do time");
        String mascote = sc.nextLine();
        System.out.println(mrBet.incluiTime(codigoTime, nomeTime, mascote));
    }

    public static void recuperaTimeMain(Controller mrBet, Scanner sc) {
        sc.nextLine();
        System.out.println("Insira o codigo do time");
        String codigoDoTime = sc.nextLine();
        System.out.println(mrBet.recuperaTime(codigoDoTime));

    }

    public static void adicionaCampeonatoMain(Controller mrBet, Scanner sc) throws InputMismatchException {
        sc.nextLine();
        System.out.println("Insira o nome do campeonato");
        String nomeCampeoanato = sc.nextLine();
        System.out.println("Insira a quantidade de times");
        int qtdTimesCampeonato;
        try {
            qtdTimesCampeonato = sc.nextInt();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("A quantidade de times deve ser um numero inteiro.");
        }
        System.out.println(mrBet.incluiCampeonato(nomeCampeoanato, qtdTimesCampeonato));
    }

    public static void adicionaEVerificaTimeNoCampeonatoMain(Controller mrBet, Scanner sc) {
        sc.nextLine();
        System.out.println("(I) Incluir time em campeonato ou (V) Verificar se time está em campeonato?");
        String submenu = sc.nextLine().toUpperCase();

        if (submenu.equals("I")) {
            System.out.println("Insira o codigo do time");
            String codigoTime = sc.nextLine();
            System.out.println("Insira o nome do campeonato");
            String nomeCampeonato = sc.nextLine();
            System.out.println(mrBet.adicionaEquipeAoCampeonato(codigoTime, nomeCampeonato));
        } else if (submenu.equals("V")) {
            System.out.println("Insira o codigo do time");
            String codigoTime = sc.nextLine();
            System.out.println("Insira o nome do campeonato");
            String nomeCampeonato = sc.nextLine();
            System.out.println(mrBet.adicionaEquipeAoCampeonato(codigoTime, nomeCampeonato));
        } else {
            System.out.println("Opção invalida");
        }
    }

    public static void exibeCampeonatosDoTimeMain(Controller mrBet, Scanner sc) {
        sc.nextLine();
        System.out.println("Insira o codigo do time");
        String codigoTime = sc.nextLine();
        System.out.println(mrBet.exibeCampeonatosDoTime(codigoTime));
    }

    public static void fazerEVerificarAposta(Controller mrBet, Scanner sc) throws InputMismatchException {
        sc.nextLine();
        System.out.println("(A)Apostar ou (S)Status das Apostas?");
        String submenu = sc.nextLine().toUpperCase();
        if (submenu.equals("A")) {
            System.out.println("Insira o codigo do time");
            String codigoTime = sc.nextLine();
            System.out.println("Insira o nome do campeonato");
            String nomeCampeonato = sc.nextLine();
            System.out.println("Insira a colocação");
            int colocacao;
            try {
                colocacao = sc.nextInt();
            } catch (InputMismatchException e) {
                throw new InputMismatchException("A colocacao deve ser um numero inteiro.");
            }
            System.out.println("Insira o valor da aposta");
            double valorDaAposta;
            try {
                valorDaAposta = sc.nextDouble();
            } catch (InputMismatchException e) {
                throw new InputMismatchException("O valor da aposta deve ser um numero.");
            }
            System.out.println(mrBet.fazerAposta(codigoTime, nomeCampeonato, colocacao, valorDaAposta));
        } else if (submenu.equals("S")) {
            System.out.println(mrBet.exibirAposta());

        } else {
            System.out.println("Opção invalida");
        }
    }

    public static void exibirHistorico(Controller mrBet, Scanner sc) {
        sc.nextLine();
        System.out.println(mrBet.bonusHistorico());
    }


}

