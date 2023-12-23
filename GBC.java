import java.awt.*;

/**
 * Custom extension of GridBagConstraints for GridBagLayout convenience.
 */
public class GBC extends GridBagConstraints
{

	/**
	 * Constructs a GBC with given grid position and size.
	 *
	 * @param x      The column index.
	 * @param y      The row index.
	 * @param width  The number of columns spanned.
	 * @param height The number of rows spanned.
	 */
	public GBC(int x, int y, int width, int height)
	{
		gridx = x;
		gridy = y;
		gridwidth = width;
		gridheight = height;
	}

	/**
	 * Sets the weight for this cell.
	 *
	 * @param weightX The horizontal weight.
	 * @param weightY The vertical weight.
	 * @return The GBC instance for method chaining.
	 */
	public GBC setWeight(int weightX, int weightY)
	{

		this.weightx = weightX;
		this.weighty = weightY;
		return this;
	}

	/**
	 * Sets the fill mode for this cell.
	 *
	 * @param fill The fill mode.
	 * @return The GBC instance for method chaining.
	 */
	public GBC setFill(int fill)
	{
		this.fill = fill;
		return this;
	}

	/**
	 * Sets the anchor for this cell.
	 *
	 * @param anchor The anchor value.
	 * @return The GBC instance for method chaining.
	 */
	public GBC setAnchor(int anchor)
	{
		this.anchor = anchor;
		return this;
	}

}
