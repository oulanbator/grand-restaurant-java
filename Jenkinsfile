node {
  stage('SCM') {
    checkout scm
  }
  stage('Test') {
    sh 'mvn test'
  }
  stage('Build') {
    sh 'mvn package'
  }
  stage('SonarQube Analysis') {
    withSonarQubeEnv() {
      sh 'mvn clean verify sonar:sonar'
    }
  }
  stage('Reports') {
    junit allowEmptyResults: true, skipPublishingChecks: true, testResults: '**/target/*-reports/*.xml'
  }
}