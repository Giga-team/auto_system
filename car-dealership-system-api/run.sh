#!/bin/bash

if ! command -v docker-compose &> /dev/null; then
  echo "Docker Compose not installed, write this command:"
  echo "sudo apt install docker-compose"
  exit 1
fi

docker-compose -f postgres.yml -f pg-admin.yml up

echo "Started!"