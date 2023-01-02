FROM    openjdk:7

WORKDIR /java-app

ADD     target/unzip/newrelic/newrelic.jar vendor/newrelic.jar
RUN     touch vendor/newrelic.yml
ENV     NEW_RELIC_APP_NAME 'Online Payment Walletgw Backend (Development)'
ENV     NEW_RELIC_LICENSE_KEY ''
ENV     NEW_RELIC_LOG STDOUT

ADD     bin/cmd bin/cmd
ADD     target/java-app/BOOT-INF/lib BOOT-INF/lib
ADD     target/java-app

EXPOSE  8080

CMD     ["bin/cmd"]
