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
 * Implements the LocationService interface by using data from
 * the Ordnance Survey's 'Open Names' and 'Code-Point Open' project.
 */
@Component
public class OSLocationService implements LocationService {

    @Override
    public Optional<GeoLocation> findLocationForPostcode(String postcode) {
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
            return Optional.empty();
        }

        String postcodePrefix = m.group().toLowerCase();

        File[] matchedPostcodeFile = postcodeDir.listFiles(f -> f.getName().equals(postcodePrefix + ".csv"));
        if (matchedPostcodeFile.length == 0) {
            // postcode doesn't exist
            return Optional.empty();
        }

        try {
            Stream<String> fileStream = Files.lines(matchedPostcodeFile[0].toPath());
            Optional<GeoLocation> foundPostcode = fileStream
                    .map(line -> line.split(","))
                    .filter(record -> postcodesMatching(record[0], postcodeNoWhitespace))
                    .findFirst()
                    .map(record -> recordToGeoLocation(record));

            return foundPostcode;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    private static GeoLocation recordToGeoLocation(String[] record) {
        String postcode = record[0].substring(1, record[0].length() - 1); // trim quotes
        long eastings = Long.valueOf(record[2]);
        long northings = Long.valueOf(record[3]);
        return new GeoLocation(postcode, eastings, northings);
    }

    private static boolean postcodesMatching(String thisPostcode, String thatPostcode) {
        String cleanedThis = thisPostcode.replaceAll("[^\\p{Alnum}]+", "").toLowerCase();
        String cleanedThat = thatPostcode.replaceAll("[^\\p{Alnum}]+", "").toLowerCase();

        return cleanedThis.equals(cleanedThat);
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
