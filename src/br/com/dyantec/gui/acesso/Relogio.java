/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dyantec.gui.acesso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author jura
 */
class Relogio extends Observable implements Runnable {

    private String horas;

    public Relogio(Observer obs) {
        this.addObserver(obs);
    }

    @Override
    public void notifyObservers() {
        super.setChanged();
        super.notifyObservers();
    }

    @Override
    public void run() {
        while (true) {
            this.horas = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(Calendar.getInstance().getTime());
            this.notifyObservers();
        }
    }

    public String getHoras() {
        return horas;
    }
}
