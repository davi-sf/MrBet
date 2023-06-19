package mrbet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;

/**
 * O sistema MrBet é um sistema um pouco complexo, entretanto aqui é possivel ver a classe principal dele;
 * Aqui estão os repositorios e nesta classe de nome Controller também possui os principais metodos e funcionalidades;
 * Repositorios de Time, Campeonato, e Aposta sao encontrados aqui;
 */
public class Controller {
    private HashMap<String, Time> times = new HashMap<>();
    private HashMap<String, Campeonato> campeonatos = new HashMap<>();

    private ArrayList<Aposta> apostas = new ArrayList<>();


    /**
     * Este meteodo "verificaTimePeloCodigo" é responsavel por verificar se um time existe a partir do seu id (codigo)
     *
     * @param codigoDoTime id unico do time
     * @return retorna true caso existe e false, para caso contrario.
     */
    public boolean verificaTimePeloCodigo(String codigoDoTime) {

        return times.containsKey(codigoDoTime);
    }

    /**
     * Este meteodo "verificaCampeonatoPeloNome" é responsavel por verificar se um campeonato existe a partir do seu nome
     *
     * @param nomeDoCampeonato identificador do campeonato
     * @return retorna true caso existe e false, para caso contrario.
     */
    public boolean verificaCampeonatoPeloNome(String nomeDoCampeonato) {
        return campeonatos.containsKey(nomeDoCampeonato);
    }

    /**
     * Este metodo "incluiTime" é responsavel por cadastrar um time;
     *
     * @param codigoDoTime id identificador do time;
     * @param nomeDoTime   nome do time;
     * @param mascote      mascote do time;
     * @return retorna um objeto do tipo Time, caso os dados passados sejam validos
     */
    public String incluiTime(String codigoDoTime, String nomeDoTime, String mascote) {
        Time novoTime = new Time(codigoDoTime, nomeDoTime, mascote);
        if (verificaTimePeloCodigo(codigoDoTime)) {
            return "TIME JÁ EXISTE!";
        } else {
            times.put(codigoDoTime, novoTime);
            return "INCLUSÃO REALIZADA!";
        }
    }


    /**
     * Este metodo recupera um time em caso de ele existir
     *
     * @param codigoDoTime id do time, responsavel por identificar o time
     * @return retorna um time caso exista, caso contrario retorna uma msg informando que o time nao existe
     */
    public String recuperaTime(String codigoDoTime) {
        Time time = times.get(codigoDoTime);
        if (verificaTimePeloCodigo(codigoDoTime)) {
            return "[" + times.get(codigoDoTime).getCodigoDoTime() + "] " + time.getNomeDoTime() + " / " + time.getMascote();
        } else {
            return "TIME NÃO EXISTE!";
        }
    }

    /**
     * Este metodo é responsavel por cadastrar um campeonato.
     *
     * @param nomeDoCampeonato o nome do campeonato que vai receber o time
     * @param quantidadeDeEquipesParticipantes a quantidade de equipes que o campeonato vai suportar
     * @return retorna um objeto do tipo campeonato, ou uma msg informando que o campeonato ja existe
     * em caso de o campeonato com as informaçoes passadas ja ter sido criado antes.
     */
    public String incluiCampeonato(String nomeDoCampeonato, int quantidadeDeEquipesParticipantes) {
        Campeonato novoCampeonato = new Campeonato(nomeDoCampeonato, quantidadeDeEquipesParticipantes);
        if (verificaCampeonatoPeloNome(nomeDoCampeonato)) {
            return "CAMPEONATO JÁ EXISTE!";
        } else {
            campeonatos.put(nomeDoCampeonato, novoCampeonato);
            return "CAMPEONATO ADICIONADO!";
        }


    }

    /**
     * este metodo é responsavel por adicionar um time a um campeonato
     *
     * @param codigoDoTime     id identificador do time desejado a adicionar ao campoenato
     * @param nomeDoCampeonato nome do campeonato que vai receber o time
     * @return retorna uma equipe adicionada ao campeonato, ou uma mensagem informando
     * o porque o time não pode ser adicionado
     */
    public String adicionaEquipeAoCampeonato(String codigoDoTime, String nomeDoCampeonato) {
        if (!verificaCampeonatoPeloNome(nomeDoCampeonato)) {
            return "CAMPEONATO NÃO EXISTE!";
        }

        Campeonato campeonato = campeonatos.get(nomeDoCampeonato);

        if (campeonato.getQuantidadeDeVagas() == 0) {
            return "TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!";
        }

        if (campeonato.getEquipesNoCampeonato().containsKey(codigoDoTime)) {
            return "TIME INCLUÍDO NO CAMPEONATO!";
        }

        if (!verificaTimePeloCodigo(codigoDoTime)) {
            return "TIME NÃO EXISTE!";
        }

        Time time = times.get(codigoDoTime);
        campeonato.getEquipesNoCampeonato().put(codigoDoTime, time);
        campeonato.setQuantidadeDeVagas(campeonato.getQuantidadeDeVagas() - 1);
        time.addParticipacao();
        campeonato.setQtdTimesQueForamAdicionados(campeonato.getQtdTimesQueForamAdicionados() + 1);
        return "TIME INCLUÍDO NO CAMPEONATO!";
    }

    /**
     * verifica se um time está presente em um determinado campeonato
     *
     * @param codigoDoTime     id do time que se deseja saber se pertence a um determinado campeonato
     * @param nomeDoCampeonato nome do campeonato que se deseja saber o se time está presente nele
     * @return retorna a informçao se o time está presente no campeonato ou nao, como também a de
     * se o time ou campeonato nao existir
     */
    public String verificaSeTimeTaNoCampeonato(String codigoDoTime, String nomeDoCampeonato) {
        if (!verificaTimePeloCodigo(codigoDoTime)) {
            return "O TIME NÃO EXISTE!";

        }
        if (!verificaCampeonatoPeloNome(nomeDoCampeonato)) {
            return "O CAMPEONATO NÃO EXISTE!";
        }
        Time time = times.get(codigoDoTime);
        if (campeonatos.get(nomeDoCampeonato).getEquipesNoCampeonato().containsKey(codigoDoTime)) {
            return "É verdade que o time está incluído no campeonato.";

        } else {
            return "É falso que o time está incluído no campeonato.";
        }

    }

    /**
     * este metodo é responsavel por exibir todos os campeonatos de um determinado time
     *
     * @param codigoDoTime é o id identificador do time que se deseja saber os campeonatos que ele esta incluso
     * @return retorna os campeonatos que o time participa, assim como as informações em caso do time nao estar
     * cadastrado no sistema ou nao estar presente no campeonato
     */
    public String exibeCampeonatosDoTime(String codigoDoTime) {
        Time time = times.get(codigoDoTime);
        String resposta = "";
        if (!verificaTimePeloCodigo(codigoDoTime)) {
            return "Time não cadastrado no sistema";
        }
        for (Campeonato campeonato : this.campeonatos.values()) {
            if (campeonato.getEquipesNoCampeonato().containsKey(codigoDoTime)) {
                resposta += "* " + campeonato.getNomeDoCampeonato() + " - " + campeonato.getQtdTimesQueForamAdicionados() + "/" + campeonato.getQuantidadeOriginal() + "\n";
            } else {
                return "É falso que o time está incluído no campeonato";
            }
        }
        return "Campeonatos do " + time.getNomeDoTime() + "\n" + resposta;

    }

    /**
     * Este metodo é responsavel por fazer uma aposta. Para fazer uma aposta é necessario
     * codigo do time, nome do campeonato, a colocacao e o valor da aposta;
     * @param codigoDoTime codigo do time que vai ser apostado;
     * @param nomeDoCampeonato nome do campeonato que que vai ser apostado;
     * @param colocacao colocacao do time que vai ser apostada;
     * @param valorDaAposta o valor da aposta;
     * @return retorna as condiçoes da aposta, se a aposta foi registrada, nao regristada ou se o time ou campeonato
     * nao estao cadastrados no sistema;
     */

    public String fazerAposta(String codigoDoTime, String nomeDoCampeonato, int colocacao, double valorDaAposta) {
        Time time = times.get(codigoDoTime);
        Campeonato campeonato = campeonatos.get(nomeDoCampeonato);
        if (!verificaTimePeloCodigo(codigoDoTime)) {
            return "Time não cadastrado no sistema";
        }
        if (!verificaCampeonatoPeloNome(nomeDoCampeonato)) {
            return "Campeonato não cadastrado no sistema";
        }
        if (colocacao > campeonato.getQuantidadeDeVagas()) {
            return "APOSTA NÃO REGISTRADA!";
        }
        if(colocacao < 1){
            return "COLOCAÇÃO INVALIDA!";
        }
        if (!campeonato.getEquipesNoCampeonato().containsKey(codigoDoTime)) {
            return "APOSTA NÃO REGISTRADA, TIME NÃO ESTÁ NO CAMPEONATO";
        } else {
            Aposta novaAposta = new Aposta(colocacao, valorDaAposta);
            novaAposta.setTime(time);
            novaAposta.setCampeonato(campeonato);
            if (novaAposta.getColocacao() == 1) {
                time.setPrimeiraColocacao(time.getPrimeiraColocacao() + 1);
            }
            apostas.add(novaAposta);
            return "APOSTA REGISTRADA!";
        }
    }

    /**
     * Este metodo é responsavel por exibir as informações de uma Aposta com a ajuda de um metodo da classe Aposta;
     * Aqui também é onde é atualizado o numero de apostas;
     * @return retorna as informaçoes da aposta ou "não ha apostas" em caso de nao haver apostas registradas;
     */
    public String exibirAposta() {
        String resposta = "";

        for (int i = 0; i < apostas.size(); i++) {
            apostas.get(i).setNumeroDaAposta(i+1);
            resposta += apostas.get(i).toString() + "\n";
        }
        if (resposta.isEmpty()) {
            return "Não há apostas";
        }
        return resposta;
    }

    /**
     * Este metodo foi criado para o objetivo 'BONUS'. Este metodo é responsavel por exibir
     * uma informação especifica das apostas, que é a quantidade de vezes em que os times
     * apareceram na colocação de primeiro lugar nas apostas registradas no sistema
     * @return retorna a popularidade das apostas;
     */
    public String popularidadeEmApostas() {
        String resposta = "";
        for (Time time : times.values()) {
            if (time.getPrimeiraColocacao() > 0) {
                resposta += time.getNomeDoTime() + " / " + time.getPrimeiraColocacao();
            }
        }
        if (resposta.isEmpty()) {
            return "Não há apostas";
        }
        return "Popularidade em apostas" + "\n"  + resposta;
    }


    /**
     * Este metodo é responsavel por identificar o time ou os times com maiores participacoes em capeonatos;
     * @return retorna o time ou os times com maiores participacoes em campeonatos
     * ou uma mensagem informando que nao houve times que participaram de campeonatos;
     */
    public String maiorParticipcao() {
        Collection<Time> times = this.times.values();

        ArrayList<Time> timesComMaisParticipacao = new ArrayList<>();
        int maiorParticipacao = 0;

        for (Time t : times) {
            if (maiorParticipacao == 0) {
                maiorParticipacao = t.getParticipacoesEmCampeonato();
            } else if (maiorParticipacao < t.getParticipacoesEmCampeonato()) {
                maiorParticipacao = t.getParticipacoesEmCampeonato();
            }
        }
        if (maiorParticipacao > 0) {
            for (Time t : times) {
                if (t.getParticipacoesEmCampeonato() == maiorParticipacao) {
                    timesComMaisParticipacao.add(t);
                }
            }
            String timesParticipacao = "";
            for (int i = 0; i < timesComMaisParticipacao.size(); i++) {
                timesParticipacao += "[" + timesComMaisParticipacao.get(i).getCodigoDoTime() + "] " + timesComMaisParticipacao.get(i).getNomeDoTime() + " / " +
                        timesComMaisParticipacao.get(i).getMascote() + " (" + timesComMaisParticipacao.get(i).getParticipacoesEmCampeonato() + ") " + "\n";
            }
            return "Participação mais frequente em campeonatos" + "\n" + timesParticipacao;
        }

        return "Participação mais frequente em campeonatos" + "\n" + "Não houve times incluidos em campeonatos"; // surdo
    }

    /**
     * Este metodo é responsavel por identicar o time existente que ainda nao teve participação em nenhum campeonato
     * @return retorna os times existentes que não estao em campeonatos. Em caso de não existirem times cadastrados
     * retorna uma mensagem alegando que não ha times existentes que ainda nao participaram de nenhum campeoanto.
     */
    public String participacaoNula() {
        ArrayList<Time> times0Participacao = new ArrayList<>();
        for (Time t : this.times.values()) {
            if (t.getParticipacoesEmCampeonato() == 0) {
                times0Participacao.add(t);
            }
        }
        if (times0Participacao.size() > 0) {
            String timesParticipacao = "";
            for (int i = 0; i < times0Participacao.size(); i++) {
                timesParticipacao += "[" + times0Participacao.get(i).getCodigoDoTime() + "] " + times0Participacao.get(i).getNomeDoTime() + " / " +
                        times0Participacao.get(i).getMascote() + "\n";
            }
            return "Ainda não participou de campeonato" + "\n" + timesParticipacao;
        }

        return "Ainda não participou de campeonato" + "\n" + "Não há times cadastrados no sistema MrBet que ainda não foram hincluidos em campeonato";

    }

    /**
     * Metodo para a funcionalidade 'BONUS', reponsavel por juntar todos os metodos que cumprem o objetivo bonus historico;
     * @return as informaçoes historicas do MrBet;
     */
    public String bonusHistorico() {
        String historico = participacaoNula() + "\n\n" + maiorParticipcao() + "\n\n" + popularidadeEmApostas();
        return historico;
    }
}