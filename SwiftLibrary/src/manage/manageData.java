package manage;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * manageData: ÈºÆºØ∫œ
 * 
 * @author ∑‰ƒÒSwift
 *
 */
public class manageData {
	private final SimpleStringProperty bookName;
	private final SimpleStringProperty bookISBN;
	private final SimpleStringProperty bookClass;
	private final SimpleStringProperty bookAuthor;
	private final SimpleStringProperty bookPress;
	private final SimpleStringProperty bookLeft;
	private final SimpleStringProperty bookTotal;
	private final SimpleStringProperty bookPrice;

	public manageData(String bookName, String bookISBN, String bookClass, String bookAuthor, String bookPress,
			String bookLeft, String bookTotal, String bookPrice) {
		this.bookName = new SimpleStringProperty(bookName);
		this.bookISBN = new SimpleStringProperty(bookISBN);
		this.bookClass = new SimpleStringProperty(bookClass);
		this.bookAuthor = new SimpleStringProperty(bookAuthor);
		this.bookPress = new SimpleStringProperty(bookPress);
		this.bookLeft = new SimpleStringProperty(bookLeft);
		this.bookTotal = new SimpleStringProperty(bookTotal);
		this.bookPrice = new SimpleStringProperty(bookPrice);
	}

	public String getBookName() {
		return bookName.get();
	}

	public void setBookName(String bookName) {
		this.bookName.set(bookName);
	}

	public StringProperty bookNameProperty() {
		return bookName;
	}

	String getBookISBN() {
		return bookISBN.get();
	}

	public void setBookISBN(String bookISBN) {
		this.bookISBN.set(bookISBN);
	}

	public StringProperty bookISBNProperty() {
		return bookISBN;
	}

	String getBookAuthor() {
		return bookAuthor.get();
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor.set(bookAuthor);
	}

	public StringProperty bookAuthorProperty() {
		return bookAuthor;
	}

	String getBookClass() {
		return bookAuthor.get();
	}

	public void setBookClass(String bookClass) {
		this.bookClass.set(bookClass);
	}

	public StringProperty bookClassProperty() {
		return bookClass;
	}

	String getBookPress() {
		return bookPress.get();
	}

	public void setBookPress(String bookPress) {
		this.bookPress.set(bookPress);
	}

	public StringProperty bookPressProperty() {
		return bookPress;
	}

	String getBookLeft() {
		return bookLeft.get();
	}

	public void setBookLeft(String bookLeft) {
		this.bookLeft.set(bookLeft);
	}

	public StringProperty bookLeftProperty() {
		return bookLeft;
	}

	String getBookTotal() {
		return bookTotal.get();
	}

	public void setBookTotal(String bookTotal) {
		this.bookTotal.set(bookTotal);
	}

	public StringProperty bookTotalProperty() {
		return bookTotal;
	}

	String getBookPrice() {
		return bookPrice.get();
	}

	public void setBookPrice(String bookPrice) {
		this.bookPrice.set(bookPrice);
	}

	public StringProperty bookPriceProperty() {
		return bookPrice;
	}
}
