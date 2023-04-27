@echo off

start ..\jdk-17.0.3\bin\javaw.exe --module-path "..\javafx\javafx-sdk-17.0.6\lib" --add-modules javafx.controls,javafx.base -jar .\target\lm_client-0.0.1-jar-with-dependencies.jar