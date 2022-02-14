node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    def mvn = tool 'mvn360';
    withSonarQubeEnv() {
      sh "${mvn}/bin/mvn clean verify sonar:sonar"
    }
  }
}