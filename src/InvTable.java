import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

public class InvTable extends JTable {
  public InvTable(ArrayList<ItemStack> inventory) {
    super(modelFromInventory(inventory));
    setShowGrid(true);
    setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    clearSelection();
  }
  public static InvTableModel modelFromInventory(ArrayList<ItemStack> inventory) {
    Object[][] rows = new String[inventory.size()][4];
    
    for (int y=0; y<inventory.size(); y++) {
      ItemStack i = inventory.get(y);
      rows[y][0] = i.getName();
      rows[y][1] = ""+i.getQuantity();
      rows[y][2] = i.getQuality();
      rows[y][3] = ""+i.getPrice();
    }
    Object[] colNames = {"Name", "Quantity", "Quality", "Price"};
    return new InvTableModel(rows, colNames);
  }
  
}
