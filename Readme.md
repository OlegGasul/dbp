## Requirements

1. JDK - 1.7
2. MySQL - 5.7
3. Tomcat - 7

## HTTPS configuration
add to tomcat server.xml

<Connector port="8443"
         protocol="org.apache.coyote.http11.Http11Protocol"
         SSLEnabled="true"
         maxThreads="150"
         scheme="https"
         secure="true"
         keystoreFile="${path}/server.jks"
         keystorePass="123456"
         sslProtocol="TLS" />

#### Note: server.jks file is located in cert folder

## MySQL
## in my.ini file add the following setting to allow big package to be stored
max_allowed_packet=25M

## CHANGING SPRING PROFILE

        In case of need to change spring profile you should add a system environment variable.
        For example to set spring profile to 'test' or 'prod' you should add in
        .bash_profile file which is location in your user directory. If the file does not exist just create it.

        Here is an example:

            1. open the .bash_profile file
            2. add the following command bellow

               export SPRING_PROFILES_ACTIVE=prod

            or change the value if 'SPRING_PROFILES_ACTIVE' already exists

        After adding or editing variable you should run the following command 'source ~/.bash_profile' in terminal to reload environments.

        After all restart the tomcat server

