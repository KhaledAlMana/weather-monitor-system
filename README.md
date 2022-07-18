# Weather-Monitor-System

Weather monitoring application interacts with IoT devices

#### Components

| Component         | Default Port | Configurable |
| ----------------- | ------------ | ------------ |
| Zookeeper         | 2181         | ❌           |
| Kafka             | 9092         | ❌           |
| Postgres Database | 60000        | ✅           |
| Monitor App       | 62000        | ✅           |
| Ingestion Service | 62100        | ✅           |
| Weather Generator | N/A          | ❌           |

### Remainings:

- Monitor App: Consume over Kafka Stream instead of using Kafka listener
- `*` : Efficient Dockerfile && docker-compose.yml
