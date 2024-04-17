# Coherence Spring Session Demo (Spring Framework)

This Coherence Spring demo uses Spring Framework ONLY (No Spring Boot)
and deploys as a WAR file.

## Overview

The example application connects to a remote Coherence cluster using
Coherence*Extend and POF serialization.

## Prerequisites

You will need a running instance of a remote Coherence cluster. You can
use the following project to start a Coherence cluster locally:

https://github.com/ghillert/coherence-spring-session-server

That server application is Spring Boot based.

## Running the Application

To run the application, execute the following command:

```bash
mvn clean package
```

This will create a WAR file in the `target` directory. Deploy this WAR
to a Servlet container such as Jetty 9.
