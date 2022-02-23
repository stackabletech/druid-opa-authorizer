# Druid Opa Authorizer

A Druid extension to request policy decisions from the [Open Policy Agent](https://www.openpolicyagent.org/) (OPA).

## Building
Simply building the library:

    ./gradlew build

Packaging it with dependencies:

    ./gradlew distZip

## Installing
Build the distribution (zip with all the dependencies) and put it all in the extensions directory.

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

    <Logger name="de.stackable.druid.opaauthorizer.OpaAuthorizer" level="trace" additivity="false">
      <Appender-ref ref="Console"/>
    </Logger>