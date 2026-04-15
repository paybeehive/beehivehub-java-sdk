@REM Maven Wrapper Script for Windows
@REM Licensed to the Apache Software Foundation (ASF)

@IF "%JAVA_HOME%" == "" (
    @ECHO Error: JAVA_HOME is not set. Please install Java 11 or later.
    EXIT /B 1
)

SET MAVEN_WRAPPER_JAR=.mvn\wrapper\maven-wrapper.jar
SET MAVEN_WRAPPER_PROPERTIES=.mvn\wrapper\maven-wrapper.properties

IF EXIST "%MAVEN_WRAPPER_JAR%" (
    "%JAVA_HOME%\bin\java.exe" -classpath "%MAVEN_WRAPPER_JAR%" ^
        org.apache.maven.wrapper.MavenWrapperMain %*
    EXIT /B %ERRORLEVEL%
)

FOR /F "tokens=2 delims==" %%i IN ('findstr /i "distributionUrl" "%MAVEN_WRAPPER_PROPERTIES%"') DO SET DISTRIBUTION_URL=%%i
FOR /F "tokens=2 delims==" %%i IN ('findstr /i "wrapperUrl" "%MAVEN_WRAPPER_PROPERTIES%"') DO SET WRAPPER_URL=%%i

SET MAVEN_USER_HOME=%USERPROFILE%\.m2
FOR %%F IN ("%DISTRIBUTION_URL%") DO SET MAVEN_FILENAME=%%~nxF
SET MAVEN_FOLDER=%MAVEN_FILENAME:.zip=%
SET MAVEN_FOLDER=%MAVEN_FOLDER:-bin=%
SET MAVEN_HOME=%MAVEN_USER_HOME%\wrapper\dists\%MAVEN_FOLDER%

IF NOT EXIST "%MAVEN_HOME%\bin\mvn.cmd" (
    MKDIR "%MAVEN_HOME%" 2>NUL
    ECHO Downloading Maven from %DISTRIBUTION_URL%...
    powershell -Command "Invoke-WebRequest -Uri '%DISTRIBUTION_URL%' -OutFile '%MAVEN_HOME%\%MAVEN_FILENAME%'"
    powershell -Command "Expand-Archive -Path '%MAVEN_HOME%\%MAVEN_FILENAME%' -DestinationPath '%MAVEN_HOME%' -Force"
    FOR /D %%D IN ("%MAVEN_HOME%\apache-maven-*") DO (
        XCOPY /E /I /Y "%%D\*" "%MAVEN_HOME%\" >NUL
        RMDIR /S /Q "%%D"
    )
)

"%MAVEN_HOME%\bin\mvn.cmd" %*
EXIT /B %ERRORLEVEL%
