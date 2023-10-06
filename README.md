# Apache Druid Open Policy Agent (OPA) Authorizer

An Apache Druid extension to request policy decisions from [Open Policy Agent](https://www.openpolicyagent.org/) (OPA).

## Supported Druid versions

This project was tested against these Druid versions:

- 0.23.0
- 24.0.0
- 26.0.0
- 27.0.0

## Building

This repository uses Maven and requires at least Java 11 to build:

        mvn clean package

The result of this is a JAR file in the `target` directory.

## Installing

Copy the JAR file into the `extensions` directory of your Druid installation.

## Configuration Settings

The OPA authorizer is created like so:

        druid.auth.authorizer.myOpaAuth.type=opa
        druid.auth.authorizer.myOpaAuth.opaUri=http://<host>:<port>/v1/data/my/druid/allow

Then the `myOpaAuth` authorizer needs to be referenced in your authenticator.

## How to Write Your RegoRules

The authorizer will send a request to the `uri` specified in the config. The input will be:

        {
            user: <String: user name>
            action: <String: READ|WRITE>
            resource: {
                name: <String>
                type: <String>
            }
        }

For the details - especially the kind of resources - consult the Druid documentation on the [Authentication and Authorization Model](https://druid.apache.org/docs/latest/operations/security-user-auth.html#authentication-and-authorization-model).

Inside your RegoRules, this snippet of data will be available as `input`. For the details on how to write RegoRule, have a look at the [OPA documentation](https://www.openpolicyagent.org/docs/latest/).

### Example

For a simple example, have a look inside the `example` directory.

## Troubleshooting

If you get 500 type errors it might be that the internal `druid_system` user doesn't have full permissions.

You can increase log output for the authorizer by adding this snippet to your `log4j.xml`:

        <Logger name="tech.stackable.druid.opaauthorizer.OpaAuthorizer" level="trace" additivity="false">
          <Appender-ref ref="Console"/>
        </Logger>

