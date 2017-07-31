if [ "$BASH" ]; then
  if [ -f ~/.bashrc ]; then
    . ~/.bashrc
  fi
fi

export JAVA_HOME="/usr/lib/jvm/java-8-openjdk-amd64/"