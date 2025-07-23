#!/bin/bash

# Включаем Docker демон Minikube
eval $(minikube docker-env)

# Переходим в директорию с сервисами
cd src/services/

# Массив сервисов для сборки
services=(
  "booking-service"
  "gateway-service"
  "hotel-service"
  "loyalty-service"
  "payment-service"
  "report-service"
  "session-service"
)

# Собираем образы для каждого сервиса
for service in "${services[@]}"; do
  echo -e "\n\033[1mBuilding ${service}...\033[0m"
  
  if [ -d "${service}" ]; then
    if [ -f "${service}/Dockerfile" ]; then
      if docker build --no-cache -t "${service}:latest" "./${service}"; then
        echo -e "\033[32mSuccessfully built ${service}\033[0m"
      else
        echo -e "\033[31mFailed to build ${service}\033[0m"
        exit 1
      fi
    else
      echo -e "\033[31mDockerfile not found in ${service}, skipping...\033[0m"
    fi
  else
    echo -e "\033[31mDirectory ${service} not found, skipping...\033[0m"
  fi
done

# Возвращаемся в исходную директорию
cd ../..

# Выводим список собранных образов
echo -e "\n\033[1mBuilt Docker images:\033[0m"
docker images | grep -E "$(printf "%s|" "${services[@]}" | sed 's/|$//')"
