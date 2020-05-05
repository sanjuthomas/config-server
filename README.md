[![Build Status](https://travis-ci.com/sanjuthomas/config-server.svg?branch=master)](https://travis-ci.com/sanjuthomas/config-server)

## Spring Config Server Playground

A couple of additions to the spring config server out of the box feature set.

1. Access config by app name, profile, and config key
2. In a multi tenant application, ability to store and access the tenant config outside the application config space.

See config store repo for more details about the tenant config structure.

curl https://github.com/sanjuthomas/config-store

#### To get config by application, profile, and config key

base url - http://localhost:8888/config
app name - event-sourcer
profile - dev
config key - vertx
 
curl http://localhost:8888/config/event-sourcer/dev/vertx

```
{
  "application": "event-sourcer",
  "profile": "dev",
  "key": "vertx",
  "value": {
    "event": {
      "bus": {
        "inbound": {
          "addesses": [
            "gate-1",
            "gate-2"
          ]
        },
        "root": {
          "context": "eventbus"
        }
      }
    },
    "websocket": {
      "port": 10001
    }
  }
}
```

curl http://localhost:8888/config/event-sourcer/dev/vertx.websocket

```
{
  "application": "event-sourcer",
  "profile": "dev",
  "key": "vertx.websocket",
  "value": {
    "port": 10001
  }
}

```

curl http://localhost:8888/config/event-sourcer/dev/vertx.websocket.port

```
{
  "application": "event-sourcer",
  "profile": "dev",
  "key": "vertx.websocket.port",
  "value": 10001
}

```

#### To get the tenant config

curl http://localhost:8888/config/one/uat

```
{
  "application": "event-sourcer",
  "profile": "dev",
  "key": "vertx",
  "value": {
    "event": {
      "bus": {
        "inbound": {
          "addesses": [
            "gate-1",
            "gate-2"
          ]
        },
        "root": {
          "context": "eventbus"
        }
      }
    },
    "websocket": {
      "port": 10001
    }
  }
}
```

#### To get the config for application named ```event-sourcer``` and profile ```dev```

curl http://localhost:8888/event-sourcer/dev

```
{
  "name": "event-sourcer",
  "profiles": [
    "dev"
  ],
  "label": null,
  "version": "e99c6767dbb6b776019ef9c183715052fdb1b586",
  "state": null,
  "propertySources": [
    {
      "name": "https://github.com/sanjuthomas/config-store/service-event-sourcer/event-sourcer-dev.yml",
      "source": {
        "kafka.bootstrap.servers[0]": "dev.dumptherightwing.com:9092"
      }
    },
    {
      "name": "https://github.com/sanjuthomas/config-store/service-event-sourcer/event-sourcer.yml",
      "source": {
        "kafka.bootstrap.servers[0]": "localhost:9092",
        "kafka.client.properties.acks": "all",
        "kafka.client.properties.batch.size": 512,
        "kafka.client.properties.buffer.memory": 2048,
        "kafka.client.properties.linger.ms": 1,
        "kafka.client.properties.retries": 3,
        "name": "event sourcer",
        "version": 1.0,
        "vertx.event.bus.inbound.addesses[0]": "gate-1",
        "vertx.event.bus.inbound.addesses[1]": "gate-2",
        "vertx.event.bus.root.context": "eventbus",
        "vertx.websocket.port": 10001,
        "tenants[0]": "one",
        "tenants[1]": "two"
      }
    }
  ]
}

```

#### To get the resultant config for application named ```event-sourcer``` and profile ```dev``` in ```json``` format

curl http://localhost:8888/event-sourcer-dev.json

```
{
  "kafka": {
    "bootstrap": {
      "servers": [
        "dev.dumptherightwing.com:9092"
      ]
    },
    "client": {
      "properties": {
        "acks": "all",
        "batch": {
          "size": 512
        },
        "buffer": {
          "memory": 2048
        },
        "linger": {
          "ms": 1
        },
        "retries": 3
      }
    }
  },
  "name": "event sourcer",
  "tenants": [
    "one",
    "two"
  ],
  "version": 1.0,
  "vertx": {
    "event": {
      "bus": {
        "inbound": {
          "addesses": [
            "gate-1",
            "gate-2"
          ]
        },
        "root": {
          "context": "eventbus"
        }
      }
    },
    "websocket": {
      "port": 10001
    }
  }
}

```

#### To get the resultant config for application named ```event-sourcer``` and profile ```dev``` in ```yml``` format

curl http://localhost:8888/event-sourcer-dev.yml

```

kafka:
  bootstrap:
    servers:
    - dev.dumptherightwing.com:9092
  client:
    properties:
      acks: all
      batch:
        size: 512
      buffer:
        memory: 2048
      linger:
        ms: 1
      retries: 3
name: event sourcer
tenants:
- one
- two
version: 1.0
vertx:
  event:
    bus:
      inbound:
        addesses:
        - gate-1
        - gate-2
      root:
        context: eventbus
  websocket:
    port: 10001

```

#### To get the resultant config for application named ```event-sourcer``` and profile ```dev``` in ```key/value``` format

curl http://localhost:8888/event-sourcer-dev.properties

```
kafka.bootstrap.servers[0]: dev.dumptherightwing.com:9092
kafka.client.properties.acks: all
kafka.client.properties.batch.size: 512
kafka.client.properties.buffer.memory: 2048
kafka.client.properties.linger.ms: 1
kafka.client.properties.retries: 3
name: event sourcer
tenants[0]: one
tenants[1]: two
version: 1.0
vertx.event.bus.inbound.addesses[0]: gate-1
vertx.event.bus.inbound.addesses[1]: gate-2
vertx.event.bus.root.context: eventbus
vertx.websocket.port: 10001

```

##### Questions? send me a note at config@sanju.org