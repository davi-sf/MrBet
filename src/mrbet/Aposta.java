package mrbet;

import java.util.HashSet;

/**
 * Esta classe representa o objetivo principal do sistema MrBet, que sao as apostas;
 * Para se fazer uma aposta é necesario a colocacao que se vai apostar, o time, o campeonato e o valor da aposta;
 * Cada aposta possui um numero identificador, que é dado de acordo com a ordem da realização das apostas;
 */
public class Aposta {
    private int colocacao = 0;


    private Time time;

    private int numeroDaAposta;
    private Campeonato campeonato;
    private double valorDaAposta = 0;


    /**
     * Construtor de aposta
     * @param colocacao colocacao do time no campeonato que vai ser apostado
     * @param valorDaAposta valor que vai ser apostado;
     */
    public Aposta(int colocacao, double valorDaAposta) {
        this.colocacao = colocacao;
        this.valorDaAposta = valorDaAposta;
    }

    public int getColocacao() {
        return colocacao;
    }

    public double getValorDaAposta() {

        return valorDaAposta;
    }

    public void setTime(Time time) {

        this.time = time;
    }

    public void setCampeonato(Campeonato campeonato) {

        this.campeonato = campeonato;
    }

    public int getNumeroDaAposta() {

        return numeroDaAposta;
    }

    public void setNumeroDaAposta(int numeroDaAposta) {

        this.numeroDaAposta = numeroDaAposta;
    }


    public Time getTime() {

        return time;
    }

    public Campeonato getCampeonato() {

        return campeonato;
    }


    /**
     * Metodo toString para formatar as informações de uma aposta para a fncionalidade "status da aposta";
     * @return retorna as informações de aposta;
     */
    @Override
    public String toString() {
        return  getNumeroDaAposta() + ". " +
                "[" + getTime().getCodigoDoTime()+ "] " + getTime().getNomeDoTime() + " / " + getTime().getMascote() + "\n" +
                getCampeonato().getNomeDoCampeonato() + "\n" +
                getColocacao() + "/" + getCampeonato().getQuantidadeOriginal() + "\n" +
                "R$ " + getValorDaAposta();
    }
}
