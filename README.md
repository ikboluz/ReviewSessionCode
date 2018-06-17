cucumber-jvm-parallel-plugin:
For each feature file in your project, it will create a separate CukesRunner.


cucumber-jvm-parallel-plugin -> generate runners for each FEATURE,SCENARIO

maven-failsafe plugin -> will take those runners and run them in paralell


maven-failsafe plugin vs maven-surefire plugin

command to run feature files in paralell:

mvn -Dtest=SomeTests -DfailIfNoTests=false verify