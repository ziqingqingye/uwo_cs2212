package western.covid.project.ui;

import western.covid.project.dispatch.observe.Observer;

import javax.swing.*;

/**
 * A customized class used to display analysis result.
 * It is required to implement Observer interface since it will be notified when changed happened.
 * @author Tianci Du
 * @version 1.0
 */
public class ResultTextArea extends JTextArea implements Observer {

    public ResultTextArea(){
        this.setEnabled(false);
        this.setText("calculation result display here.");
    }

    @Override
    public void update(Object obj) {
        this.setText("");
        if (obj!=null){
            this.append(String.valueOf(obj));
        }

    }
}
