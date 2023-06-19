package mrbet;

import java.util.HashMap;
import java.util.Objects;

/**
 * Para o funcionamento do sistema MrBet é necessario que hava um campeonato
 * para os times serem registados nele;
 * Para a existencia do campeonato é necessario Nome e quantidade de vagas que o campeonato vai oferecer
 * que diminui a cada time inserido;
 * Nesta classe também existe a quantidade de um contador de quantos times foram adicionados
 * assim como uma variavel que 'quantidadeOrginal' reponsavel por preservar a quantidade original
 * de vagas que o campeonato ofertou;
 */
public class Campeonato {

    private String nomeDoCampeonato;
    private int quantidadeDeVagas;
    private int qtdTimesQueForamAdicionados;
    private final int quantidadeOriginal;
    private HashMap<String, Time> equipesNoCampeonato = new HashMap<>();

    /**
     * Construtor de campeonato, onde ha dois parametros e onde 'quantidadeOriginal' recebe a quantidade de vagas iniciais
     * ofertadas pelo campeonato;
     * @param nomeDoCampeonato nome do campeonato
     * @param quantidadeDeVagas quantidade de vagas ofertadas por campeonato;
     */
    public Campeonato(String nomeDoCampeonato, int quantidadeDeVagas) {
        this.nomeDoCampeonato = nomeDoCampeonato;
        this.quantidadeDeVagas = quantidadeDeVagas;
        this.quantidadeOriginal = quantidadeDeVagas;
    }

    /**
     * HashMap que contem que os times que foram adicionados ao campeonato;
     * @return os times adicionados ao campeonato;
     */
    public HashMap<String, Time> getEquipesNoCampeonato() {
        return equipesNoCampeonato;
    }

    public void setQuantidadeDeVagas(int quantidadeDeVagas) {
        this.quantidadeDeVagas = quantidadeDeVagas;
    }

    public int getQuantidadeDeVagas() {

        return quantidadeDeVagas;
    }

    public String getNomeDoCampeonato() {

        return nomeDoCampeonato;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campeonato that = (Campeonato) o;
        return Objects.equals(nomeDoCampeonato, that.nomeDoCampeonato);
    }

    public int hashCode() {

        return Objects.hash(nomeDoCampeonato);
    }

    public int getQuantidadeOriginal() {
        return quantidadeOriginal;
    }

    public int getQtdTimesQueForamAdicionados() {
        return qtdTimesQueForamAdicionados;
    }

    public void setQtdTimesQueForamAdicionados(int qtdTimesQueForamAdicionados) {
        this.qtdTimesQueForamAdicionados = qtdTimesQueForamAdicionados;
    }
}
