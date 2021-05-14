pipeline {
  agent any
  options {
    skipDefaultCheckout()
  }
  environment {
    PATH = "$PATH:/usr/local/bin"
  }

  stages {
    stage('build') {
      steps {
        checkout scm
      }
    }

    stage('test') {
      steps {
        sh 'docker-compose up test --abort-on-container-exit'
      }
    }

  }
}
