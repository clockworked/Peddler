import javax.swing.table.DefaultTableModel;


public class InvTableModel extends DefaultTableModel {
  public InvTableModel(Object[][] data, Object[] columnNames) {
    super(data, columnNames);
  }
  @Override
  public boolean isCellEditable(int row, int column) {
    return false;
  }
}
