
# Rmi Vigenère Encryption Project 
###### Java RMI program that contains encrypt, decrypt and crack methods for the Vigenère cipher
**by Ronan Connolly**  

![Cipher Header](https://github.com/RonanC/RMIEncryption/misc-resources/cipher-header.jpg "Cipher Header")

Contents:
---------
1. About
2. Structure
3. Extra Features
4. Tools & Environment used
5. Installation
6. Future Features
7. References
8. Team
  
1 - About
---
The aim of this project is to showcase the use of a Java RMI application that allows access to three methods:  
* encrypt
* decrypt
* crack
  
The project is for Dr John Healys, Distributed Systems Module, fourth year software development, GMIT.    
  
The project is a dynamic web application in Java that contains Servlets and JSP pages.  
Located here: [https://github.com/RonanC/RmiCipherWebApp](https://github.com/RonanC/RmiCipherWebApp)  
Bootstrap is used for styling and Angular for the form validation.  

The Producer-Consumer model for dealing with requests.  
A blocking queue is used to block threads.   
There is one consumer thread that keeps checking the request queue, it then talks to the Rmi Cipher Service (this project).  
This consumer thread runs infinitely, being blocked by either the RMI request or the blocking queue.  
A new producer thread is created each time a client sends a request (with a new task number).  
These producer threads wait in line to add the new Task to the blocking queue.  

The web project is hosted on a digital ocean server (based in London).  
The server environment is Ubuntu and has being fully configured for Apache Tomcat.  
It is located here:  
[http://46.101.85.159:8080/CipherWebApp/index.jsp](http://46.101.85.159:8080/CipherWebApp/index.jsp)  


2 - Structure 
---
##RmiEncrypter
**There are three packages:**  
* ie.gmit.sw.cipher
* ie.gmit.sw.server
* ie.gmit.sw.tests

####ie.gmit.sw.cipher:  
* KeyEnumerator
* QuadgramMap
* Vigenere
* VigenereBreaker (interface)
* VigenereBreakerImpl

####ie.gmit.sw.server:  
* CipherService
 
####ie.gmit.sw.tests:  
* TestVigenereBreakerImpl
  
##RmiCipherWebApp
**There are two packages:**  
* ie.gmit.sw.cipher
* ie.gmit.sw.runner

####ie.gmit.sw.cipher:  
* VigenereBreaker (interface)

####ie.gmit.sw.runner:  
* CrackerHandler
* Result
* Task
* TaskConsumer
* TaskProducer
* TaskType

3 -  Extra Features
---

I will now list all the extra features I added.

##RmiEncrypter
####ie.gmit.sw.cipher:  
##### KeyEnumerator
##### QuadgramMap
##### Vigenere
##### VigenereBreaker
##### VigenereBreakerImpl

####ie.gmit.sw.server:  
##### CipherService
 
####ie.gmit.sw.tests:  
##### TestVigenereBreakerImpl
  
  
##RmiCipherWebApp
####ie.gmit.sw.cipher:  
#####VigenereBreaker (interface)

####ie.gmit.sw.runner:  
##### CrackerHandler
##### Result
##### Task
##### TaskConsumer
##### TaskProducer
##### TaskType
  
4 - Tools & Environment used
---
 - Created the web and normal project in Java with the Eclipse EE IDE
 - Angular (form validation)
 - Bootstrap (styling)
 - Deployed to Apache Tomcat via Digital Ocean
  
5 - Installation
---
### Dependencies  
In order to allow the jar to be exectuable do the following: (unix)
```sh
> sudo chmod +x RmiCipherService.jar
```
  
## run the Rmi Cipher Service
Next create a script to run the jar  
```sh
#!/bin/sh
java -jar RmiCipherService.jar
```

Next run the script  
```sh
> ./cipher-service.sh
```

## deploy the web app
Drag the war file into the tomcat webapps folder while tomcat is running

6 - Future Features
---
###Check old Task number
You could come back at a later time with a task number or code to bring up your result.


7 - References
---
- Servlets (http://www.javatpoint.com/servlet-tutorial)
- JSP (http://www.javatpoint.com/jsp-tutorial)
- Blocking Queues (http://tutorials.jenkov.com/java-util-concurrent/blockingqueue.html)
- Multi-Threading (http://stackoverflow.com/questions/2854812/how-to-set-a-infinite-loop-and-break-it-java-threads)
  
8 - Team
---
This project was created by Ronan Connolly.
Software Development student in fourth year, term 1, GMIT  
for the Distributed Systems module.

<a href="https://github.com/RonanC"><img src="https://github.com/RonanC/DodgySpike/blob/master/PromoImages/Ronan.png" width="100px" height="100px" title="Ronan" alt="Ronan Image"/></a> <a href="https://github.com/JohnMalmsteen"><img src="https://avatars1.githubusercontent.com/u/7085486?v=3&s=400" width="100px" height="100px" title="Ronan" alt="Ronan Image"/></a>
