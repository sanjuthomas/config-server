# config-server


to get config by key -
 
curl http://localhost:8888/api/event-sourcer/dev/tenants

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