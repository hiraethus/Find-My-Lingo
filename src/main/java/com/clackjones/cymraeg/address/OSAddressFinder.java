package com.clackjones.cymraeg.address;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Implements the AddressFinder interface by using data from
 * the Ordnance Survey's 'Open Names' and 'Code-Point Open' project.
 */
@Component
public class OSAddressFinder implements AddressFinder {

    @Override
    public boolean isValidPostcode(String postcode) {
        File postcodeDir = null;
        try {
            postcodeDir = retrievePostcodeDir();
        } catch (Exception e) {
            // postcode file not found
            e.printStackTrace();
            System.exit(1);
        }

        // remove whitespace
        String postcodeNoWhitespace = postcode.replaceAll("\\s+","").toUpperCase();

        // check letter/two-letters
        Pattern p = Pattern.compile("^(\\p{Alpha}\\p{Alpha})");
        Matcher m = p.matcher(postcodeNoWhitespace);

        String letterPrefix = null;

        if (!m.find()) {
            // TODO malformed postcode: Invalid prefix
            return false;
        }

        String postcodePrefix = m.group().toLowerCase();

        File[] matchedPostcodeFile = postcodeDir.listFiles(f -> f.getName().equals(postcodePrefix + ".csv"));
        if (matchedPostcodeFile.length == 0) {
            // postcode doesn't exist
            return false;
        }

        try {
            Stream<String> fileStream = Files.lines(matchedPostcodeFile[0].toPath());
            Optional<String> foundPostcode = fileStream.filter(line -> line.split(",")[0].equals("\"" + postcodeNoWhitespace + "\""))
                    .findFirst();

            return foundPostcode.isPresent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private File retrievePostcodeDir() throws FileNotFoundException, URISyntaxException {
        URL url = this.getClass().getClassLoader().getResource("codepo_gb/Data/CSV");
        File f = null;
        f = new File(url.toURI());
        if (f.exists()) {
            return f;
        } else {
            throw new FileNotFoundException("Could not find postcode folder");
        }
    }
}
