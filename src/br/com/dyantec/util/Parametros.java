package br.com.dyantec.util;

import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Parametros {

    private static final Locale locale = new Locale("pt", "BR");
    private static final ResourceBundle resource;
    public static URL WSDL_WEBSERVICE;
    public static String portaComBematech;
    public static String quebraLinhaFinal;

    static {
        resource = ResourceBundle.getBundle("ApplicationProperties_pt_BR", locale);
        setWsdlWebService();
        setPortaUSBBematech();
        setquebraLinhaFinal();
    }

    private static String getParametroEmApplicationResources(String chave) {
        String valor = null;
        try {
            valor = ((String) resource.getObject(chave));
        } catch (MissingResourceException e) {

        }

        return valor;
    }

    private static void setWsdlWebService() {
        try {
            if (WSDL_WEBSERVICE == null) {
                WSDL_WEBSERVICE = new URL(getParametroEmApplicationResources("wsdl_webservice_url"));
            }
        } catch (Exception e) {
            WSDL_WEBSERVICE = null;
            throw new RuntimeException("Os parametros do sistema não estão corretamente configurados.");
        }
    }
    
    private static void setPortaUSBBematech() {
        try {
            portaComBematech = getParametroEmApplicationResources("porta_com_bematech");
            if ("".equals(portaComBematech)) {
                portaComBematech = "COM4:";
            }
        } catch (Exception e) {
            portaComBematech = "COM4:";
            throw new RuntimeException("Os parametros do sistema não estão corretamente configurados.");
        }
    }
    
    private static void setquebraLinhaFinal() {
        try {
            quebraLinhaFinal = getParametroEmApplicationResources("quebra_linha_final");
            if ("".equals(quebraLinhaFinal)) {
                quebraLinhaFinal = "\n\n\n:";
            }
        } catch (Exception e) {
            quebraLinhaFinal = "COM4:";
            throw new RuntimeException("Os parametros do sistema não estão corretamente configurados.");
        }
    }


}
