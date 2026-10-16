## Database (Docker)

1. Start Postgres: `docker compose up -d`
2. Reset schema + seed (use after schema changes):  
   `docker exec -i fitnessapp-db psql -U fitnessuser -d fitnessapp < database/reset.sql`
3. JDBC URL in the app: `localhost:5433` (host port **5433** avoids clashing with a local Postgres on 5432).

Credentials match `docker-compose.yml`: user `fitnessuser`, password `fitnesspass`, database `fitnessapp`.

## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
