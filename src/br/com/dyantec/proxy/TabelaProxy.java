/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.dyantec.proxy;

import br.com.dynatec.controlador.ws.Tabela;

/**
 *
 * @author jura
 */
public class TabelaProxy extends Tabela {
    @Override
    public String toString() {
        return super.getNome();
    }    
}
