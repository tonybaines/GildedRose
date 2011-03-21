#!/bin/bash
PACKAGE="refactoring-kata"
rm -rf $PACKAGE $PACKAGE.zip
mkdir $PACKAGE
cp -a README .project .classpath src pom.xml $PACKAGE
zip -r $PACKAGE.zip $PACKAGE
rm -rf $PACKAGE
