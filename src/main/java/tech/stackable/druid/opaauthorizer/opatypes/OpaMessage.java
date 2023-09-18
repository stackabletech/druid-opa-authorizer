package tech.stackable.druid.opaauthorizer.opatypes;

public class OpaMessage {
  public OpaInput input;

  public OpaMessage(String user, String action, String resourceName, String resourceType) {
    this.input = new OpaInput(user, action, resourceName, resourceType);
  }
}
