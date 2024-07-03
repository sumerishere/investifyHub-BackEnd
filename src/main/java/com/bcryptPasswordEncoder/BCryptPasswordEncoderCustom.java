package org.springframework.security.crypto.bcrypt;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptPasswordEncoderCustom {

	 public String encode(String password) {
	        // Generate a salt for the password
	        String salt = BCrypt.gensalt();

	        // Hash the password with the generated salt
	        String hashedPassword = BCrypt.hashpw(password, salt);

	        // Return the hashed password
	        return hashedPassword;
	    }

	    public boolean matches(String rawPassword, String encodedPassword) {
	        // Use BCrypt's checkpw method to verify whether the raw password matches the encoded password
	        return BCrypt.checkpw(rawPassword, encodedPassword);
	    }
	
}
