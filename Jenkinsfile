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
        sh 'docker compose up --abort-on-container-exit test'
      }
    }
  }

  post {
    success {
      junit 'build/reports/**/*.xml'
      step( [ $class: 'JacocoPublisher' ] )
    }
    always {
        sh 'docker compose down test'
    }
  }
}
