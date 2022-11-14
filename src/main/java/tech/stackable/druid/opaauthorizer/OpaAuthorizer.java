package tech.stackable.druid.opaauthorizer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tech.stackable.druid.opaauthorizer.opatypes.OpaMessage;
import tech.stackable.druid.opaauthorizer.opatypes.OpaResponse;
import org.apache.druid.server.security.*;
import org.apache.druid.java.util.common.logger.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@JsonTypeName("opa")
public class OpaAuthorizer implements Authorizer {
    private static final Logger log = new Logger(OpaAuthorizer.class);
    private final String name;
    private final String opaUri;
    private final ObjectMapper objectMapper;

    @JsonCreator
    public OpaAuthorizer(
        @JsonProperty("name") String name,
        @JsonProperty("opaUri") String opaUri
    ) {
        this.name = name;
        this.opaUri = opaUri;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Access authorize(AuthenticationResult authenticationResult, Resource resource, Action action) {
        log.debug(
                "Authorizing %s for %s on %s",
                authenticationResult.getIdentity(),
                action.name(),
                resource.toString()
        );
        log.trace("Creating OPA request JSON.");
        OpaMessage msg = new OpaMessage(
                authenticationResult.getIdentity(),
                action.name(),
                resource.getName(),
                resource.getType().toString()
        );
        String msgJson;
        try {
            msgJson = this.objectMapper.writeValueAsString(msg);
        } catch (JsonProcessingException e) {
            return new Access(false, "Failed to create the OPA request JSON: " + e);
        }

        log.trace("Creating HTTP Client and executing post.");
        var client = HttpClient.newHttpClient();
        try {
            var request = HttpRequest.newBuilder()
                    .uri(new URI(this.opaUri))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(msgJson))
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            log.debug("OPA Response code: %s - %s", response.statusCode(), response.body());
            log.trace("Parsing OPA response.");
            OpaResponse opaResponse = this.objectMapper.readValue(response.body(), OpaResponse.class);
            if (opaResponse.result) {
                return Access.OK;
            } else {
                return new Access(false, "Access denied.");
            }

        } catch (Exception e) {
            return new Access(false, "An error occurred: " + e);
        }
    }
}
