package com.clackjones.cymraeg.registration;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class Sha256PasswordEncryptionTest {
    @Test
    public void shouldAppendUsernameBetweenCurlyBracketsToPassword() {
        // given
        RegistrationDetails registrationDetails = new RegistrationDetails();
        String user = "username";
        String pass = "pass";

        registrationDetails.setUsername(user);
        registrationDetails.setPassword(pass);
        registrationDetails.setPasswordSecondTimeEntered(pass);

        Sha256PasswordEncryption encryption = new Sha256PasswordEncryption();

        // when
        String concatenated = encryption.appendSaltToPassword(registrationDetails);

        // then
        assertThat(concatenated, equalTo(pass + "{" + user + "}"));
    }
}
