import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

/**
 * Represents a custom Table Model to handle data for a Swing JTable.
 */
public class TableModel extends AbstractTableModel
{
	// Array containing column names for the table
	private final String[] columnNames =
	{
			"Name",
			"Last name",
			"Birth day",
			"Fav. colour",
			"Height [cm]",
			"Weight [kg]",
			"BMI"
	};

	// 2D array containing table data
	private final Object[][] data;

	/**
	 * Constructs a TableModel object by reading data from a file and initializing the table data.
	 *
	 * @param filePath The path to the file containing table data.
	 */
	public TableModel(String filePath)
	{

		try
		{
			// Read lines from the file
			List<String> lines = Files.readAllLines(Path.of(filePath));
			data = new Object[lines.size()][7];

			// Parse each line to populate table data
			for (int index = 0; index < lines.size(); index++)
			{
				String[] lineSplitted = lines.get(index).split("\\s+");
				String name = lineSplitted[0];
				String lastName = lineSplitted[1];

				// Parse birthdate components
				String[] dateSplitted = lineSplitted[2].split("-");
				int year = Integer.parseInt(dateSplitted[0]);
				int month = Integer.parseInt(dateSplitted[1]);
				int day = Integer.parseInt(dateSplitted[2]);

				LocalDate birthDay = LocalDate.of(year, month, day);

				// Parse favorite color components
				String[] colorSplitted = lineSplitted[3].split(",");
				int r = Integer.parseInt(colorSplitted[0]);
				int g = Integer.parseInt(colorSplitted[1]);
				int b = Integer.parseInt(colorSplitted[2]);

				Color favColour = new Color(r, g, b);

				// Parse height and weight to calculate BMI
				int height = Integer.parseInt(lineSplitted[4]);
				int weight = Integer.parseInt(lineSplitted[5]);
				int BMI = (int) Math.ceil((weight / (height / 100. * height / 100.)));

				// Populate data array for the table
				data[index][0] = name;
				data[index][1] = lastName;
				data[index][2] = birthDay;
				data[index][3] = favColour;
				data[index][4] = height;
				data[index][5] = weight;
				data[index][6] = BMI;
			}

		}
		catch (IOException e)
		{
			// Send the io exception information to the standard error output
			System.err.println("IO error when reading the file.");
			System.err.println(e.getMessage());
		}
	}

	// Overrides from AbstractTableModel
	@Override
	public int getRowCount()
	{
		return data.length;
	}

	// Overrides from AbstractTableModel
	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}

	// Overrides from AbstractTableModel
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		return data[rowIndex][columnIndex];
	}

	// Overrides from AbstractTableModel
	@Override
	public Class<?> getColumnClass(int c)
	{
		return data[0][c].getClass();
	}

	// Overrides from AbstractTableModel
	@Override
	public String getColumnName(int c)
	{
		return columnNames[c];
	}
}
