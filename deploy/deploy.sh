#!/usr/bin/env bash
set -e

APP_DIR="${APP_DIR:-/opt/smartstudy}"
REPO="https://github.com/ynk-m/SmartStudyPlanner.git"

echo "=== SmartStudyPlanner Deploy ==="

# Clone or pull
if [ -d "$APP_DIR" ]; then
    echo "Pulling latest changes..."
    cd "$APP_DIR"
    git fetch origin main
    git reset --hard origin/main
else
    echo "Cloning repository..."
    git clone "$REPO" "$APP_DIR"
    cd "$APP_DIR"
fi

# Build and restart
echo "Building and restarting containers..."
docker compose -f docker-compose.prod.yml build --no-cache
docker compose -f docker-compose.prod.yml up -d

# Cleanup old images
docker image prune -f

echo "=== Deploy complete ==="
docker compose -f docker-compose.prod.yml ps
