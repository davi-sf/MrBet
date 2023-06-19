package testes;

import mrbet.Time;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {

    Time timeTeste = new Time("002_RJ","Clube de Regatas do Flamengo","Urubu");
    @Test
    void addParticipacao() {
        timeTeste.setParticipacoesEmCampeonato(5);
        timeTeste.addParticipacao();
        int resposta = 6;
        int resultado = timeTeste.getParticipacoesEmCampeonato();
        assertEquals(resposta,resultado);

    }
}