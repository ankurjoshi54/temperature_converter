pipeline {
  agent {
    node {
      label 'controller'
    }

  }
  stages {
    stage('build') {
      steps {
        checkout scm
        sh './gradlew clean build'
      }
    }

    stage('test') {
      steps {
        sh './gradlew test'
      }
    }

  }
}