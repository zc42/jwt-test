Spring Boot Actuator provides a variety of endpoints that expose different aspects of the application's internal state and runtime behavior. Below is a comprehensive list of the Actuator endpoints available in Spring Boot 2.x and 3.x (as of the latest versions).

---

### **Core Actuator Endpoints**

| **Endpoint**     | **Description**                                                                                   |
|-------------------|---------------------------------------------------------------------------------------------------|
| `auditevents`     | Displays audit events, such as user authentication and authorization events.                     |
| `beans`           | Shows a list of all beans in the application context.                                            |
| `caches`          | Exposes available caches and their configurations.                                               |
| `conditions`      | Provides information about condition evaluation (auto-configuration report).                     |
| `configprops`     | Displays all configuration properties bound by the application.                                  |
| `env`             | Exposes the current environment properties, including system properties and environment variables.|
| `health`          | Displays the health information of the application.                                              |
| `info`            | Displays arbitrary application information defined by the developer.                             |
| `loggers`         | Shows and allows management of the application’s logging levels.                                 |
| `metrics`         | Provides metrics data for the application, such as JVM memory usage, threads, and HTTP requests. |
| `mappings`        | Displays all `@RequestMapping` paths.                                                            |
| `shutdown`        | Allows the application to be gracefully shut down (disabled by default for safety).              |
| `threaddump`      | Provides a thread dump of the application.                                                       |
| `httptrace`       | Shows HTTP request-response traces (requires `HttpTraceRepository` implementation).              |
| `integrationgraph`| Exposes the Spring Integration graph (if applicable).                                            |
| `liquibase`       | Displays Liquibase database migration details.                                                   |
| `flyway`          | Displays Flyway database migration details.                                                      |
| `heapdump`        | Provides a heap dump for troubleshooting memory issues.                                          |
| `sessions`        | Exposes HTTP session information (requires Spring Session).                                      |
| `serviceregistry` | Shows the service registry status (if using Spring Cloud).                                       |

---

### **Exposure of Actuator Endpoints**
- **By Default**: Only the `health` and `info` endpoints are exposed.
- To expose additional endpoints, you need to configure `management.endpoints.web.exposure.include` in your `application.properties` or `application.yml`:

#### Example:
```properties
management.endpoints.web.exposure.include=health,info,metrics,loggers
```

- To expose **all** endpoints:
```properties
management.endpoints.web.exposure.include=*
```

---

### **Security Considerations**
By default, sensitive endpoints like `shutdown` are disabled or secured. Use Spring Security or other mechanisms to protect these endpoints in production.

#### Example of Allowing Access to All Actuator Endpoints:
```properties
management.endpoints.web.exposure.include=*
management.endpoint.shutdown.enabled=true
```

---

### **Viewing Actuator Endpoints**
Once configured, the Actuator endpoints are typically accessible under the `/actuator` base path. Example:
- `http://localhost:8080/actuator/health`
- `http://localhost:8080/actuator/metrics`

If you change the `management.server.base-path` property, the base path will change accordingly:
```properties
management.server.base-path=/monitor
```
Endpoints will then be accessible at `/monitor`.

---

Let me know if you'd like specific details about any of these endpoints!
