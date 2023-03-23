#!/usr/bin/env bash
set -euox pipefail
OLD_PATH="$(pwd)"
cleanup() {
  cd "${OLD_PATH}"
}
trap cleanup 0
main() {
  cd build_tools && mvn package
  cp ./target/*-dependencies.jar ./../producer.jar
  cd "${OLD_PATH}"
  java -jar ./producer.jar
}
main
