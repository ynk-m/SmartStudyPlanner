#!/usr/bin/env bash
set -e

RED='\033[0;31m'
GREEN='\033[0;32m'
CYAN='\033[0;36m'
NC='\033[0m'

PROJECT_DIR="$(cd "$(dirname "$0")" && pwd)"

cleanup() {
    echo -e "\n${CYAN}Shutting down...${NC}"
    kill $BACKEND_PID $FRONTEND_PID 2>/dev/null
    docker compose -f "$PROJECT_DIR/docker-compose.yml" down
    echo -e "${GREEN}Done.${NC}"
}
trap cleanup EXIT

# 1. Start PostgreSQL
echo -e "${CYAN}Starting PostgreSQL...${NC}"
docker compose -f "$PROJECT_DIR/docker-compose.yml" up -d

# Wait for PostgreSQL to be ready
echo -e "${CYAN}Waiting for PostgreSQL...${NC}"
until docker exec smartstudy-db pg_isready -U smartstudy > /dev/null 2>&1; do
    sleep 1
done
echo -e "${GREEN}PostgreSQL ready.${NC}"

# 2. Start Backend
echo -e "${CYAN}Starting Backend...${NC}"
cd "$PROJECT_DIR/backend"
./gradlew bootRun &
BACKEND_PID=$!

# Wait for backend to be ready
echo -e "${CYAN}Waiting for Backend...${NC}"
until curl -s http://localhost:8080/api/courses > /dev/null 2>&1; do
    sleep 2
done
echo -e "${GREEN}Backend ready at http://localhost:8080${NC}"

# 3. Start Frontend
echo -e "${CYAN}Starting Frontend...${NC}"
cd "$PROJECT_DIR/frontend"
NG_CLI_ANALYTICS=false npx ng serve &
FRONTEND_PID=$!

echo -e "${GREEN}Frontend starting at http://localhost:4200${NC}"
echo -e "${CYAN}Press Ctrl+C to stop all services.${NC}"

wait
