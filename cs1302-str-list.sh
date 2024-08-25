#!/bin/bash -eax
javac -d bin -cp cs1302-str-list.jar src/cs1302/p2/BaseStringList.java
javac -d bin -cp bin:cs1302-str-list.jar src/cs1302/p2/ArrayStringList.java
javac -d bin -cp bin:cs1302-str-list.jar src/cs1302/p2/LinkedStringList.java
javac -d bin -cp bin:cs1302-str-list.jar src/cs1302/test/ListTester.java
java -cp bin:cs1302-str-list.jar cs1302.test.ListTester
