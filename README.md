# interface-mapping project

This project is a simulator of interface mapping between the train consolidator and the event driven solution.

It just exposes some search API and then return results via Kafka.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `train-interface-mapping-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/train-interface-mapping-1.0.0-SNAPSHOT-runner.jar`.

```shell
# build the docker
 docker build -f src/main/docker/Dockerfile.jvm -t ibmcase/train-interface-mapping .
```

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/train-interface-mapping-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.


```shell
# build the docker
 docker build -f src/main/docker/Dockerfile.native -t ibmcase/train-interface-mapping .
```


## Build and deploy to OpenShift


Be sure to be logged to the cluster using `oc login...`
Be sure to have define config maps and secrets for the TLS certificate and password and Kafka Bootstrap URL.

```shell
# First time deploy the config map
oc apply -f src/main/kubernetes/configmap.yaml
# and secret

# build image locally and upload it to openshift private registry. It uses s2i binary build
./mvnw clean package -Dquarkus.container-image.build=true
# Verify image stream
oc get is
# Deploy as new app
oc new-app --name train-interface-mapping trainsearch/train-interface-mapping:1.0.0-SNAPSHOT
# Get service name
oc get svc
# Expose service as route for ingress traffic
oc expose svc/train-interface-mapping
# Get the route url
oc get routes
```

Or run the following command to build and deploy or redeploy.

```
./mvnw clean package -Dquarkus.container-image.deploy=true
```

To simply test the interface do a synch call to the exposed rules 

```shell
curl -X POST http://train-interface-mapping-trainsearch.gse-eda-2020-10-3-0143c5dd31acd8e030a1d6e0ab1380e3-0000.us-east.containers.appdomain.cloud/consolidatorA -H  "accept: application/json" -H  "Content-Type: application/json" -d @src/test/data/search-req.json 
```

In the event streams console go to the searchResponses topic to see the message.