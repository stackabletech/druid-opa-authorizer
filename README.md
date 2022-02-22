# Druid Opa Authorizer

by Stackable.

## Building
Simply building the library:

    ./gradlew build

Packaging it with dependencies:

    ./gradlew distZip

## Installing
Build the distribution (zip with all the dependencies) and put it all in the extensions directory.

## Configuration Settings
In the Druid `runtime.properties`:

    druid.auth.authorizer.myOpaAuth.type=opa
    druid.auth.authorizer.myOpaAuth.opaUri=http://<host>:<port>/v1/data/my/druid/allow

## How to Write Your RegoRules

The authorizer will send a request to the `uri` specified in the config. The input will be:

    {
        user: <user identity>
        action: <READ/WRITE>
        resource: {
            name: "MyTable"
            type: "DATASOURCE"
        }
    }

The above is an example.

TODO: specify further/better

## Troubleshooting

If you get 500 type errors it might be that the internal `druid_system` user doesn't have full permissions.