#!/bin/bash
PACKAGE="refactoring-kata"
rm -rf $PACKAGE $PACKAGE.zip
mkdir $PACKAGE
gradle copyToLib
cp -a README.md .project .classpath src lib *.iml $PACKAGE
zip -r $PACKAGE.zip $PACKAGE
rm -rf $PACKAGE
