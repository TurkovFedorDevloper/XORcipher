package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField massField;

    @FXML
    private PasswordField keyField;

    @FXML
    private TextField shifrMassField;

    @FXML
    private Button shifrButton;

    @FXML
    private Button deletButton;

    @FXML
    void initialize() {
        shifrButton.setOnAction(event -> {

            char[] massFieldChar = massField.getText().toCharArray();
            char[] keyFieldChar = keyField.getText().toCharArray();

            int msgLen = massFieldChar.length, i, j;
            int binaryIntMsg, binaryIntKey;
            char[] newKey = new char[msgLen];
            int decimalNewLetter;
            char newLetter;

            String encryptedLetterMsg = "";
            String encryptedLetterMsgStr = "";

            for (i = 0, j = 0; i < msgLen; ++i, ++j) {
                if (j == keyFieldChar.length) j = 0;
                newKey[i] = keyFieldChar[j];
            }

            for (i = 0; i < msgLen; ++i) {
                binaryIntMsg = (massFieldChar[i] - 32);
                binaryIntKey = newKey[i];
                String binaryStrMsg = Integer.toBinaryString(binaryIntMsg);

                while (binaryStrMsg.length() < 7) binaryStrMsg = "0" + binaryStrMsg;

                String binaryStrKey = Integer.toBinaryString(binaryIntKey);

                for (int iSecond = 0; iSecond < binaryStrMsg.length(); iSecond++) {
                    if (binaryStrKey.charAt(iSecond) != binaryStrMsg.charAt(iSecond)) {
                        encryptedLetterMsg += "1";
                    } else if (binaryStrKey.charAt(iSecond) == binaryStrMsg.charAt(iSecond)) {
                        encryptedLetterMsg += "0";
                    }

                    if (iSecond >= binaryStrMsg.length() - 1) {
                        decimalNewLetter = Integer.parseInt(encryptedLetterMsg, 2);
                        newLetter = (char) decimalNewLetter;
                        encryptedLetterMsgStr += newLetter;
                        encryptedLetterMsg = "";
                    }
                }
            }
            shifrMassField.setText(encryptedLetterMsgStr);
        });

        deletButton.setOnAction(event -> {
            massField.setText(null);
            shifrMassField.setText(null);
            keyField.setText(null);
        });
    }
}
