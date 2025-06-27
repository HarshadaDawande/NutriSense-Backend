# NutriSense Backend

Spring Boot backend for NutriSense demo.  
Provides REST endpoints for user management, food tracking and LLM-powered features.

## Running locally

```bash
# clone & move into the project
git clone <this-repo>
cd NutriSense-Backend

# start the application (Maven wrapper)
./mvnw spring-boot:run
```

### Required environment variables
The LLM proxy bean requires API key which **must** be provided as environment variable before you start the app:

```bash
export OPENROUTER_API_KEY=<your-openrouter-key>
```

Spring automatically maps those variables to the configuration property `openrouter.api-key` used in `OpenRouterProxy`.

