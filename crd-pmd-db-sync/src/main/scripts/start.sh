#!/bin/bash
export ENVIRONMENT=$1
export OPTIONS="-Denvironment=${ENVIRONMENT} -Dspring.profiles.active=dev2,oracle --enable-preview -XX:TieredStopAtLevel=1 -noverify -Dspring.output.ansi.enabled=always -Dcom.sun.management.jmxremote -Dspring.jmx.enabled=true -Dspring.liveBeansView.mbeanDomain -Dspring.application.admin.enabled=true -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -enableassertions "
echo java ${OPTIONS} -jar ../lib/crd-pmd-db-sync.jar
java ${OPTIONS} -jar ../lib/crd-pmd-db-sync.jar >& console.out &