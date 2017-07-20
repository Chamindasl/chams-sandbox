FROM ubuntu
MAINTAINER Chaminda Amarasinghe

RUN apt-get update \
    && apt-get install -y \
        openjdk-8-jdk \
        git \
        openssh-server

RUN mkdir /var/run/sshd \
    && useradd -G sudo -m chams \
    && echo "p\np\n" | passwd chams

ADD id_rsa /home/chams/.ssh/id_rsa 
ADD id_rsa.pub /home/chams/.ssh/id_rsa.pub 
ADD id_rsa.pub /home/chams/.ssh/authorized_keys 
ADD hosts /etc/hosts

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/

RUN chown chams:chams /home/chams/.ssh/id_rsa && \
    chown chams:chams /home/chams/.ssh/id_rsa.pub && \
    chown chams:chams /home/chams/.ssh/authorized_keys && \
    chmod 600 /home/chams/.ssh/id_rsa && \
    /usr/bin/ssh-keygen -A && \
    export JAVA_HOME

ENTRYPOINT service ssh start && /bin/bash

CMD ["true"]

EXPOSE 22

