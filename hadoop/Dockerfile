FROM chamindasl/ubuntu

MAINTAINER Chaminda Amarasinghe

RUN wget http://ftp.heanet.ie/mirrors/www.apache.org/dist/hadoop/common/hadoop-2.8.0/hadoop-2.8.0.tar.gz && \
    tar -xzf hadoop-2.8.0.tar.gz && \
    rm hadoop-2.8.0.tar.gz && \
    export HADOOP_HOME=/root/hadoop-2.8.0 && \
    mkdir /root/h_data

ADD core-site.xml /hadoop-2.8.0/etc/hadoop/core-site.xml

RUN export HADOOP_HOME=/hadoop-2.8.0 && \
    export USER=root && \
    sed -i 's|export JAVA_HOME|export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 #export JAVA_HOME|g' /hadoop-2.8.0/etc/hadoop/hadoop-env.sh && \
    $HADOOP_HOME/bin/hdfs namenode -format -clusterid hadoop && \
    $HADOOP_HOME/sbin/start-dfs.sh
    
EXPOSE 50070