# weather-monitor-system

Weather monitoring application interacts with IoT devices

#### Components

All you environment varibales are in `.env`. It is uncommon with springboot, but definetly friendlier with docker will more simplicity.

| Component         | Default Port | Configurable | Notes                                                                                                                                                               |
| ----------------- | ------------ | ------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Zookeeper         | 2181         | ❌           | N/A                                                                                                                                                                 |
| Kafka             | 9092         | ❌           | N/A                                                                                                                                                                 |
| Postgres Database | 60000        | ✅           | N/A                                                                                                                                                                 |
| Monitor App       | 62000        | ✅           | API Collection & Env made by [thunder client](https://www.thunderclient.com/ "thunder client") under ./thunder_client <br>Basic Auth (User:Password): client:tomato |
| Ingestion Service | 62100        | ✅           | API Collection & Env made by [thunder client](https://www.thunderclient.com/ "thunder client") under ./thunder_client <br>Basic Auth (User:Password): client:tomato |
| Weather Generator | N/A          | ❌           | Communicating with ingestion-service over docker internal network [wms]                                                                                             |

### Remainings:

- Monitor App: Consume over Kafka Stream instead of using Kafka listener
- `*` : Efficient Dockerfile && docker-compose.yml
