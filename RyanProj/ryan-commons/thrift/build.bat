rd /s /q gen-java
rd /s /q gen-cpp
thrift -gen java -gen cpp MessagePacketProtocal.thrift
xcopy gen-java\*.* ..\src\main\java /s /y
pause