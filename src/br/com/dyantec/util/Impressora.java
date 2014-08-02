/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dyantec.util;

import java.io.FileOutputStream;
import java.io.PrintStream;
import javax.swing.JOptionPane;

/**
 *
 * @author jura
 */
public class Impressora {

    public static void imprimir(String texto) {
        try {            
            Integer a;
            a = 0 / 10;
            StringBuffer str = new StringBuffer();

            char ABRENEGRITO = ((char) 27 + (char) 69);
            char FECHANEGRITO = ((char) 27 + (char) 70);

            str.append(texto.getBytes());
            str.append(Parametros.quebraLinhaFinal.getBytes());
            FileOutputStream outputFile = new FileOutputStream(Parametros.portaComBematech);
            PrintStream ps = new PrintStream(outputFile);
            ps.print(str);
            ps.close();
            outputFile.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro ao imprimir", JOptionPane.ERROR_MESSAGE); 
        }
    }

}
