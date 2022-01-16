import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveActionListner implements ActionListener {
    private SaveCallBack saveCallBack;

    public SaveActionListner(SaveCallBack saveCallBack){
        this.saveCallBack = saveCallBack;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        saveCallBack.invokeSave();
    }
}
