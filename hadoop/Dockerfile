FROM chamindasl/ubuntu

MAINTAINER Chaminda Amarasinghe

RUN wget http://ftp.heanet.ie/mirrors/www.apache.org/dist/hadoop/common/hadoop-2.8.0/hadoop-2.8.0.tar.gz && \
    tar -xzf hadoop-2.8.0.tar.gz && \
    rm hadoop-2.8.0.tar.gz && \
    export HADOOP_HOME=/root/hadoop-2.8.0 && \
    mkdir /root/h_data

RUN export HADOOP_HOME=/hadoop-2.8.0 && \
    export USER=root && \
    $HADOOP_HOME/bin/hdfs namenode -format && \
    $HADOOP_HOME/sbin/start-dfs.sh