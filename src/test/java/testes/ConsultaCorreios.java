package testes;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ConsultaCorreios {

    @Test
    public void testesConsulta() throws IOException {
        //Abrir navegador
        System.setProperty("webDriver.chrome.driver", "src/test");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().window().maximize();

        //acessar site correios
        navegador.get("https://www.correios.com.br/");

        //digitar cep invalido
        navegador.findElement(By.name("relaxation")).sendKeys("80700000");

        //clicar botao pesquisar cep
        navegador.findElement(By.cssSelector("#content > div.mais-acessados > div > div.card-destaque-normal.flex > div:nth-child(2) > form > div.campo > button > i")).click();

        //altera para nova aba
        String originalWindow = navegador.getWindowHandle();
        for (String windowHandle : navegador.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                navegador.switchTo().window(windowHandle);
                break;
            }
       }


        //validando cep nao encontrado
        WebElement msg = navegador.findElement(By.xpath("//*[@id=\"mensagem-resultado-alerta\"]"));
        assertEquals(msg.getText(), "Dados não encontrado");


        //home
        navegador.findElement(By.xpath("//section[@id='menu']/a[2]")).click();

        //digitar cep valido
        navegador.findElement(By.name("relaxation")).sendKeys("01013001");


        //clicar botao pesquisar cep
        navegador.findElement(By.cssSelector("#content > div.mais-acessados > div > div.card-destaque-normal.flex > div:nth-child(2) > form > div.campo > button > i")).click();

        //digitar codigo rastreamento
        navegador.findElement(By.name("objetos")).sendKeys("SS987654321BR");

        //clicar botao pesquisar codigo
        navegador.findElement(By.xpath("//article[@id='content']/div[3]/div/div[2]/div/form/div[2]/button/i")).click();


        //print tela
        File scrFile = ((TakesScreenshot) navegador).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./target/evidencia.png"));

        //fechar navegador
        navegador.quit();

    }
}
