pipeline {
  agent any
  
  tools {
    maven "mvn360"
    jdk "jdk11"
  }

  stages {
    stage('Initialize'){
      steps{
        echo "PATH = ${M2_HOME}/bin:${PATH}"
        echo "M2_HOME = /opt/maven"
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test'
      }

    }
    stage('Build') {
      steps {
        sh 'mvn package'
      }
    }
    stage('SonarQube Analysis') {
      withSonarQubeEnv() {
        sh "mvn -e clean verify sonar:sonar"
      }
    }
  }
  post {
    success {
      junit '**/target/*-reports/*.xml'
    }
    always {
      junit(
        allowEmptyResults: true,
        testResults: '**/target/surefire-reports/.xml'
      )
    }
  }
}