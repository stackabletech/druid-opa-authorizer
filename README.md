# Apache Druid Open Policy Agent (OPA) Authorizer

An Apache Druid extension to request policy decisions from [Open Policy Agent](https://www.openpolicyagent.org/) (OPA).

## Supported Druid versions

This project was tested against these Druid versions:

- 30.0.0
- 30.0.1
- 31.0.1

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
            authenticationResult: {
                identity: <String: user name>
                authorizerName: <String>
                authenticatedBy: <String>
                context: Map<String, Object>
            }
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

## Development

### How to add support for a new version

1. Add a new profile and get the dependency version from the upstream Druid POM
2. Add the new profile to the `requireActiveProfile` enforcer rule
3. Update `.github/workflows/maven.yml` to include the new profile in CI
4. Update `README.md` to name the newly supported version
5. _After_ the PR has been merged update the GitHub settings to require the new Druid version to pass

### Release

To release this run the relevant [GitHub Action](https://github.com/stackabletech/druid-opa-authorizer/actions/workflows/release.yml) which asks for various inputs.
It will then create a release in GitHub and upload all artifacts.
