/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dyantec.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.swing.JOptionPane;

/**
 *
 * @author jura
 */
public class Impressora {

    public static void imprimir() {
        String impressao = "TESTE DE IMPRESSAO";
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        DocPrintJob job = service.createPrintJob();

        DocFlavor.URL url = new DocFlavor.URL(impressao);
        DocFlavor flavor = DocFlavor.URL.GIF;
        Doc doc = new SimpleDoc(url, flavor, null);
        PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();
        attrs.add(new Copies(1));
        try {
            job.print(doc, attrs);
        } catch (PrintException ex) {
            Logger.getLogger(Impressora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public class ImprimeComprovantes {

        public void imprime(File file) {
            try {
                if (file.exists()) {
                    String[] portas = {"LPT1:", "LPT2:"};
                    String portaSelecionada = (String) JOptionPane.showInputDialog(null, "Informe a porta.", "Porta", JOptionPane.QUESTION_MESSAGE, null, portas, portas[0]);
                    if (portaSelecionada != null) {
                        java.io.InputStream is = new FileInputStream(file);
                        Scanner sc = new Scanner(is);
                        FileOutputStream fs = new FileOutputStream(portaSelecionada);
                        PrintStream ps = new PrintStream(fs);

                        while (sc.hasNextLine()) {
                            String linhas = sc.nextLine();
                            ps.println(linhas);
                        }
                        fs.close();
                        is.close();
                        sc.close();
                        file.delete();
                    } else {
                        file.delete();
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível imprimir comprovante, erro encontrado ao imprimir.", "Erro", JOptionPane.ERROR_MESSAGE);
                file.delete();
            }
        }
    }

}
