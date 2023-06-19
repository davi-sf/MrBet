package mrbet;

/**
 * Para o funcionamento do MrBet é necessario que haja a existencia de times;
 * Cada time possui nome, um id identificador, que é o codigo do time, e também um mascote;
 * O Sistema também precisa de informações como o numero de vezes que determinado time apareceu em primeiro lugar
 * e também a quantidade de vezes que este time participou de um campeonato;
 */
public class Time {
    private String nomeDoTime;
    private String codigoDoTime;
    private String mascote;

    private int primeiraColocacao = 0;

    private int participacoesEmCampeonato = 0;

    /**
     * construtor de Time;
     * @param codigoDoTime codigo do time
     * @param nomeDoTime nome do time
     * @param mascote mascote do time
     */
    public Time(String codigoDoTime, String nomeDoTime, String mascote) {
        this.nomeDoTime = nomeDoTime;
        this.codigoDoTime = codigoDoTime;
        this.mascote = mascote;
    }

    public String getNomeDoTime() {
        return nomeDoTime;
    }

    public String getCodigoDoTime() {
        return codigoDoTime;
    }

    public String getMascote() {
        return mascote;
    }

    public int getParticipacoesEmCampeonato() {
        return participacoesEmCampeonato;
    }


    /**
     * Este metodo é responsavel por contabilizar a participação de um time em campeonato
     */
    public void addParticipacao(){

        this.participacoesEmCampeonato++;
    }

    public void setPrimeiraColocacao(int primeiraColocacao) {
        this.primeiraColocacao = primeiraColocacao;
    }
    public int getPrimeiraColocacao() {
        return primeiraColocacao;
    }

    public void setParticipacoesEmCampeonato(int participacoesEmCampeonato) {
        this.participacoesEmCampeonato = participacoesEmCampeonato;
    }
}
