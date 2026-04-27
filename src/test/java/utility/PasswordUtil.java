package utility;

import java.util.Base64;

public class PasswordUtil {
	public static String decode(String encodedPassword) {
        return new String(Base64.getDecoder().decode(encodedPassword));
    }

    // Encode plain password (one time use)
    public static String encode(String plainPassword) {
        return Base64.getEncoder()
                .encodeToString(plainPassword.getBytes());
    }
}
