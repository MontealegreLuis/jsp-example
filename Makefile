SHELL = /bin/bash

.PHONY: install

install:
	@echo "Creating default configuration"
	@cp .env.example .env.sh
	@echo "Generating files to match the host User and Group IDs for the container..."
	@source .env.sh; rm -f containers/images/config/.bashrc; CONTAINER_VARS='$$CONTAINER_HOSTNAME'; envsubst "$$CONTAINER_VARS" < "containers/templates/.bashrc.template" > "containers/images/config/.bashrc";
	@source .env.sh; rm -f docker-compose.yml; CONTAINER_VARS='$$CONTAINERS_PREFIX:$$DB_ROOT_PASSWORD'; envsubst "$$CONTAINER_VARS" < "containers/templates/docker-compose.yml.template" > "docker-compose.yml";
	@echo "Building containers..."
	@docker-compose up -d
