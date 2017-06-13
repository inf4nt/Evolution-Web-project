package evolution.dao;


import evolution.model.dialog.Dialog;

/**
 * Created by Admin on 08.06.2017.
 */
public interface DialogDao extends DefaultDao {

    Long save (Dialog dialog);

    Dialog find(Dialog dialog);

}
