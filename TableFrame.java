import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;


/**
 * Represents a Swing JFrame containing a table and cell renderers.
 */
public class TableFrame extends JFrame
{
	TableModel myModel;

	/**
	 * Constructs a TableFrame with a table, custom renderers, and an exit button.
	 */
	public TableFrame()
	{
		this.setLayout(new GridBagLayout());

		// Initialize table model with data from file
		myModel =
				new TableModel("./SwingTable.txt");

		// Create JTable using the custom TableModel
		JTable table = new JTable(myModel);
		table.setGridColor(Color.BLACK);

		// Set default renderers for specific data types
		table.setDefaultRenderer(Color.class, new FavouriteColorRenderer());

		table.setDefaultRenderer(Integer.class, new BMICellRenderer());

		table.setDefaultRenderer(LocalDate.class, new DateCellRenderer());


		// Create and configure EXIT button
		JButton button = new JButton("EXIT");
		button.addActionListener(event -> System.exit(0));


		//this.setSize(640, 480);

		// Add components to the frame using GridBagLayout
		this.add(table,
				new GBC(0, 0, 1,2).
						setFill(GBC.BOTH).setWeight(100, 0).setAnchor(GBC.CENTER));

		this.add(button, new GBC(0, 2, 1, 1).
				setFill(GBC.BOTH).setWeight(100, 100));

		// Set frame properties and make it visible
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}


	// Custom TableCellRenderer for rendering favorite colors
	static class FavouriteColorRenderer extends JLabel implements TableCellRenderer
	{
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value,
		                                               boolean isSelected, boolean hasFocus, int row, int column)
		{
			this.setOpaque(true);
			Color color = (Color) value;
			this.setBackground(color);

			return this;
		}
	}

	// Custom TableCellRenderer for rendering BMI values
	class BMICellRenderer extends JLabel implements TableCellRenderer
	{

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value,
		                                               boolean isSelected, boolean hasFocus, int row, int column)
		{
			this.setOpaque(true);
			int valueInt = (int) value;
			this.setBackground(Color.WHITE);
			if (TableFrame.this.myModel.getColumnName(column).equals("BMI")){

				if (valueInt <= 18)
					this.setBackground(Color.YELLOW);

				else if (valueInt >= 25) this.setBackground(Color.RED);

			}
			this.setText(String.valueOf(valueInt));
			return this;
		}
	}

	// Custom TableCellRenderer for rendering LocalDate values
	static class DateCellRenderer extends JLabel implements TableCellRenderer
	{

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value,
		                                               boolean isSelected, boolean hasFocus, int row, int column)
		{
			LocalDate date = (LocalDate) value;
			this.setFont(new Font("Serif", Font.BOLD, 12));
			this.setText(date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.of("en")));
			return this;
		}
	}
}