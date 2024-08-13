#!/usr/bin/env bash
javac -d out --module-source-path module-task $(find module-task -name "*.java")