package user;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * bookData:储存借阅书目信息
 * 
 * @author 蜂鸟Swift
 */
public class bookData {
	private final SimpleStringProperty bookName;
	private final SimpleStringProperty bookISBN;
	private final SimpleStringProperty bookClass;
	private final SimpleStringProperty bookAuthor;
	private final SimpleStringProperty bookTime;
	private final SimpleStringProperty bookLong;
	private final SimpleStringProperty bookPrice;

	public bookData(String bookName, String bookISBN, String bookClass, String bookAuthor, String bookTime,
			String bookLong, String bookPrice) {
		this.bookName = new SimpleStringProperty(bookName);
		this.bookISBN = new SimpleStringProperty(bookISBN);
		this.bookClass = new SimpleStringProperty(bookClass);
		this.bookAuthor = new SimpleStringProperty(bookAuthor);
		this.bookTime = new SimpleStringProperty(bookTime);
		this.bookLong = new SimpleStringProperty(bookLong);
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

	String getBookTime() {
		return bookTime.get();
	}

	public void setBookTime(String bookTime) {
		this.bookTime.set(bookTime);
	}

	public StringProperty bookTimeProperty() {
		return bookTime;
	}

	String getBookLong() {
		return bookLong.get();
	}

	public void setBookLong(String bookLong) {
		this.bookLong.set(bookLong);
	}

	public StringProperty bookLongProperty() {
		return bookLong;
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
