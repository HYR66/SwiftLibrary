package manage;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * userData:用户数据集合
 * 
 * @author 蜂鸟Swift
 *
 */
public class userData {
	private final SimpleStringProperty userName;
	private final SimpleStringProperty userTrue;
	private final SimpleStringProperty userPhone;
	private final SimpleStringProperty userCredit;
	private final SimpleStringProperty userValid;
	private final SimpleStringProperty userTime;

	userData(String userName, String userTrue, String userPhone, String userCredit, String userValid, String userTime) {
		this.userName = new SimpleStringProperty(userName);
		this.userTrue = new SimpleStringProperty(userTrue);
		this.userPhone = new SimpleStringProperty(userPhone);
		this.userCredit = new SimpleStringProperty(userCredit);
		this.userValid = new SimpleStringProperty(userValid);
		this.userTime = new SimpleStringProperty(userTime);
	}

	String getUserName() {
		return userName.get();
	}

	void setUserName(String userName) {
		this.userName.set(userName);
	}

	public StringProperty userNameProperty() {
		return userName;
	}

	String getUserTrue() {
		return userTrue.get();
	}

	void setUserTrue(String userTrue) {
		this.userTrue.set(userTrue);
	}

	public StringProperty userTrueProperty() {
		return userTrue;
	}

	String getUserPhone() {
		return userPhone.get();
	}

	void setUserPhone(String userPhone) {
		this.userPhone.set(userPhone);
	}

	public StringProperty userPhoneProperty() {
		return userPhone;
	}

	String getUserCredit() {
		return userCredit.get();
	}

	void setRightCount(String userCredit) {
		this.userCredit.set(userCredit);
	}

	public StringProperty userCreditProperty() {
		return userCredit;
	}

	String getUserValid() {
		return userValid.get();
	}

	void setUserValid(String userValid) {
		this.userValid.set(userValid);
	}

	public StringProperty userValidProperty() {
		return userValid;
	}

	String getUserTime() {
		return userTime.get();
	}

	void setUserTime(String userTime) {
		this.userTime.set(userTime);
	}

	public StringProperty userTimeProperty() {
		return userTime;
	}
}
