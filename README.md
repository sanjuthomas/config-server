# config-server


to get config by key -
 
curl http://localhost:8888/config/event-sourcer/dev/tenants

```
{
  "application": "event-sourcer",
  "profile": "dev",
  "key": "tenants",
  "value": [
    "one",
    "two"
  ]
}

```

to get the tenant config 

curl http://localhost:8888/config/one/uat

```
{
  "tenant": "one",
  "profile": "uat",
  "value": {
    "data-improve": "http://uat.dumptherightwing.com/api/my-data/",
    "friendlyDisplayName": "Type One from Heaven",
    "id": "t1",
    "request-attributes": [
      "one",
      "two"
    ]
  }
}
```

to get the config for application named ```event-sourcer``` and profile ```dev```

http://localhost:8888/event-sourcer/dev

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

to get the resultant config for application named ```event-sourcer``` and profile ```dev``` in json format.

http://localhost:8888/event-sourcer-dev.json

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