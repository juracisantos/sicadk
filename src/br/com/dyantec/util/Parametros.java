package br.com.dyantec.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.xml.ws.WebServiceException;

public class Parametros {

    private static final Locale locale = new Locale("pt", "BR");
    private static final ResourceBundle resource;
    public static URL WSDL_WEBSERVICE;

    static {
        resource = ResourceBundle.getBundle("ApplicationProperties_pt_BR", locale);
        setWsdlWebService();
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

}
