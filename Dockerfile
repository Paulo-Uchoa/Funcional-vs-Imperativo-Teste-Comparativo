FROM openjdk:17-jdk-slim

WORKDIR /app

COPY main/src/ /app/

COPY libs/ /app/libs/

RUN find . -name "*.java" > sources.txt \
    && javac -cp "libs/*" @sources.txt

ENV PRINT_ARRAY=false
ENV EXECUTIONS=1
ENV CLASSPATH=/app:libs/*

CMD ["sh", "-c", "java Main"]
