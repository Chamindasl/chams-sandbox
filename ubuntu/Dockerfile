FROM ubuntu
MAINTAINER Chaminda Amarasinghe

RUN apt-get update \
    && apt-get install -y \
        openjdk-8-jdk \
        git \
        openssh-server

RUN mkdir /var/run/sshd \
    && useradd -G sudo -m u1 \
    && echo "p\np\n" | passwd u1

ADD id_rsa /home/u1/.ssh/id_rsa 
ADD id_rsa.pub /home/u1/.ssh/id_rsa.pub 
ADD id_rsa.pub /home/u1/.ssh/authorized_keys 
ADD hosts /etc/hosts

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/

RUN chown u1:u1 /home/u1/.ssh/id_rsa && \
    chown u1:u1 /home/u1/.ssh/id_rsa.pub && \
    chown u1:u1 /home/u1/.ssh/authorized_keys && \
    chmod 600 /home/u1/.ssh/id_rsa && \
    /usr/bin/ssh-keygen -A && \
    export JAVA_HOME

ENTRYPOINT service ssh start && /bin/bash

CMD ["true"]

EXPOSE 22

