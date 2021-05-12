pipeline {
  agent any

  stages {
    stage('build') {
      steps {
        checkout scm
      }
    }

    stage('test') {
      steps {
        sh 'docker-compose up test'
      }
    }

  }
}
