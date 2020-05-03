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