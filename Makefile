.PHONY: setup stop dev

setup:
	if [ ! -f .env ]; then cp .env.example .env ; fi
	wget "https://github.com/51Degrees/device-detection-data/raw/main/51Degrees-LiteV4.1.hash" -O src/main/resources/51Degrees-LiteV4.1.hash
	docker-compose build

stop:
	docker-compose down

dev:
	docker-compose up api-dev rabbitmq -d