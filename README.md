Chat Server Java

Protocol is:

1) Send an array of characters to client with ASCII integer value of message (e.g. 124).

2) Send Message to Client.

To get the server running do the following:

1) Navigate into the Java_Server folder.

2) Type "ant".

3) After build is finished navigate into the jar directory. E.X. "cd build/jar".

4) Type "java -jar ChatServerJava.jar [PORTNUMBER]"


IF you desire, I have included a script that will automatically start the server on port 55222. To execute it do the following:

1) Navigate into the Java_Server folder.

2) Type "chmod +x easy_setup.sh"

3) Type "./easy_setup.sh"

Server has no quit method and must be manually terminated (e.x control + c).
