package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudenTyhjanVarastonTilavuusEiVoiOllaNegatiivinen(){
        this.varasto = new Varasto(-10);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudenEiTyhjanVarastonTilavuusEiVoiOllaNegatiivinen(){
        this.varasto = new Varasto(-10, 10);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudenEiTyhjanVarastonSaldoEiVoiOllaNegatiivinen(){
        this.varasto = new Varasto(10, -10);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudenEiTyhjanVarastonSaldoEiVoiYlittaaTilavuutta(){
        this.varasto = new Varasto(100, 110);
        assertEquals(100, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudenEiTyhjanVarastonAlkusaldoOnOikein(){
        this.varasto = new Varasto(100, 80);
        assertEquals(80, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiMuutaSaldoa(){
        this.varasto = new Varasto(10, 5);
        varasto.lisaaVarastoon(-1);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoaEiVoiTayttaaYli() {
        varasto.lisaaVarastoon(20);
        assertEquals(10, this.varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttoEiMuutaSaldoa(){
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(-1);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttoPalauttaaNollan(){
        varasto.lisaaVarastoon(5);
        assertEquals(0, varasto.otaVarastosta(-1), vertailuTarkkuus);
    }

    @Test
    public void yliSaldonOttaminenPalauttaaVainSaldon(){
        varasto.lisaaVarastoon(5);
        assertEquals(5, varasto.otaVarastosta(10), vertailuTarkkuus);
    }
    
    @Test
    public void yliSaldonOttaminenNollaaSaldon(){
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(10);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void merkkijonoEsitysOnOikea(){
        varasto.lisaaVarastoon(5);
        assertEquals("saldo = 5.0, vielä tilaa 5.0", varasto.toString());
    }
    

}