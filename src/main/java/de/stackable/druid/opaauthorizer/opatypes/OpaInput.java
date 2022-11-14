package de.stackable.druid.opaauthorizer.opatypes;

public class OpaInput {
    public String user;
    public String action;
    public OpaResource resource;

    public OpaInput(String user, String action, String resourceName, String resourceType) {
        this.user = user;
        this.action = action;
        this.resource = new OpaResource(resourceName, resourceType);
    }
}
