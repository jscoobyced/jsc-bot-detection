FROM gradle:jdk17-jammy

RUN DEBIAN_FRONTEND=noninteractive apt update
RUN DEBIAN_FRONTEND=noninteractive apt upgrade -y
RUN DEBIAN_FRONTEND=noninteractive apt install libatomic1 -y

WORKDIR /home/gradle

CMD gradle --no-daemon run
