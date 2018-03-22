FROM jenkins/jenkins:lts

USER root
RUN set -x && \
    apt-get update
USER jenkins

COPY executors.groovy /usr/share/jenkins/ref/init.groovy.d/executors.groovy
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt

RUN set -x \
    && /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt \
    && echo '2.0' > /usr/share/jenkins/ref/jenkins.install.UpgradeWizard.state