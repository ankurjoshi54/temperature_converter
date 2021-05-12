pipeline {
  agent {
    node {
      label 'controller'
    }

  }
  stages {
    stage('build') {
      steps {
        git(url: 'https://github.com/ankurjoshi54/temperature_converter.git', branch: 'master')
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