/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteestacionamento;

import br.com.dynatec.controlador.ws.ConsultaCartao;
import br.com.dynatec.controlador.ws.RetornoConsultaCartaoVO;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Service;
import java.io.StringReader;

/**
 *
 * @author jura
 */
public class ClienteEstacionamento {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RetornoConsultaCartaoVO r;
        r = processaCartao("123", "01/01/2014", 651, 1000d, 100d, 201, null);        
        System.out.println(r);
        System.out.println(r.getCartao());
    }

    private static RetornoConsultaCartaoVO processaCartao(java.lang.String cartao, java.lang.String dataTransasaoFinanceira, java.lang.Integer codTabela, java.lang.Double desconto, java.lang.Double valorRecebido, java.lang.Integer usuarioId, java.lang.String placaCarro) {
        br.com.dynatec.controlador.ws.AcessoControle_Service service = new br.com.dynatec.controlador.ws.AcessoControle_Service();
        br.com.dynatec.controlador.ws.AcessoControle port = service.getAcessoControlePort();
        return port.processaCartao(cartao, dataTransasaoFinanceira, codTabela, desconto, valorRecebido, usuarioId, placaCarro);
    }

    
}
