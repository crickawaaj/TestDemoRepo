pipeline {
  agent any

  environment {
    IMAGE_NAME = "your-dockerhub-username/springboot-jenkins-docker"
    IMAGE_TAG = "${env.BUILD_NUMBER}"
    // If you want to push, set DOCKER_CREDENTIALS in Jenkins (username/password or token).
    // DOCKER_REGISTRY = "docker.io"
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build (Maven)') {
      steps {
        sh 'mvn -B -DskipTests=false clean package'
      }
    }

    stage('Unit Tests') {
      steps {
        junit '**/target/surefire-reports/*.xml'
      }
    }

    stage('Build Docker Image') {
      steps {
        script {
          dockerImage = docker.build("${env.IMAGE_NAME}:${env.IMAGE_TAG}")
        }
      }
    }

    stage('Run Container (smoke test)') {
      steps {
        script {
          docker.image("${env.IMAGE_NAME}:${env.IMAGE_TAG}").withRun('-p 8080:8080') { c ->
            // simple curl to health endpoint
            sh "sleep 3" // give container a moment to start
            sh "curl -f http://localhost:8080/api/hello"
          }
        }
      }
    }

    stage('Push to Registry (optional)') {
      when {
        expression { return env.PUSH_TO_REGISTRY == 'true' }
      }
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKERHUB_USER', passwordVariable: 'DOCKERHUB_PASS')]) {
          sh "echo $DOCKERHUB_PASS | docker login -u $DOCKERHUB_USER --password-stdin"
          sh "docker push ${env.IMAGE_NAME}:${env.IMAGE_TAG}"
        }
      }
    }
  }

  post {
    always {
      cleanWs()
    }
    success {
      echo "Build succeeded: ${env.BUILD_NUMBER}"
    }
    failure {
      echo "Build failed"
    }
  }
}
