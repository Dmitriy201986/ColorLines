import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelSaveActionListner implements ActionListener {
    public CancelSaveActionListner(OnCancelSaveCallBack cancelSaveCallBack){
        this.cancelSaveCallBack = cancelSaveCallBack;
    }
    private OnCancelSaveCallBack cancelSaveCallBack;
    @Override
    public void actionPerformed(ActionEvent e) {
        cancelSaveCallBack.invoke();
    }
}
