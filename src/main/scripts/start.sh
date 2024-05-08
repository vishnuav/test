#!/bin/bash
export ENVIRONMENT=$1
export OPTIONS="-Denvironment=${ENVIRONMENT} -Dspring.profiles.active=${ENVIRONMENT} --enable-preview -XX:TieredStopAtLevel=1 -noverify -Dspring.output.ansi.enabled=always -Dcom.sun.management.jmxremote -Dspring.jmx.enabled=true -Dspring.liveBeansView.mbeanDomain -Dspring.application.admin.enabled=true -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -enableassertions "
echo java ${OPTIONS} -jar ../lib/crd-pmd-poc-$1.jar
java ${OPTIONS} -jar ../lib/crd-pmd-poc-$1.jar >& console.out &