#!/bin/bash -eax
javac -d bin -cp bin:cs1302-str-list.jar src/cs1302/test/ListTester.java
java -cp bin:cs1302-str-list.jar cs1302.test.ListTester
