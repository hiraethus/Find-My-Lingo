package com.clackjones.cymraeg.user;

import org.springframework.stereotype.Component;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

@Component
class Sha256PasswordEncryption implements PasswordEncryption {

    @Override
    public void encryptPassword(RegistrationDetails regDetails) {
        String stringToEncrypt = appendSaltToPassword(regDetails);
        String hashedPassword = sha256Hex(stringToEncrypt);

        regDetails.setPassword(hashedPassword);
    }

    String appendSaltToPassword(RegistrationDetails regDetails) {
        return String.format("%s{%s}", regDetails.getPassword(), regDetails.getUsername());
    }
}
