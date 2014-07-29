/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dyantec.util;

import br.com.dynatec.controlador.ws.RetornoConsultaCartaoVO;
import br.com.dynatec.controlador.ws.TipoMovimento;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author jura
 */
public class Util {

    public static Date strToDate(String data) throws ParseException {
        SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
        return dateformatDDMMYYYY.parse(data);
    }

    public static String dateToString(Date data) {
        if (data != null) {
            SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
            return dateformatDDMMYYYY.format(data);
        } else {
            return null;
        }
    }

    public static String dateTimeToString(Date data) {
        SimpleDateFormat dateformatDDMMYYYYHHNNSS = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateformatDDMMYYYYHHNNSS.format(data);
    }
    
    public static double diferencaEmMinutos(Date dataInicial, Date dataFinal) {
        long diferenca = dataFinal.getTime() - dataInicial.getTime();
        double diferencaEmMinutos = (diferenca / 1000) / 60; //resultado é diferença entre as datas em minutos              
        return diferencaEmMinutos;
    }

    public static String xmlGregorianCalendarToStr(XMLGregorianCalendar data) {
        return Util.dateTimeToString(Util.toDate(data));
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
        } catch (DatatypeConfigurationException ex) {
            ex.printStackTrace();
        }
        return xmlCalendar;
    }

    public static Date toDate(XMLGregorianCalendar calendar) {
        if (calendar == null) {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();
    }

    public static String tipoMovimentoToStr(TipoMovimento tipoMovimento) {
        return tipoMovimento == TipoMovimento.DEPOSITO ? "Depósito" : "Retirada";
    }

    public static TipoMovimento strToTipoMovimento(String tipoMovimento) {
        return tipoMovimento.equals("DEPOSITO") ? TipoMovimento.DEPOSITO : TipoMovimento.RETIRADA;
    }

    public static StringBuilder cupom(RetornoConsultaCartaoVO obj) {
        StringBuilder retorno = new StringBuilder();

        retorno.append("<html>");
        retorno.append("<head>");
        retorno.append("</head>");
        retorno.append("<body>");
        retorno.append("<p style='margin-top: 0'>");

        if (obj.isMensalista()) {
            retorno.append("PAGAMENTO MENSALIDADE").append("<br/><br/>");
            retorno.append("CPF: ").append(obj.getCpf()).append("<br/>");
            retorno.append("Mensalista: ").append(obj.getNomeMensalista()).append("<br/>");
            retorno.append("Mensalidade: ").append(obj.getValorReceber()).append("<br/>");
            retorno.append("Desconto: ").append(obj.getDescontoAtual()).append("<br/>");
            retorno.append("Valor Recebido: ").append(obj.getValorRecebido()).append("<br/>");
            retorno.append("Troco: ").append(obj.getTroco()).append("<br/>");
            retorno.append("Data Pagamento: ").append(Util.dateToString(new Date())).append("<br/>");
            retorno.append("Proxímo vencimento: ").append(Util.xmlGregorianCalendarToStr(obj.getProximoVencimento())).append("<br/>");
        } else {
            retorno.append("Entrada: ").append(Util.xmlGregorianCalendarToStr(obj.getEntrada())).append("<br/>");
            retorno.append("Saida: ").append(Util.xmlGregorianCalendarToStr(obj.getSaida())).append("<br/>");
            retorno.append("Permanência: ").append(obj.getPermanencia()).append("<br/>");
            retorno.append("Limite para sair: ").append(Util.xmlGregorianCalendarToStr(obj.getLimiteParaSair())).append("<br/>");

            retorno.append("Valor cobrado: ").append(obj.getValorCobrado()).append("<br/>");
            retorno.append("Valor já pago: ").append(obj.getUltimoValorPago()).append("<br/>");
            retorno.append("Desconto: ").append(obj.getDesconboAcumulado()).append("<br/>");
            retorno.append("Valor a receber: ").append(obj.getValorReceber()).append("<br/>");
            retorno.append("Valor recebido: ").append(obj.getValorRecebido()).append("<br/>");
            retorno.append("Troco: ").append(obj.getTroco()).append("<br/>");
            retorno.append("Placa do veiculo: ").append(obj.getPlacaVeiculo()).append("<br/>");
        }

        retorno.append("</p>");
        retorno.append("</body>");
        retorno.append("</html>");
        return retorno;
    }

    public static void addHotKey(javax.swing.JFrame frame, final javax.swing.JComponent jComponent, Integer keyEvent) {
        InputMap inputMap = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(keyEvent, 0), "forward");
        frame.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
        frame.getRootPane().getActionMap().put("forward", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (jComponent instanceof JButton) {
                    JButton btn = (JButton) jComponent;
                    btn.doClick();
                }
            }
        });
    }

}
