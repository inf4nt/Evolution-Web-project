package evolution.dao;


import evolution.model.dialog.Dialog;

/**
 * Created by Admin on 08.06.2017.
 */
public interface DialogDao  {

    Long save (Dialog dialog);

    void update(Dialog dialog);

    void delete(Dialog dialog);

    Dialog find(Dialog dialog);
}
