TAG=$(shell git describe --tags --long)

build:
	mvn clean package -DskipTests
	unzip -q target/walletgw-backend-0.0.1-SNAPSHOT.jar -d target/java-app
	docker build --pull -t registry.truemoney.com.vn/ewallet/backend:$(TAG) .

push: build
	docker push registry.truemoney.com.vn/ewallet/backend:$(TAG)

deploy:
	@echo "\033[92mThis will deploy tag $(TAG)\033[0m"
	cd $(ANSIBLE_ROOT) && ansible-playbook -e version=$(TAG) -Dvv -i dev ewallet-backend-deploy.yml

version:
	@echo $(TAG)
