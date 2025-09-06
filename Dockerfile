FROM openjdk:17-jdk-slim

WORKDIR /app

COPY main/src/ /app/

RUN find . -name "*.java" > sources.txt && javac @sources.txt

ENV PRINT_ARRAY=false
ENV EXECUTIONS=1
ENV CLASSPATH=/app

CMD ["sh", "-c", "java -cp /app Main"]
