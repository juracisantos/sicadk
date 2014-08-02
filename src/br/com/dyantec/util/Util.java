/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dyantec.util;

import br.com.dyantec.type.TipoImpressao;
import br.com.dynatec.controlador.ws.RetornoConsultaCartaoVO;
import br.com.dynatec.controlador.ws.TipoMovimento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
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

    public static StringBuilder cupomMovimentoCaixa(TipoMovimento tm, Double valor, Date data, String observacao, Integer operadorID, String operadorNome) {
        StringBuilder sb = new StringBuilder();
        sb.append("MOVIMENTO DE CAIXA").append("\n");
        sb.append("\n");
        sb.append("Tipo..........:").append(tm.value()).append("\n");
        sb.append("Valor.........:").append(String.valueOf(valor)).append("\n");
        sb.append("Data..........:").append(Util.dateToString(data)).append("\n");
        sb.append("Obs...........:").append(observacao).append("\n");
        sb.append("Operador ID...:").append(operadorID).append("\n");
        sb.append("Operador Nome.:").append(operadorNome);
        
        return sb;
    }
    
    public static StringBuilder cupom(RetornoConsultaCartaoVO obj, TipoImpressao tp) {
        StringBuilder retorno = new StringBuilder();
        Util.setHeader(retorno, tp, null);

        if (tp.equals(TipoImpressao.BEMATHEC_TEXT)) {
            if (obj.isMensalista()) {
                retorno.append("PAGAMENTO MENSALIDADE").append("\n");
                retorno.append("CPF................:").append(obj.getCpf()).append("\n");
                retorno.append("Mensalista.........:").append(obj.getNomeMensalista()).append("\n");
                retorno.append("Mensalidade........:").append(obj.getValorReceber()).append("\n");
                retorno.append("Desconto...........:").append(obj.getDescontoAtual()).append("\n");
                retorno.append("Valor Recebido.....:").append(obj.getValorRecebido()).append("\n");
                retorno.append("Troco..............:").append(obj.getTroco()).append("\n");
                retorno.append("Data Pagamento.....:").append(Util.dateToString(new Date())).append("\n");
                retorno.append("Proxímo vencimento.:").append(Util.xmlGregorianCalendarToStr(obj.getProximoVencimento())).append("\n");
            } else {
                retorno.append("Entrada............:").append(Util.xmlGregorianCalendarToStr(obj.getEntrada())).append("\n");
                retorno.append("Saida..............:").append(Util.xmlGregorianCalendarToStr(obj.getSaida())).append("\n");
                retorno.append("Permanência........:").append(obj.getPermanencia()).append("\n");
                retorno.append("Limite para sair...:").append(Util.xmlGregorianCalendarToStr(obj.getLimiteParaSair())).append("\n");

                retorno.append("Valor cobrado......:").append(obj.getValorCobrado()).append("\n");
                retorno.append("Valor já pago......:").append(obj.getUltimoValorPago()).append("\n");
                retorno.append("Desconto...........:").append(obj.getDesconboAcumulado()).append("\n");
                retorno.append("Valor a receber....:").append(obj.getValorReceber()).append("\n");
                retorno.append("Valor recebido.....:").append(obj.getValorRecebido()).append("\n");
                retorno.append("Troco..............:").append(obj.getTroco()).append("\n");
                retorno.append("Placa do veiculo...:").append(obj.getPlacaVeiculo()).append("\n");
            }
        } else {
            if (obj.isMensalista()) {
                retorno.append("<tr><td colspan='2'>PAGAMENTO MENSALIDADE</td></tr>");
                retorno.append("<tr><td>CPF................:</td>").append("<td>").append(obj.getCpf()).append("</td></tr>");
                retorno.append("<tr><td>Mensalista.........:</td>").append("<td>").append(obj.getNomeMensalista()).append("</td></tr>");
                retorno.append("<tr><td>Mensalidade........:</td>").append("<td>").append(obj.getValorReceber()).append("</td></tr>");
                retorno.append("<tr><td>Desconto...........:</td>").append("<td>").append(obj.getDescontoAtual()).append("</td></tr>");
                retorno.append("<tr><td>Valor Recebido.....:</td>").append("<td>").append(obj.getValorRecebido()).append("</td></tr>");
                retorno.append("<tr><td>Troco..............:</td>").append("<td>").append(obj.getTroco()).append("</td></tr>");
                retorno.append("<tr><td>Data Pagamento.....:</td>").append("<td>").append(Util.dateToString(new Date())).append("</td></tr>");
                retorno.append("<tr><td>Proxímo vencimento.:</td>").append("<td>").append(Util.xmlGregorianCalendarToStr(obj.getProximoVencimento())).append("</td></tr>");
            } else {
                retorno.append("<tr><td colspan='2'>ESTACIONAMENTO</td></tr>");
                retorno.append("<tr><td>Entrada             </td>").append("<td>").append(Util.xmlGregorianCalendarToStr(obj.getEntrada())).append("</td></tr>");
                retorno.append("<tr><td>Saida               </td>").append("<td>").append(Util.xmlGregorianCalendarToStr(obj.getSaida())).append("</td></tr>");
                retorno.append("<tr><td>Permanência         </td>").append("<td>").append(obj.getPermanencia()).append("</td></tr>");
                retorno.append("<tr><td>Limite para sair    </td>").append("<td>").append(Util.xmlGregorianCalendarToStr(obj.getLimiteParaSair())).append("</td></tr>");

                retorno.append("<tr><td>Valor cobrado       </td>").append("<td>").append(obj.getValorCobrado()).append("</td></tr>");
                retorno.append("<tr><td>Valor já pago       </td>").append("<td>").append(obj.getUltimoValorPago()).append("</td></tr>");
                retorno.append("<tr><td>Desconto            </td>").append("<td>").append(obj.getDesconboAcumulado()).append("</td></tr>");
                retorno.append("<tr><td>Valor a receber     </td>").append("<td>").append(obj.getValorReceber()).append("</td></tr>");
                retorno.append("<tr><td>Valor recebido      </td>").append("<td>").append(obj.getValorRecebido()).append("</td></tr>");
                retorno.append("<tr><td>Troco               </td>").append("<td>").append(obj.getTroco()).append("</td></tr>");
                retorno.append("<tr><td>Placa do veiculo    </td>").append("<td>").append(obj.getPlacaVeiculo()).append("</td></tr>");
            }
        }

        Util.setFooter(retorno, tp, null);
        return retorno;
    }

    public static void setHeader(StringBuilder sb, TipoImpressao ti, String text) {
        if ((!"".equals(text)) && (text != null)) {
            if (ti.equals(TipoImpressao.BEMATHEC_TEXT)) {
                sb.append(text);
            } else {
                sb.append("<html>");
                sb.append("<head>");
                sb.append("</head>");
                sb.append("<body>");
                sb.append("<p style='margin-top: 0'>");
                sb.append("<table>");
                sb.append(text);
            }
        }
    }

    public static void setFooter(StringBuilder sb, TipoImpressao ti, String text) {
        if ((!"".equals(text)) && (text != null)) {
            if (ti.equals(TipoImpressao.BEMATHEC_TEXT)) {
                sb.append(text);
            } else {
                sb.append("</table>");
                sb.append("</p>");
                sb.append("</body>");
                sb.append("</html>");
                sb.append(text);
            }
        }
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
