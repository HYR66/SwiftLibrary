package utils;

import java.util.List;

import javafx.scene.control.TextField;

/**
 * AutoCompleteTextFieldBuilder:���������򹹽���
 * 
 * @author ����Swift
 *
 */
public class AutoCompleteTextFieldBuilder {
	public static final AutoCompleteTextField build(TextField textField, List<String> cacheData) {
		return new AutoCompleteTextField(textField, cacheData);
	}

	public static final AutoCompleteTextField build(TextField textField) {
		return new AutoCompleteTextField(textField);
	}

}
