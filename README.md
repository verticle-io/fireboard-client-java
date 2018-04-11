# fireboard-client-java
A Java client library to create and post status messages to Fireboard (https://fireboard.verticle.io)
.

## Setup & Usage

### Requirements

The library requires a JDK which supports the letsencrypt CA:

 * Java 7 >= 7u111 or
 * Java 8 >= 8u101 or
 * Java 9

Dependencies: none

The lib is written as low level as possible, without any dependencies and potentially compatible with JDK 6. Note, that the above JDKs are supported because of the letscencrypt CA cert. If you want to use a version below you have to import the CA certificate into your truststore.

### Maven

This library can be retrieved via Jitpack:

```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

```
<dependency>
    <groupId>com.github.verticleio</groupId>
    <artifactId>fireboard-client-java</artifactId>
    <version>LATEST</version>
</dependency>
```

### Configuration
Configuration is read from System.properties.


Mandatory properties:
* `fireboard.tenant` - your tenant ID
* `fireboard.bucket` - the bucket you want to store the status to
* `fireboard.api.token` - your API token (open Fireboard UI to retrieve it)

Optional:
* `fireboard.api.endpoint` - is preset but can be altered if necessary
* `fireboard.debug` - to enable debug output


### Message Format


#### StatusEvent

| field         | type   | description                 | example                |
| ------------- | ------ | --------------------------- | ---------------------- |
| event         | string | the event, should be short  | 'service started'
| message       | string | detail message              | 'The backend service has successfully started and connected to elasticsearch' |
| category      | string `[a-z0-9._-]`| a category name              | 'backend_services' |
| link          | URL, optional | fully qualified deep link to navigate for details           | 'backend.elasticsearch.bootstrap' |
| ident         | string | a qualifier for this message           | 'backend.elasticsearch.bootstrap' |
| status        | enum `success,warn,error,info`  | the status level          | 'success' |
| severity      | number `1-6`, optional| 1 (low) - 6 (high)           | |
| uuid          | string | unique message identifier, applied by service           | |
| created       | timestamp | applied by service           | |
| correlationId | string | not yet implemented           | |
| messagepropertiessection | array of `MessagePropertiesSection`, optional  | section for key/values to store          | |

A uuid for this message and a timestamp will be applied by the service

#### MessagePropertiesSection

| field         | type   | description                 | example                |
| ------------- | ------ | --------------------------- | ---------------------- |
| name | string  | the section name         | 'service meta properties' |
| uuid | string  | optional unique identifier         | |
| messageproperties | array of `MessageProperty`  | optional section for key/values to store          | |

NOTE: the section prefix "_" is reserved for Fireboard namespaces.

#### MessageProperty

| field         | type   | description                 | example                |
| ------------- | ------ | --------------------------- | ---------------------- |
| name | string | the property key        | 'service.id' |
| value | string | the property value        | 'elastic-bridge' |


NOTE: all strings need to be JSON compatible! Escape them if needed.

### Example

```

try {
    FireboardClient.post(
        new FireboardMessageBuilder()
            .event("service started")
            .category("backend_services")
            .severity(3)
            .ident("ident")
            .link(new URL("https://fireboard.verticle.io"))
            .message("The backend service has successfully started and connected to elasticsearch")
            .status(StatusEnum.success)
            .properties(
                new MessagePropertyHelper()
                    .section("service meta properties")
                    .property("service.id", "elastic-bridge")
                    .section("all foos")
                    .property("foo1", "bar1")
                    .property("foo2", "bar2")
                    .build()
        ).build()
    );

} catch (Exception e) {
    // you should always catch anything
    //  so your own logic is not interrupted
    e.printStackTrace();
}


```

## Licence


MIT