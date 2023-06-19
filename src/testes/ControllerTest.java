package testes;

import mrbet.Controller;
import mrbet.MrBetMain;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller mrBetTest = new Controller();

    @BeforeEach
    void cadastraTimes(){
        mrBetTest.incluiTime("250_PB","Nacional de Patos","Canário");
        mrBetTest.incluiTime("252_PB","Sport Lagoa Seca","Carneiro");
        mrBetTest.incluiTime("002_RJ","Clube de Regatas do Flamengo","Urubu");
        mrBetTest.incluiTime("105_PB","Sociedade Recreativa de Monteiro (SOCREMO)","Gavião");
    }

    @Test
    void recuperaTimes(){
        String resposta = "[002_RJ] Clube de Regatas do Flamengo / Urubu";
        String resultado = mrBetTest.recuperaTime("002_RJ");
        assertEquals(resposta,resultado);
    }

    @Test
    void recuperaTimesSemExistir(){
        String resposta = "TIME NÃO EXISTE!";
        String resultado = mrBetTest.recuperaTime("001_MS");
        assertEquals(resposta,resultado);

    }
    @Test
    void cadastraCampeonato() {
        String resposta = "CAMPEONATO ADICIONADO!";
        String resultado = mrBetTest.incluiCampeonato("Brasileirão Serie A 2023", 20);
        assertEquals(resposta, resultado);
    }

    @Test
    void cadastraCampeonatoComNomeJaExistente(){
        mrBetTest.incluiCampeonato("Brasileirão Serie A 2023",20);
        String resposta = "CAMPEONATO JÁ EXISTE!";
        String resultado = mrBetTest.incluiCampeonato("Brasileirão Serie A 2023", 20);
        assertEquals(resposta, resultado);

    }

    @Test
    public void fazerApostaValida(){
        mrBetTest.incluiCampeonato("Brasileirão Serie A 2023",20);
        mrBetTest.adicionaEquipeAoCampeonato("002_RJ","Brasileirão Serie A 2023");
        String resultado = mrBetTest.fazerAposta("002_RJ","Brasileirão Serie A 2023",1,45000);
        String resposta = "APOSTA REGISTRADA!";
        assertEquals(resposta,resultado);
    }

    @Test
    public void fazerApostaInvalida(){
        mrBetTest.incluiCampeonato("Brasileirão Serie A 2023",3);
        mrBetTest.adicionaEquipeAoCampeonato("002_RJ","Brasileirão Serie A 2023");
        String resultado = mrBetTest.fazerAposta("002_RJ","Brasileirão Serie A 2023",4,45000);
        String resposta = "APOSTA NÃO REGISTRADA!";
        assertEquals(resposta,resultado);


    }
    @Test
    void incluiTimeNoCampeonato(){
        mrBetTest.incluiCampeonato("Brasileirao Serie A 2023",20);
        String resposta = "TIME INCLUÍDO NO CAMPEONATO!";
        String resultado = mrBetTest.adicionaEquipeAoCampeonato("250_PB","Brasileirao Serie A 2023");
        assertEquals(resposta,resultado);
    }

    @Test
    void incluiTimeJaExistenteNoCampeonato(){
        mrBetTest.incluiCampeonato("Brasileirao Serie A 2023",20);
        mrBetTest.adicionaEquipeAoCampeonato("252_PB","Brasileirao Serie A 2023");
        String resposta = "TIME INCLUÍDO NO CAMPEONATO!";
        String resuldato = mrBetTest.adicionaEquipeAoCampeonato("252_PB","Brasileirao Serie A 2023");
        assertEquals(resposta,resuldato);
    }

    @Test
    void incluiTimeNaoCadastradoEmCampeonato(){
        mrBetTest.incluiCampeonato("Brasileirão série A 2023",20);
        String respota = "TIME NÃO EXISTE!";
        String resultado = mrBetTest.adicionaEquipeAoCampeonato("005_PB","Brasileirão série A 2023");
        assertEquals(respota,resultado);
    }

    @Test
    void campeonatoSemVagas(){
        mrBetTest.incluiCampeonato("Brasileirão série A 2023",1);
        String resposta = "TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!";
        mrBetTest.adicionaEquipeAoCampeonato("252_PB","Brasileirão série A 2023");
        String resultado = mrBetTest.adicionaEquipeAoCampeonato("250_PB","Brasileirão série A 2023");
        assertEquals(resposta,resultado);
    }

    @Test
    void incluiTimeEmCampeonatoNaoCadastrado(){
        String resposta = "CAMPEONATO NÃO EXISTE!";
        String resultado = mrBetTest.adicionaEquipeAoCampeonato("252_PB","Brasileirão série D 2023");
        assertEquals(resposta,resultado);
    }
    @Test
    void verificaTimeNoCampeonato(){
        mrBetTest.incluiCampeonato("Copa do Nordeste 2023",20);
        mrBetTest.adicionaEquipeAoCampeonato("250_PB","Copa do Nordeste 2023");
        String resposta = "É verdade que o time está incluído no campeonato.";
        String resultado = mrBetTest.verificaSeTimeTaNoCampeonato("250_PB","Copa do Nordeste 2023");
        assertEquals(resposta,resultado);
    }
    @Test
    void verificaTimeNaoEstaNoCampeonato(){
        mrBetTest.incluiCampeonato("Copa do Nordeste 2023",20);
        mrBetTest.adicionaEquipeAoCampeonato("250_PB","Copa do Nordeste 2023");
        String resposta = "É falso que o time está incluído no campeonato.";
        String resultado = mrBetTest.verificaSeTimeTaNoCampeonato("252_PB","Copa do Nordeste 2023");
        assertEquals(resposta,resultado);
    }

    @Test
    void exibeCampeonatosDoTime(){
        mrBetTest.incluiCampeonato("Copa do Nordeste 2023",20);
        mrBetTest.incluiCampeonato("Brasileirão série A 2023",20);
        mrBetTest.adicionaEquipeAoCampeonato("250_PB","Copa do Nordeste 2023");
        mrBetTest.adicionaEquipeAoCampeonato("250_PB","Brasileirão série A 2023");
        String resposta = "Campeonatos do Nacional de Patos\n" +
                "* Brasileirão série A 2023 - 1/20\n" +
                "* Copa do Nordeste 2023 - 1/20\n";
        String resultado = mrBetTest.exibeCampeonatosDoTime("250_PB");
        assertEquals(resposta,resultado);

    }
    //Testa bonus

    @Test
    void timeCadastradoSemEstarEmCampeonato(){
        String resposta = "Ainda não participou de campeonato\n" +
                "[250_PB] Nacional de Patos / Canário\n" +
                "[002_RJ] Clube de Regatas do Flamengo / Urubu\n" +
                "[105_PB] Sociedade Recreativa de Monteiro (SOCREMO) / Gavião\n" +
                "[252_PB] Sport Lagoa Seca / Carneiro" +"\n";
        String resultado = mrBetTest.participacaoNula();
        assertEquals(resposta,resultado);
    }

    @Test
    void timesNaoCadastradosForaDoSistemaMrBet(){
        mrBetTest.incluiCampeonato("Brasileirao 2023",20);
        mrBetTest.adicionaEquipeAoCampeonato("250_PB","Brasileirao 2023");
        mrBetTest.adicionaEquipeAoCampeonato("252_PB","Brasileirao 2023");
        mrBetTest.adicionaEquipeAoCampeonato("002_RJ","Brasileirao 2023");
        mrBetTest.adicionaEquipeAoCampeonato("105_PB","Brasileirao 2023");

        String resposta = "Ainda não participou de campeonato\n" +
                "Não há times cadastrados no sistema MrBet que ainda não foram hincluidos em campeonato";
        String resultado = mrBetTest.participacaoNula();
        assertEquals(resposta,resultado);
    }

    @Test
    void testaFrequenciaDeTimeCadastrados(){
        String resposta = "Participação mais frequente em campeonatos\n" +
                "Não houve times incluidos em campeonatos";
        String resultado = mrBetTest.maiorParticipcao();
        assertEquals(resposta,resultado);
    }

    @Test
    void testaFrequenciaDetimeCadastradosEmCampeonato(){
        mrBetTest.incluiCampeonato("Brasileirao Serie A 2023",20);
        String resposta = "Participação mais frequente em campeonatos" + "\n" +
                "[250_PB] Nacional de Patos / Canário (1) " + "\n";
        mrBetTest.adicionaEquipeAoCampeonato("250_PB","Brasileirao Serie A 2023");
        String resultado = mrBetTest.maiorParticipcao();
        assertEquals(resposta,resultado);

    }

    @Test
    void popularidadeEmApostas(){
        mrBetTest.incluiCampeonato("Brasileirao Serie A 2023",20);
        mrBetTest.adicionaEquipeAoCampeonato("002_RJ","Brasileirao Serie A 2023");
        mrBetTest.fazerAposta("002_RJ","Brasileirao Serie A 2023",1,1000);
        String resultado = mrBetTest.popularidadeEmApostas();
        String resposta = "Popularidade em apostas\n" +
                "Clube de Regatas do Flamengo / 1";
        assertEquals(resposta,resultado);

    }



}
