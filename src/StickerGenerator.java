import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator {
    
    public void criaImagem(InputStream inputStream, String nomeArquivo) throws IOException {

        // lê imagem
        // InputStream inputStream = new FileInputStream(new File("images/TopMovies_7.jpg"));
        // InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_7.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;        
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copia a imagem original pra nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, null, 0, 0);

        // configurar a fonte
        var fonte = new Font("Impact", Font.BOLD, 72);
        graphics.setFont(fonte);
        graphics.setColor(Color.YELLOW);

        // escreve uma frase na nova imagem
        String texto = "MANERO!";
        FontMetrics medidasDaFonte = graphics.getFontMetrics();
        Rectangle2D retangulo = medidasDaFonte.getStringBounds(texto, graphics);
        int larguraDoTexto = (int) retangulo.getWidth();
        int posicaoTexto = (largura - larguraDoTexto) / 2;
        graphics.drawString(texto, posicaoTexto, altura + 125);

        // grava a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }

}