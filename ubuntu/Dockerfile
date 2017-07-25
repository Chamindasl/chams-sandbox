FROM ubuntu
MAINTAINER Chaminda Amarasinghe

RUN apt-get update \
    && apt-get install -y --no-install-recommends \
        openjdk-8-jdk \
        git \
        openssh-server

RUN mkdir /var/run/sshd \
    && echo "p\np\n" | passwd

ADD id_rsa /root/.ssh/id_rsa
ADD id_rsa.pub /root/.ssh/id_rsa.pub 
ADD id_rsa.pub /root/.ssh/authorized_keys 
ADD hosts /etc/hosts

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/

RUN /usr/bin/ssh-keygen -A && \
    chmod 600 /root/.ssh/id_rsa && \
    sed -i 's/PermitRootLogin prohibit-password/PermitRootLogin yes/g' /etc/ssh/sshd_config && \
    export JAVA_HOME

ENTRYPOINT service ssh start && /bin/bash

CMD ["true"]

EXPOSE 22

