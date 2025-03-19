./gradlew build -x test
rm -rf target
mkdir target
cp build/libs/demo-0.0.1-SNAPSHOT.jar target/