package com.clackjones.cymraeg.address.getthedata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    private String status;
    @JsonProperty("match_type")
    private String matchType;
    private String input;
    private Data data;
    private String[] copyright;

    public Result() { }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String[] getCopyright() {
        return copyright;
    }

    public void setCopyright(String[] copyright) {
        this.copyright = copyright;
    }
}
